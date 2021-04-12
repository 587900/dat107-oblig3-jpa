
package no.hvl.dat107;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class Main {

	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		System.out.println("Meny:\n"
				+ "\n(1) Søk etter ansatt på ansatt-ID"
				+ "\n(2) Søk etter ansatt på brukernavn (4 bokstaver)"
				+ "\n(3) Skriv ut alle ansatte"
				+ "\n(4) Oppdater ansatt sin stilling og/eller lønn"
				+ "\n(5) Legg inn ny ansatt"
				+ "\n\nTast inn tall (1-5) for å velge:");
		
		AnsattDAO ansattDAO = new AnsattDAO();

		//TODO Legge inn case og scanner
		
		
		// (1) Søk etter ansatt på ansatt-ID:
		int ansattID = 1;
		Ansatt ansattA = ansattDAO.finnAnsattMedID(ansattID);
		
		System.out.println("\nAnsatt med ansatt-ID: " + ansattID + "\n");
		ansattA.skrivUt();
		
		
		// (2) Søk etter ansatt på brukernavn:
		String ansBrukern = "larb";
		Ansatt ansattB = ansattDAO.finnAnsattMedBrukernavn(ansBrukern);
		
		System.out.println("\nAnsatt med brukernavn: " + ansBrukern + "\n");
		ansattB.skrivUt();
		
		
		// (3) Skriv ut alle ansatte
		List<Ansatt> alleAnsatte = ansattDAO.finnAlleAnsatt();
		
		System.out.println("\nSkriver ut alle ansatte:");
		alleAnsatte.forEach(t -> System.out.println("   " + t));
		
		// (4) Oppdater ansatt sin stilling og/eller lønn
		System.out.println("\nAngi ansatt-ID for å endre stilling og/eller lønn:");

		//TODO Input fra bruker + case 1 og 2
		
		Ansatt ansattf1 = ansattDAO.finnAnsattMedID(3); //Husk å endre til ID fra bruker
		
		//Endre stilling
		ansattf1.setStilling("");
		ansattDAO.oppdaterAnsatt(ansattf1);

		Todo todof2 = todoDAO.finnTodoMedPk(3);
		System.out.println("   Henter ut todo med pk=3");
		System.out.println("   " + todof2);
		
		// (5) Legg inn ny ansatt
//		Ansatt nyAnsatt = new Ansatt("BrNv", "ForN", "EtterN", (1989, 12, 12), "Stilling", 24000.00);
		
		
	}

	private static void tilbakeTilMeny(String prompt) {
		System.out.println(prompt);
		System.out.println("Trykk \"ENTER\" for å returnere til hovedmeny");
		scanner.nextLine();
	}
}
