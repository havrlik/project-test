CREATE TABLE municipality
(
    code TEXT,
    name TEXT NOT NULL,
    PRIMARY KEY (code)
);

CREATE TABLE municipality_part
(
    code              TEXT,
    name              TEXT NOT NULL,
    municipality_code TEXT NOT NULL,
    PRIMARY KEY (code)
);

ALTER TABLE municipality_part
    ADD CONSTRAINT FK_MUNICIPALITY_CODE_ON_MUNICIPALITY FOREIGN KEY (municipality_code) REFERENCES municipality (code);
