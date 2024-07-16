package com.havrlik.test.project.save;

import com.havrlik.test.project.municipality.municipality.Municipality;
import com.havrlik.test.project.municipality.municipality.MunicipalityRepository;
import com.havrlik.test.project.municipality.part.MunicipalityPart;
import com.havrlik.test.project.municipality.part.MunicipalityPartRepository;
import com.havrlik.test.project.save.exception.EntityNotUniqueException;
import com.havrlik.test.project.save.exception.ParseException;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

@Service
public class ParseService {

    private final MunicipalityRepository municipalityRepository;
    private final MunicipalityPartRepository municipalityPartRepository;

    private static final String MARK_X_OBEC = "x:Obec";
    private static final String MARK_Y_KOD = "y:Kod";
    private static final String MARK_Y_NAZEV = "y:Nazev";
    private static final String MARK_X_CAST_OBCE = "x:CastObce";
    private static final String MARK_Z_KOD = "z:Kod";
    private static final String MARK_Z_NAZEV = "z:Nazev";

    public ParseService(
            final MunicipalityRepository municipalityRepository,
            final MunicipalityPartRepository municipalityPartRepository
    ) {
        this.municipalityRepository = municipalityRepository;
        this.municipalityPartRepository = municipalityPartRepository;
    }

    /**
     * This method will parse data from the file.
     * Prepared for exact file content. It only processes the first municipality that must be included and the related
     * parts of the municipality.
     */
    public void parseData(String workingDirectory, File xmlFile) {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(new File(workingDirectory + xmlFile.getName()));
            doc.getDocumentElement().normalize();

            // Municipality. Get the first item.
            NodeList nodeMunicipalities = doc.getElementsByTagName(MARK_X_OBEC);

            Element municipalityAttribute = (Element) nodeMunicipalities.item(0);

            String municipalityCode = municipalityAttribute.getElementsByTagName(MARK_Y_KOD).item(0).getTextContent();
            String municipalityName = municipalityAttribute.getElementsByTagName(MARK_Y_NAZEV).item(0).getTextContent();

            Municipality municipality = new Municipality(municipalityCode, municipalityName);
            checkUnique(municipality);
            municipalityRepository.save(municipality);

            // Municipality part. Get all items.
            NodeList nodeMunicipalityParts = doc.getElementsByTagName(MARK_X_CAST_OBCE);

            for (int i = 0; i < nodeMunicipalityParts.getLength(); i++) {
                Element municipalityPartAttribute = (Element) nodeMunicipalityParts.item(i);

                String municipalityPartCode = municipalityPartAttribute.getElementsByTagName(MARK_Z_KOD).item(0).getTextContent();
                String municipalityPartName = municipalityPartAttribute.getElementsByTagName(MARK_Z_NAZEV).item(0).getTextContent();
                String municipalityPartMunicipalityCode = municipalityPartAttribute.getElementsByTagName(MARK_Y_KOD).item(0).getTextContent();

                if (!municipalityPartMunicipalityCode.equals(municipality.getCode())) {
                    throw new RuntimeException(
                            "Unexpected municipality code '" + municipalityPartMunicipalityCode +
                                    "' of municipality part '" + municipalityPartCode + "'."
                    );
                }

                MunicipalityPart municipalityPart = new MunicipalityPart(municipalityPartCode, municipalityPartName, municipality);
                checkUnique(municipalityPart);
                municipalityPartRepository.save(municipalityPart);
            }

        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new ParseException(e.toString());
        }
    }

    private void checkUnique(Municipality municipality) {
        if (municipalityRepository.existsById(municipality.getCode())) {
            throw new EntityNotUniqueException(Municipality.class, municipality.getCode());
        }
    }

    private void checkUnique(MunicipalityPart municipalityPart) {
        if (municipalityPartRepository.existsById(municipalityPart.getCode())) {
            throw new EntityNotUniqueException(MunicipalityPart.class, municipalityPart.getCode());
        }
    }
}
