package no.hvl.dat107;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "prosjektdeltakelse", schema = "firma")
@IdClass(ProsjektdeltakelseID.class)

public class Prosjektdeltakelse {
	
	@Id
	private Prosjekt prosjektid;
	
	@Id
	private Ansatt ansattid;
	
	private String rolle;
	private double timer;
	
	public Prosjektdeltakelse() { }

	public Prosjektdeltakelse(Prosjekt prosjektid, Ansatt ansattid, String rolle, double timer) {
		this.prosjektid = prosjektid;
		this.ansattid = ansattid;
		this.rolle = rolle;
		this.timer = timer;
	}
	
	public Prosjekt getProsjektid() {
		return prosjektid;
	}

	public void setProsjektid(Prosjekt prosjektid) {
		this.prosjektid = prosjektid;
	}

	public Ansatt getAnsattid() {
		return ansattid;
	}

	public void setAnsattid(Ansatt ansattid) {
		this.ansattid = ansattid;
	}

	public String getRolle() {
		return rolle;
	}

	public void setRolle(String rolle) {
		this.rolle = rolle;
	}

	public double getTimer() {
		return timer;
	}

	public void setTimer(double timer) {
		this.timer = timer;
	}

	public void skrivUt() {
		System.out.println("Prosjekt-ID: " + prosjektid.getProsjektID()
		+ "\nAnsatt-ID: " + ansattid.getAnsattID()
		+ "\nRolle: " + rolle
		+ "\nArbeidstimer: " + timer);
	}
}
