
package no.hvl.dat107;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner tastatur = new Scanner(System.in).useLocale(new Locale("nb")); // Norsk locale
		int input;
		AnsattDAO ansattDAO = new AnsattDAO();
		AvdelingDAO avdDAO = new AvdelingDAO();

		do {
			System.out.println("Meny:\n" + "\n(1) Søk etter ansatt på ansatt-ID"
					+ "\n(2) Søk etter ansatt på brukernavn (4 bokstaver)"
					+ "\n(3) Skriv ut alle ansatte"
					+ "\n(4) Oppdater ansatt sin stilling og/eller lønn"
					+ "\n(5) Legg inn ny ansatt"
					+ "\n(6) Søk etter avdeling på avdeling-ID"
					+ "\n(7) Skriv ut ansatt-liste og sjef på gitt avdeling"
					+ "\n(8) Oppdater avdeling for ansatt"
					+ "\n(9) Legg inn ny avdeling"
					+ "\n\n(0) Avslutt" + "\n\nTast inn tall (0-9) for å velge:");

			input = tastatur.nextInt();

			switch (input) {

			// (1) Søk etter ansatt på ansatt-ID:
			case 1:
				System.out.println("Søk etter ansatt på ansatt-ID" + "\n\nVennligst oppgi ansatt-ID:");
				int ansattID = tastatur.nextInt();
				Ansatt ansattA = ansattDAO.finnAnsattMedID(ansattID);

				System.out.println("\nAnsatt med ansatt-ID: " + ansattID + "\n");
				ansattA.skrivUt();
				break;

			// (2) Søk etter ansatt på brukernavn:
			case 2:
				System.out.println("Søk etter ansatt på brukernavn" + "\n\nVennligst oppgi brukernavn:");
				String ansBrukern = tastatur.next();
				Ansatt ansattB = ansattDAO.finnAnsattMedBrukernavn(ansBrukern);
				
				if (ansattB != null) {
					System.out.println("\nAnsatt med brukernavn: " + ansBrukern + "\n");
					ansattB.skrivUt();	
				} else System.out.println("\nIngen ansatt med brukernavn \"" + ansBrukern + "\" funnet.\n\n");
				
				break;
				

			// (3) Skriv ut alle ansatte
			case 3:
				System.out.println("Skriv ut alle ansatte:" + "\n\n");
				List<Ansatt> alleAnsatte = ansattDAO.finnAlleAnsatt();

				System.out.println("\nSkriver ut alle ansatte:");
				alleAnsatte.forEach(t -> t.skrivUt());
				break;

			// (4) Oppdater ansatt sin stilling og/eller lønn
			case 4:
				System.out.println("\nAngi ansatt-ID for å endre stilling og/eller lønn:");
				int ansID = tastatur.nextInt();
				Ansatt ansattSL = ansattDAO.finnAnsattMedID(ansID);
				System.out.println("\nAnsatt med ansatt-ID: " + ansID + "\n");
				ansattSL.skrivUt();

				System.out.println(
						"Meny:\n" + "\n(1) Endre både stilling og lønn" + "\n(2) Endre stilling" + "\n(3) Endre lønn");

				int inputSL = tastatur.nextInt();
				switch (inputSL) {

				case 1:
					System.out.println("Skriv inn ny stilling, og trykk ENTER:");
					String nyStilling1 = tastatur.next();
					ansattSL.setStilling(nyStilling1);
					ansattDAO.oppdaterAnsatt(ansattSL);

					System.out.println("Skriv inn ny månedslønn, og trykk ENTER:");
					double nyMndLonn1 = tastatur.nextDouble();
					ansattSL.setMndLonn(nyMndLonn1);
					ansattDAO.oppdaterAnsatt(ansattSL);

					System.out.println("\n\nEndret til:\n\n");
					ansattSL.skrivUt();
					break;

				case 2:
					System.out.println("Skriv inn ny stilling, og trykk ENTER:");
					String nyStilling2 = tastatur.next();
					ansattSL.setStilling(nyStilling2);
					ansattDAO.oppdaterAnsatt(ansattSL);

					System.out.println("\n\nEndret til:\n\n");
					ansattSL.skrivUt();
					break;

				case 3:
					System.out.println("Skriv inn ny månedslønn, og trykk ENTER:");
					double nyMndLonn3 = tastatur.nextDouble();
					ansattSL.setMndLonn(nyMndLonn3);
					ansattDAO.oppdaterAnsatt(ansattSL);

					System.out.println("\n\nEndret til:\n\n");
					ansattSL.skrivUt();
					break;
				}
				break;
			// (5) Legg inn ny ansatt
			case 5:
				System.out.println("Legg inn ny ansatt");
				Ansatt nyAnsatt = new Ansatt();

				System.out.println("\n\nVennligst oppgi fornavn:");
				String fNavn = tastatur.next();
				nyAnsatt.setFornavn(fNavn);

				System.out.println("Vennligst oppgi etternavn:");
				String eNavn = tastatur.next();
				nyAnsatt.setEtternavn(eNavn);
				
				System.out.println("Vennligst lag brukernavn (4 små bokstaver) for ny ansatt, og trykk ENTER:");
				String bruker = tastatur.next();
				nyAnsatt.setBrukernavn(bruker);
				
				LocalDate ansDato;
				while (true) {
					System.out.println("Vennligst oppgi ansettelsesdato (ÅÅÅÅ-MM-DD) og trykk ENTER:");
					
					String dato = tastatur.next();
					try {
						 ansDato = LocalDate.parse(dato);
						 break;
					} catch (DateTimeParseException e) {}
				}
				nyAnsatt.setAnsDato(ansDato);
				
				Avdeling avdNy;
				int sokID5;
				do {
					System.out.println("Vennligst oppgi avdeling-ID (tall), og trykk ENTER:\n");
					sokID5 = tastatur.nextInt();
					avdNy = avdDAO.getAvdelingReference(sokID5);
				} while (avdNy == null);
				nyAnsatt.setAvdeling(avdNy);
				
				System.out.println("Vennligst oppgi stilling, og trykk ENTER:");
				String stilling = tastatur.next();
				nyAnsatt.setStilling(stilling);

				System.out.println("Vennligst oppgi månedslønn, og trykk ENTER:");
				double lonn = tastatur.nextDouble();
				nyAnsatt.setMndLonn(lonn);
				
				System.out.println("Legger inn [" + fNavn + " " + eNavn + "] i databasen...");
				try {
					ansattDAO.lagreNyAnsatt(nyAnsatt);
				} catch (Throwable e) { };

				System.out.println("\n\nAnsatt lagt inn:\n\n");
				nyAnsatt.skrivUt();
				break;
				
			// (6) Søke etter avdeling på avdeling-ID
			case 6:
				System.out.println("Søk etter avdeling på avdeling-ID" + "\n\nVennligst oppgi avdeling-ID, og trykk ENTER:");
				int sokID1 = tastatur.nextInt();
				System.out.println("Søker etter avdeling-ID "+ sokID1 + "...\n");
				Avdeling avdA = avdDAO.finnAvdelingMedID(sokID1);
				System.out.println("\nAvdeling med avdeling-ID: " + sokID1 + ":\n");
				avdA.skrivUt();
				break;
			
			// (7) Skriv ut ansatt-liste og sjef på gitt avdeling
			case 7:
				System.out.println("Skriv ut ansatt-liste og sjef på gitt avdeling" + "\n\nVennligst oppgi avdeling-ID, og trykk ENTER:");
				int sokID2 = tastatur.nextInt();
				Avdeling avdB = avdDAO.finnAvdelingMedID(sokID2);
				avdB.skrivUtAnsSjef();
				break;
			
			// (8) Oppdater avdeling for ansatt
			case 8:
				System.out.println("Oppdater avdeling for ansatt" + "\n\nVennligst oppgi ansatt-ID, og trykk ENTER:");
				int sokID3 = tastatur.nextInt();
				System.out.println("\nFinner ansatt med ansatt-ID: " + sokID3 + "...");
				Ansatt ansattD = ansattDAO.finnAnsattMedID(sokID3);
				ansattD.skrivUt();
				
				Avdeling avdSok;
				int sokID4;
				do {
					System.out.println("\n\nVennligst oppgi ny avdeling-ID, og trykk ENTER:");
					sokID4 = tastatur.nextInt();
					avdSok = avdDAO.getAvdelingReference(sokID4);
				} while (avdSok == null);
				System.out.println("\nSetter ny avdeling-ID til" + sokID4 + "...");
				ansattD.setAvdeling(avdSok);
				ansattDAO.oppdaterAnsatt(ansattD);
				
				System.out.println("\n\nAnsatt oppdatert:\n\n");
				ansattD.skrivUt();
				
				break;
			// (9) Legg inn ny avdeling
			case 9:
				System.out.println("Legg inn ny avdeling\n\n");
				Avdeling nyAvd = new Avdeling();

				System.out.println("Vennligst oppgi navn på avdeling:");
				String aNavn = tastatur.next();
				nyAvd.setAvdeling(aNavn);

				Ansatt aSok;
				do {
					System.out.println("Vennligst oppgi sjef vha. ansatt-ID (1-10):");
					int aSjef = tastatur.nextInt();
//					aSok = ansattDAO.finnAnsattMedID(aSjef);
					aSok = ansattDAO.getAnsattReference(aSjef);
				} while (aSok == null);
				nyAvd.setSjef(aSok);
				
				System.out.println("Legger inn [" + aNavn + "] i databasen...");
				try {
					avdDAO.lagreNyAvdeling(nyAvd);
				} catch (Throwable e) { };

				System.out.println("\n\nAvdeling lagt inn:\n\n");
				nyAvd.skrivUt();
				break;

			}
		} while (input != 0);
		tastatur.close();
	}
}
