DROP SCHEMA IF EXISTS firma CASCADE;

CREATE SCHEMA firma;

DROP TABLE IF EXISTS ansatt CASCADE;
DROP TABLE IF EXISTS avdeling CASCADE;
DROP TABLE IF EXISTS prosjekt CASCADE;
DROP TABLE IF EXISTS prosjektdeltakelse CASCADE;

--Tabell: Ansatt
CREATE TABLE firma.ansatt
(
    ansattid SERIAL NOT NULL UNIQUE,
    brukernavn VARCHAR(4) NOT NULL UNIQUE,
    fornavn VARCHAR(25) NOT NULL,
    etternavn VARCHAR (25) NOT NULL,
    ansDato DATE NOT NULL,
    stilling VARCHAR (25) NOT NULL,
    avdelingid INTEGER NOT NULL,
    mndLonn DECIMAL(8, 2),
    PRIMARY KEY (ansattid)
);

--Tabell: Avdeling
CREATE TABLE firma.avdeling
(
    avdelingid SERIAL NOT NULL UNIQUE,
    avdeling VARCHAR(25),
    sjef INTEGER,
    PRIMARY KEY (avdelingid)
);

--Tabell: Prosjekt
CREATE TABLE firma.prosjekt
(
    prosjektid SERIAL NOT NULL UNIQUE,
    prosjektnavn VARCHAR(25),
    PRIMARY KEY (prosjektid)
);

--Tabell: Prosjektdeltakelse
CREATE TABLE firma.prosjektdeltakelse
(
    prosjektid INTEGER,
    ansattid INTEGER,
    rolle VARCHAR(25),
    timer DECIMAL(5, 2),
	FOREIGN KEY (prosjektid) REFERENCES firma.prosjekt (prosjektid) ON UPDATE CASCADE,
	FOREIGN KEY (ansattid) REFERENCES firma.ansatt (ansattid) ON UPDATE CASCADE,
	PRIMARY KEY (prosjektid,ansattid)
);

INSERT INTO firma.avdeling (avdelingid, avdeling) VALUES
(1, 'Avdeling for post'),
(2, 'Avdeling for ører m.m.'),
(2, 'Avdeling for gress');

INSERT INTO firma.ansatt (brukernavn, fornavn, etternavn, ansDato, stilling, avdelingid, mndLonn) VALUES
('arnb', 'Arne', 'Fislefjeld', '1992-03-02', 'Ørepiller', 2, 45933.02),
('kjeb', 'Kjetil', 'Berg', '2003-12-02', 'Gresskjemmer', 3, 29733.13),
('larb', 'Lars Erik', 'Birkeland', '1976-12-03', 'Marihøne-klapper', 1, 36933.03),
('lima', 'Lima', 'Aliar', '2048-03-04', 'Frimerkesleiker', 1, 63933.06),
('guma', 'Guri', 'Malla', '2008-06-29', 'Ørepiller', 2, 80932.77),
('stem', 'Stein', 'Magnus', '1996-08-01', 'Luftfukter', 3, 20932.77),
('feil', 'Ferdinand', 'Ilesund', '1999-05-23', 'Konvoluttmaker', 1, 10932.77),
('hyse', 'Hylvild', 'Seeberg', '1982-01-01', 'Ørevarmer', 2, 50932.77),
('kore', 'Kornelius', 'Rennesøy', '2003-05-23', 'Gjødselsanker', 3, 13922.51),
('sige', 'Sigrun', 'Geltenes', '1990-09-09', 'Administrator', 2, 13922.51);

INSERT INTO firma.prosjekt (prosjektnavn) VALUES
('Brevbæring'),
('Hørselstest'),
('Gressvekst');

INSERT INTO firma.prosjektdeltakelse (prosjektid, ansattid, rolle, timer) VALUES
(1, 3, 'Kasserer', 424.25),
(1, 4, 'Prosjektleder', 524.25),
(1, 7, 'Instruktør', 585.76),
(2, 1, 'Prosjektleder', 670.52),
(2, 5, 'Flaggbærer', 600.02),
(2, 8, 'Kasserer', 643.12),
(2, 10, 'Kaffebærer', 997.12),
(3, 2, 'HR', 356.22),
(3, 6, 'Prosjektleder', 856.22),
(3, 9, 'Sekretær', 753.22);

ALTER TABLE firma.ansatt
ADD FOREIGN KEY (avdelingid) REFERENCES firma.avdeling (avdelingid) ON UPDATE CASCADE;

ALTER TABLE firma.avdeling
ADD FOREIGN KEY (sjef) REFERENCES firma.ansatt (ansattid) ON UPDATE CASCADE;

UPDATE firma.avdeling
SET sjef = 1
WHERE avdelingid = 1;

UPDATE firma.avdeling
SET sjef = 8
WHERE avdelingid = 2;

UPDATE firma.avdeling
SET sjef = 2
WHERE avdelingid = 3;