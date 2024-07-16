package com.havrlik.test.project.save;

import com.havrlik.test.project.save.exception.CreateDirectoryException;
import com.havrlik.test.project.save.exception.EmptyFileException;
import com.havrlik.test.project.save.exception.FileDeleteException;
import com.havrlik.test.project.save.exception.FileDownloadException;
import com.havrlik.test.project.save.exception.UnzipException;
import com.havrlik.test.project.save.exception.ZipEntryDirectoryException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
public class SaveService {

    private final String fileUrl;
    private final ParseService parseService;

    private static final String TEMP_DIRECTORY_FOR_FILES = "temp_save_file";
    private static final String TEMP_FILE_NAME = "temp_file";

    public SaveService(
            @Value("${variables.url}") final String fileUrl,
            final ParseService parseService
    ) {
        this.fileUrl = fileUrl;
        this.parseService = parseService;
    }

    public void save() {
        // Prepare folder name for files.
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        String randomUUID = UUID.randomUUID().toString();
        String workingFolder = String.format("%s_%s", timeStamp, randomUUID);
        String workingDirectory = String.format("%s/%s", TEMP_DIRECTORY_FOR_FILES, workingFolder);
        String tempOriginFilePathName = String.format("%s/%s", workingDirectory, TEMP_FILE_NAME);

        // Download a ZIP file.
        downloadFile(fileUrl, tempOriginFilePathName);

        File xmlFile = null;

        try {
            // Unzip.
            xmlFile = unzipFile(workingDirectory, tempOriginFilePathName);

            // Parse and save to database.
            parseService.parseData(workingDirectory, xmlFile);
        } finally {
            // Delete files.
            boolean deletedZipFile;
            boolean deletedXmlFile = false;

            File zipFile = new File(tempOriginFilePathName);
            deletedZipFile = zipFile.delete();

            if (xmlFile != null) {
                deletedXmlFile = xmlFile.delete();
            }

            if (
                    !deletedZipFile ||
                            (xmlFile != null && !deletedXmlFile)
            ) {
                throw new FileDeleteException(workingDirectory);
            }
        }
    }

    private static void downloadFile(String fileUrl, String tempOriginFilePathName) {
        try (
                BufferedInputStream in = new BufferedInputStream(new URL(fileUrl).openStream());
                FileOutputStream fileOutputStream = new FileOutputStream(tempOriginFilePathName)
        ) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
            throw new FileDownloadException(e.toString(), fileUrl);
        }
    }

    private static File unzipFile(String workingDirectory, String tempOriginFilePathName) {
        File newFile;
        File destDir = new File(workingDirectory);

        try {
            byte[] buffer = new byte[1024];
            ZipInputStream zis = new ZipInputStream(new FileInputStream(tempOriginFilePathName));

            // Precondition: the zip file contains one file to be unzipped.
            ZipEntry zipEntry = zis.getNextEntry();
            if (zipEntry == null) {
                throw new EmptyFileException(workingDirectory);
            }

            newFile = newUnzippedFile(destDir, zipEntry);
            if (zipEntry.isDirectory()) {
                if (!newFile.isDirectory() && !newFile.mkdirs()) {
                    throw new CreateDirectoryException(newFile.toString());
                }
            } else {
                // Fix for Windows-created archives.
                File parent = newFile.getParentFile();
                if (!parent.isDirectory() && !parent.mkdirs()) {
                    throw new CreateDirectoryException(parent.toString());
                }

                // Write file content.
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
            }

            zis.closeEntry();
            zis.close();

            return newFile;
        } catch (IOException e) {
            throw new UnzipException(e.toString(), workingDirectory);
        }
    }

    private static File newUnzippedFile(File destinationDir, ZipEntry zipEntry) throws IOException {
        File destFile = new File(destinationDir, zipEntry.getName());

        String destDirPath = destinationDir.getCanonicalPath();
        String destFilePath = destFile.getCanonicalPath();

        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw new ZipEntryDirectoryException(destFile.getCanonicalPath(), zipEntry.getName());
        }

        return destFile;
    }
}
