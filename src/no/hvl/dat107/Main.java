package no.hvl.dat107;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner tastatur = new Scanner(System.in).useLocale(new Locale("nb")); // Norsk locale
		String input;
		AnsattDAO ansattDAO = new AnsattDAO();
		AvdelingDAO avdDAO = new AvdelingDAO();
		ProsjektDAO prosjektDAO = new ProsjektDAO();
		ProsjektdeltakelseDAO prosjektdeltakelseDAO = new ProsjektdeltakelseDAO();

		do {
			System.out.println("Meny:\n"
					+ "\n(a) Søk etter ansatt på ansatt-ID"
					+ "\n(b) Søk etter ansatt på brukernavn (4 bokstaver)"
					+ "\n(c) Skriv ut alle ansatte"
					+ "\n(d) Oppdater ansatt sin stilling og/eller lønn"
					+ "\n(e) Legg inn ny ansatt"
					+ "\n(f) Søk etter avdeling på avdeling-ID"
					+ "\n(g) Skriv ut ansatt-liste og sjef på gitt avdeling"
					+ "\n(h) Oppdater avdeling for ansatt"
					+ "\n(i) Legg inn ny avdeling"
					+ "\n(j) Legg inn nytt prosjekt"
					+ "\n(k) Registrer prosjektdeltakelse"
					+ "\n(l) Føre timer for en ansatt på prosjekt"
					+ "\n(m) Liste ut totalt antall timer på et prosjekt vha. ID"
					+ "\n\nTast inn bokstav (a-l) for å velge, eller tast x for å avslutte:");

			input = tastatur.next();

			switch (input) {

			// (a) Søk etter ansatt på ansatt-ID:
			case "a":
				System.out.println("Søk etter ansatt på ansatt-ID" + "\n\nVennligst oppgi ansatt-ID:");
				int ansattID = tastatur.nextInt();
				Ansatt ansattA = ansattDAO.finnAnsattMedID(ansattID);

				System.out.println("\nAnsatt med ansatt-ID: " + ansattID + "\n");
				ansattA.skrivUt();
				break;

			// (b) Søk etter ansatt på brukernavn:
			case "b":
				System.out.println("Søk etter ansatt på brukernavn" + "\n\nVennligst oppgi brukernavn:");
				String ansBrukern = tastatur.next();
				Ansatt ansattB = ansattDAO.finnAnsattMedBrukernavn(ansBrukern);
				
				if (ansattB != null) {
					System.out.println("\nAnsatt med brukernavn: " + ansBrukern + "\n");
					ansattB.skrivUt();	
				} else System.out.println("\nIngen ansatt med brukernavn \"" + ansBrukern + "\" funnet.\n\n");
				
				break;
				

			// (c) Skriv ut alle ansatte
			case "c":
				System.out.println("Skriv ut alle ansatte:" + "\n\n");
				List<Ansatt> alleAnsatte = ansattDAO.finnAlleAnsatt();

				System.out.println("\nSkriver ut alle ansatte:");
				alleAnsatte.forEach(t -> t.skrivUt());
				break;

			// (d) Oppdater ansatt sin stilling og/eller lønn
			case "d":
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
			// (e) Legg inn ny ansatt
			case "e":
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
				
			// (f) Søke etter avdeling på avdeling-ID
			case "f":
				System.out.println("Søk etter avdeling på avdeling-ID" + "\n\nVennligst oppgi avdeling-ID, og trykk ENTER:");
				int sokID1 = tastatur.nextInt();
				System.out.println("Søker etter avdeling-ID "+ sokID1 + "...\n");
				Avdeling avdA = avdDAO.finnAvdelingMedID(sokID1);
				System.out.println("\nAvdeling med avdeling-ID: " + sokID1 + ":\n");
				avdA.skrivUt();
				break;
			
			// (g) Skriv ut ansatt-liste og sjef på gitt avdeling
			case "g":
				System.out.println("Skriv ut ansatt-liste og sjef på gitt avdeling" + "\n\nVennligst oppgi avdeling-ID, og trykk ENTER:");
				int sokID2 = tastatur.nextInt();
				Avdeling avdB = avdDAO.finnAvdelingMedID(sokID2);
				avdB.skrivUtAnsSjef();
				break;
			
			// (h) Oppdater avdeling for ansatt
			case "h":
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
			// (i) Legg inn ny avdeling
			case "i":
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
			
			//(j) Legg inn nytt prosjekt
			case "j":
				System.out.println("Legg inn nytt prosjekt\n\n");
				Prosjekt nyttProsjekt = new Prosjekt();

				System.out.println("Vennligst oppgi navn på prosjekt:");
				String pNavn = tastatur.next();
				nyttProsjekt.setProsjektnavn(pNavn);
				
				System.out.println("Legger inn [" + pNavn + "] i databasen...");
				try {
					prosjektDAO.lagreNyttProsjekt(nyttProsjekt);
				} catch (Throwable e) { };

				System.out.println("\n\nProsjekt lagt inn:\n\n");
				nyttProsjekt.skrivUt();
				break;
			
			//(k) Registrer prosjektdeltakelse
			case "k":
				System.out.println("Registrer prosjektdeltakelse\n\n");
				Prosjektdeltakelse nyProsjektdeltakelse = new Prosjektdeltakelse();

				Prosjekt pSok;
				do {
					System.out.println("Angi prosjekt vha. prosjekt-ID (1-3), og trykk ENTER:");
					int prosjektID = tastatur.nextInt();
					pSok = prosjektDAO.getProsjektReference(prosjektID);
				} while (pSok == null);
				nyProsjektdeltakelse.setProsjektid(pSok);
				
				Ansatt aSok2;
				do {
					System.out.println("Angi ansatt vha. ansatt-ID (1-10), og trykk ENTER:");
					int ansattID2 = tastatur.nextInt();
					aSok2 = ansattDAO.getAnsattReference(ansattID2);
				} while (aSok2 == null);
				nyProsjektdeltakelse.setAnsattid(aSok2);
				
				System.out.println("Angi rolle for ansatt-ID: " + aSok2.getAnsattID() + ":");
				String rolle = tastatur.next();
				nyProsjektdeltakelse.setRolle(rolle);
				
				System.out.println("Oppgi antall timer jobbet for ansatt-ID: " + aSok2.getAnsattID() + ":");
				int timer = tastatur.nextInt();
				nyProsjektdeltakelse.setTimer(timer);
				
				System.out.println("Legger inn prosjektdeltakelse i databasen...");
				try {
					prosjektdeltakelseDAO.lagreNyProsjektdeltakelse(nyProsjektdeltakelse);
				} catch (Throwable e) { };

				System.out.println("\n\nProsjektdeltakelse lagt inn:\n\n");
				nyProsjektdeltakelse.skrivUt();
				break;
			
			//(l) Føre timer for en ansatt på prosjekt
			case "l":
				System.out.println("Føre timer for en ansatt på prosjekt"
				+ "\n\nVennligst oppgi prosjekt-ID og trykk ENTER:");
				int sokID6 = tastatur.nextInt();
				System.out.println("Vennligst oppgi ansatt-id og trykk ENTER:");
				int sokID62 = tastatur.nextInt();
				System.out.println("\nFinner prosjektdeltakelse med ID: (" + sokID6 + "," + sokID62 + ")...");
				Prosjektdeltakelse prosjektdeltakelse = prosjektdeltakelseDAO.finnProsjektdeltakelseMedID(sokID6, sokID62);
				prosjektdeltakelse.skrivUt();

				System.out.println("\n\nVennligst oppgi antall timer å legge til, og trykk ENTER:");
				double leggTil = tastatur.nextDouble();
				System.out.println("\n\nLegger til " + leggTil + " timer...");
				prosjektdeltakelse.setTimer(prosjektdeltakelse.getTimer() + leggTil);
				prosjektdeltakelseDAO.oppdaterTimer(sokID6, sokID62, leggTil);;
				
				System.out.println("\n\nProsjektdeltakelse oppdatert:\n\n");
				prosjektdeltakelse.skrivUt();
				break;
				
			case "m":
				//fikk ikke tid til � skrive ut info om prosjekt, liste deltagere med roller og timer, men vi f�r skrive ut timar pr. prosjekt:
				System.out.println("Liste ut antall timar p� eit prosjekt");
				System.out.println("Vennligst oppgi prosjekt-id og trykk ENTER:");
				int sokID7 = tastatur.nextInt();
				
				System.out.println("Antall timer for prosjekt med id: " + sokID7 + " = " + prosjektdeltakelseDAO.antallTimer(sokID7));
				break;				
			case "x":
				System.out.println("Takk for i dag!");
				break;
			
			default:
				System.out.println("Ugyldig bokstav: " + input);
				break;
			}
		}
		while (!input.equals("x"));
		tastatur.close();
	}
}