DROP SCHEMA IF EXISTS firma CASCADE;

CREATE SCHEMA firma;

DROP TABLE IF EXISTS ansatt CASCADE;

--Tabell: Ansatt
CREATE TABLE firma.ansatt
(
    ansattID SERIAL UNIQUE,
    brukernavn VARCHAR(4) NOT NULL UNIQUE,
    fornavn VARCHAR(25) NOT NULL,
    etternavn VARCHAR (25) NOT NULL,
    ansDato DATE NOT NULL,
    stilling VARCHAR (25) NOT NULL, --bør nok være en FK som stillingID
    mndLonn DECIMAL(8, 2),
    PRIMARY KEY (ansattID)
    --FOREIGN KEY (stillingID) REFERENCES stilling (stillingID)
);

INSERT INTO firma.ansatt (brukernavn, fornavn, etternavn, ansDato, stilling, mndLonn) VALUES
('kjeb', 'Kjetil', 'Berg', CURRENT_TIMESTAMP, 'Gresskjemmer', 45933.02),
('larb', 'Lars Erik', 'Birkeland', '1976-12-03', 'Marihøne-klapper', 46933.03),
('lima', 'Lima', 'Aliar', '2048-03-04', 'Frimerkesleiker', 63933.06);