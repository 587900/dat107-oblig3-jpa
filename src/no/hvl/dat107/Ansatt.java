package no.hvl.dat107;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ansatt", schema = "firma")
@NamedQuery(name = "finnAlleAnsatt", query ="SELECT a FROM Ansatt a")

public class Ansatt {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ansattid;
	
	private String brukernavn;
	private String fornavn;
	private String etternavn;
	private LocalDate ansDato;
	private String stilling;
	
	@ManyToOne
    @JoinColumn(name = "avdelingid")
	private Avdeling avdelingid;
	private double mndLonn;
	
	public Ansatt () {}
	
	public Ansatt (String brukernavn, String fornavn, String etternavn,
			LocalDate ansDato, String stilling, Avdeling avdelingID, double mndLonn) {
		this.brukernavn = brukernavn;
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		this.ansDato = ansDato;
		this.stilling = stilling;
		this.avdelingid = avdelingID;
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

	public Avdeling getAvdeling() {
		return avdelingid;
	}
	
	public void setAvdeling(Avdeling avdelingid) {
		this.avdelingid = avdelingid;
	}
	
	public double getMndLonn() {
		return mndLonn;
	}

	public void setMndLonn(double mndLonn) {
		this.mndLonn = mndLonn;
	}

	public void skrivUt() {
		System.out.println("Ansatt-ID: " + ansattid + "\nBrukernavn: " + brukernavn +
				"\nFornavn: " + fornavn + "\nEtternavn: " + etternavn +
				"\nDato for ansettelse: " + ansDato + "\nStilling: " + stilling + "\nAvdeling: "
				+ avdelingid.getAvdeling() + "(ID: " + avdelingid.getAvdelingID() + ")"
				+ "\nMånedslønn: " + mndLonn + "\n\n");
	}

}
