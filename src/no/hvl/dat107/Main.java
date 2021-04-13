
package no.hvl.dat107;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner tastatur = new Scanner(System.in).useLocale(new Locale("nb")); // Norsk locale
		int input;

		do {
			System.out.println("Meny:\n" + "\n(1) Søk etter ansatt på ansatt-ID"
					+ "\n(2) Søk etter ansatt på brukernavn (4 bokstaver)" + "\n(3) Skriv ut alle ansatte"
					+ "\n(4) Oppdater ansatt sin stilling og/eller lønn" + "\n(5) Legg inn ny ansatt"
					+ "\n\n(0) Avslutt" + "\n\nTast inn tall (1-5) for å velge:");

			AnsattDAO ansattDAO = new AnsattDAO();
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
				Ansatt nyAnsatt = new Ansatt();

				System.out.println("Vennligst oppgi fornavn:");
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
					System.out.println("Vennligst oppgi ansettelsesdato (ÅÅÅÅ-MM-DD):");
					
					String dato = tastatur.next();
					try {
						 ansDato = LocalDate.parse(dato);
						 break;
					} catch (DateTimeParseException e) {}
				}
				nyAnsatt.setAnsDato(ansDato);
				
				System.out.println("Vennligst oppgi stilling:");
				String stilling = tastatur.next();
				nyAnsatt.setStilling(stilling);

				System.out.println("Vennligst oppgi månedslønn:");
				double lonn = tastatur.nextDouble();
				nyAnsatt.setMndLonn(lonn);

				System.out.println("\n\nAnsatt lagt inn:\n\n");
				nyAnsatt.skrivUt();
			}

		} while (input != 0);
		tastatur.close();
	}

}
