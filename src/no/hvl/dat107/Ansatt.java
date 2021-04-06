package no.hvl.dat107;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "ansatt", schema = "firma")
@NamedQuery(name = "hentAlleAnsatte", query ="SELECT p FROM ansatt p")

public class Ansatt {
	
	@Id private int ansattID;
	private String brukernavn;
	private String fornavn;
	private String etternavn;
	private LocalDate ansDato;
	private String stilling; // må gjøres til stillingID og til FK
	private double mndLonn;
	
	public Ansatt () {}
	
	public Ansatt (String brukernavn, String fornavn, String etternavn,
			LocalDate ansDato, String stilling, double mndLonn) {
		this.brukernavn = brukernavn;
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		this.ansDato = ansDato;
		this.stilling = stilling;
		this.mndLonn = mndLonn;
	}

	public String getBrukernavn() {
		return brukernavn;
	}

	public void setBrukernavn(String brukernavn) {
		this.brukernavn = brukernavn;
	}

	public String getFornavn() {
		return fornavn;
	}

	public void setFornavn(String fornavn) {
		this.fornavn = fornavn;
	}

	public String getEtternavn() {
		return etternavn;
	}

	public void setEtternavn(String etternavn) {
		this.etternavn = etternavn;
	}

	public LocalDate getAnsDato() {
		return ansDato;
	}

	public void setAnsDato(LocalDate ansDato) {
		this.ansDato = ansDato;
	}

	public String getStilling() {
		return stilling;
	}

	public void setStilling(String stilling) {
		this.stilling = stilling;
	}

	public double getMndLonn() {
		return mndLonn;
	}

	public void setMndLonn(double mndLonn) {
		this.mndLonn = mndLonn;
	}

	public void skrivUt() {
		System.out.println("Ansatt-ID.: " + ansattID + "\nBrukernavn: " + brukernavn +
				"\nFornavn: " + fornavn + "\nEtternavn: " + etternavn +
				"\nDato for ansettelse: " + ansDato + "\nStilling: " + stilling +
				"\nMånedslønn: " + mndLonn);
	}

}
