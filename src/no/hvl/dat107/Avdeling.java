package no.hvl.dat107;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "avdeling", schema = "firma")
//@NamedQuery(name = "finnAvdelingMedID", query ="SELECT a FROM Avdeling a")

public class Avdeling {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int avdelingid;
	private String avdeling;
	@OneToOne
    @JoinColumn(name = "sjef", referencedColumnName = "ansattid")
	private Ansatt sjef;
	@OneToMany(mappedBy = "avdelingid")
	private List<Ansatt> ansatte = new ArrayList<>();
	
	public Avdeling() {}
	
	public Avdeling(int avdelingid, String avdeling) {
		this.avdelingid = avdelingid;
		this.avdeling = avdeling;
	}
	
	public int getAvdelingID() {
		return avdelingid;
	}

	public String getAvdeling() {
		return avdeling;
	}

	public void setAvdeling(String avdeling) {
		this.avdeling = avdeling;
	}
	
	public Ansatt getSjef() {
		return sjef;
	}
	
	public void setSjef(Ansatt sjef) {
		this.sjef = sjef;
	}
	
	public void skrivUtAnsSjef() {
        skrivUt();
        ansatte.forEach(Ansatt::skrivUt);
    }
	
	public void skrivUt() {
		System.out.println("Avdeling-ID: " + avdelingid + "\nAvdeling: " + avdeling + "\nSjef: " + sjef.getFornavn() + " " + sjef.getEtternavn() + "\n\n");
	}

}
