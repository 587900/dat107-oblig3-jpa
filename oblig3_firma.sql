DROP SCHEMA IF EXISTS firma CASCADE;

CREATE SCHEMA firma;

DROP TABLE IF EXISTS ansatt CASCADE;

--Tabell: Ansatt
CREATE TABLE ansatt
(
    ansattID SERIAL UNIQUE,
    brukernavn VARCHAR(10) UNIQUE,
    fornavn VARCHAR(25) NOT NULL,
    etternavn VARCHAR (25) NOT NULL,
    ansDato DATE NOT NULL,
    stilling VARCHAR (25) NOT NULL, --må nok være en FK som stillingID
    mndLonn DECIMAL(8, 2),
    PRIMARY KEY (ansattID),
    --FOREIGN KEY (stillingID) REFERENCES stilling (stillingID)
);  