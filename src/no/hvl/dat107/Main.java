
package no.hvl.dat107;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class Main {

	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		/*
		 * Ting å prøve: 
		 * a) Hente ut todo med pk=2 
		 * b) Hente ut alle todos 
		 * c.i)   Hente ut SINGLE todo med tekst="Handle mat" 
		 * c.ii)  Hente ut SINGLE todo med tekst="Vaske bilen" (som ikke finnes) 
		 * c.iii) Hente ut LISTE av todos med tekst="Handle mat" 
		 * c.iv)  Hente ut LISTE av todos med tekst="Vaske bilen" (som ikke finnes) 
		 * d) Legge til en ny todo med pk=4 
		 * e) Slette todo med pk=4 
		 * f) Endre tekst på todo med pk=3
		 * g) Endre tekst på todo med pk=3, alternativ måte
		 */

		// Hjelpeklasse for å snakke med databasen
		// DAO er en forkortelse for Data Access Object, og er et vanlig navn på slike
		AnsattDAO ansattDAO = new AnsattDAO();

		// a)
		Ansatt ansattA = ansattDAO.finnAnsattMedID(2);
		
		System.out.println("\na) Hente ut ansatt med ID = 2");
		System.out.println("   " + ansattA);

		
		// b)
		List<Ansatt> alleAnsatte = ansattDAO.finnAlleAnsatt();
		
		System.out.println("\nb) Hente ut alle ansatte");
		alleAnsatte.forEach(t -> System.out.println("   " + t));

		
//		// c)
//		Todo todoci = todoDAO.finnTodosMedTekst("Handle mat");
//		System.out.println(todoci);
//				
//		Todo todocii = todoDAO.finnTodosMedTekst("Vaske bilen");
//		System.out.println(todocii);
//		
//		List<Todo> todosciii = todoDAO.finnTodosMedTekst("Handle mat");
//		System.out.println(todosciii);
//
//		List<Todo> todosciv = todoDAO.finnTodosMedTekst("Vaske bilen");
//		System.out.println(todosciv);
		
		
		// d)
		System.out.println("\nd) Legge til en ny ansatt");

		Ansatt nyAnsatt = new Ansatt("Bjørnefar", "Bjørn", "Bjørnsen", LocalDate.of(2021, 02, 25),
				"Bjørnevasker" + 68943,98);  // Hva raden skal inneholde
		ansattDAO.lagreNyAnsatt(nyAnsatt);   // Lagrer den nye raden
		
		Ansatt finnAns = ansattDAO.finnAnsattMedTekst("Bjørn");
		System.out.println("   Henter ut todo med pk=4");
		System.out.println("   " + ansattDAO);
	
		pauseOgSjekkDatabasen("\nSjekk at vi har fått en ny rad med id=4 og tekst=Gjøre lekser.");

		
		// e)
//		System.out.println("\ne) Slette todo med pk=4");
//
//		todoDAO.slettTodoMedPk(4);
//
//		Todo todoe = todoDAO.finnTodoMedPk(4);
//		System.out.println("   Henter ut todo med pk=4");
//		System.out.println("   " + todoe);
//
//		pauseOgSjekkDatabasen("\nSjekk at rad med id=4 er slettet igjen.");

		
		// f)
//		System.out.println("\nf) Endre tekst på todo med pk=3");
//
//		Todo todof1 = todoDAO.finnTodoMedPk(3);
//		todof1.setTekst("Endret tekst " + LocalTime.now());
//		todoDAO.oppdaterTodo(todof1);
//
//		Todo todof2 = todoDAO.finnTodoMedPk(3);
//		System.out.println("   Henter ut todo med pk=3");
//		System.out.println("   " + todof2);
//
//		pauseOgSjekkDatabasen("\nSjekk at rad med id=3 har fått oppdatert klokkeslett i tekst.");

		
		// g)
//		System.out.println("\ng) Endre tekst på todo med pk=3, alternativ måte");
//
//		todoDAO.oppdaterTekst(3, "Jobbe med DAT107-oblig");
//
//		Todo todog = todoDAO.finnTodoMedPk(3);
//		System.out.println("   Henter ut todo med pk=3");
//		System.out.println("   " + todog);
//
//		pauseOgSjekkDatabasen("\nSjekk at rad med id=3 har fått oppdatert tekst til 'Jobbe med DAT107-oblig'.");
//
//		System.out.println("\nProgrammet er ferdig! :)");
	}
//
	private static void pauseOgSjekkDatabasen(String prompt) {
		System.out.println(prompt);
		System.out.println("Trykk \"ENTER\" for å fortsette ...");
		scanner.nextLine();
	}
}
