package no.hvl.dat107;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Embeddable
public class ProsjektdeltakelseID implements Serializable {
	
	@Id
	private int prosjektid;
	
	@Id
	private int ansattid;
	
	public ProsjektdeltakelseID() {}

	public ProsjektdeltakelseID(int prosjektid, int ansattid) {
		this.prosjektid = prosjektid;
		this.ansattid = ansattid;
	}

	public int getProsjektid() {
		return prosjektid;
	}

	public void setProsjektid(int prosjektid) {
		this.prosjektid = prosjektid;
	}

	public int getAnsattid() {
		return ansattid;
	}

	public void setAnsattid(int ansattid) {
		this.ansattid = ansattid;
	}
	
	@Override
	public boolean equals(Object object) {
		if (!(object instanceof ProsjektdeltakelseID)) return false;
		ProsjektdeltakelseID o = (ProsjektdeltakelseID) object;
		if (ansattid != o.getAnsattid()) return false;
		if (prosjektid != o.getProsjektid()) return false;
		return true;
	}

}
