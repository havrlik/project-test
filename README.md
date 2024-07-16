# README

Author: Havrlik, created on: 2024

## Goal

- Show a simple Spring project as an example.

## How to start

1. Download dependencies and build Gradle project.
2. Create `.env` file, you can copy content from `dev.example.env`.
3. You need to prepare empty PostgreSQL database, then set access in the `.env` file.
4. Run the application by main method in `/src/main/java/com/havrlik/test/project/Application.java`.

## Features

- The application has one endpoint `POST /save`.
  - [Note: do not work if you do not have the required source file.]
  - You need to set URL of the file in `.env` file, variable `FILE_URL`.
  - Functionality download zip file from given URL. Inner file have to be an XML.
    The XML contains some details (`name`, `code`) about a municipality and parts of a municipality.
    The XML is parsed and the details are saved to the database.
