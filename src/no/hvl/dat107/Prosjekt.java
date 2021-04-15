package no.hvl.dat107;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "prosjekt", schema = "firma")

public class Prosjekt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int prosjektid;
	private String prosjektnavn;
	
	@OneToMany(mappedBy = "prosjektid")
	private List<Prosjektdeltakelse> ansatte = new ArrayList<>();
	
	public Prosjekt() {}
	
	public Prosjekt(String prosjektnavn) {
		this.prosjektnavn = prosjektnavn;
	}
	
	public int getProsjektID() {
		return prosjektid;
	}

	public String getProsjektnavn() {
		return prosjektnavn;
	}

	public void setProsjektnavn(String prosjektnavn) {
		this.prosjektnavn = prosjektnavn;
	}
	
	public void skrivProsjektinfo() {
        skrivUt();
        ansatte.forEach(Prosjektdeltakelse::skrivUt);
    }
	
	public void skrivUt() {
		System.out.println("Prosjekt-ID: " + prosjektid + "\nProsjektnavn: " + prosjektnavn);
	}
}
