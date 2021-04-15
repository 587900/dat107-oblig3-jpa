package no.hvl.dat107;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class ProsjektdeltakelseDAO {

private EntityManagerFactory emf = Persistence.createEntityManagerFactory("firmaPU");
	
	public Prosjektdeltakelse finnProsjektdeltakelseMedID(int prosjektid, int ansattid) {
		
		EntityManager em = emf.createEntityManager();

		try {
			return em.find(Prosjektdeltakelse.class, new ProsjektdeltakelseID(prosjektid, ansattid));

		} catch (NoResultException e) {
			return null;
			
		}finally {
			em.close();
		}
	}

	public List<Prosjektdeltakelse> finnAlleProsjektdeltakelse() {
		
		EntityManager em = emf.createEntityManager();
		
		try {
			
			TypedQuery<Prosjektdeltakelse> query = em.createQuery ("SELECT a FROM Prosjektdeltakelse a", Prosjektdeltakelse.class);
			return query.getResultList();
		
		} finally {
			em.close();
		}
	}
	
	public void oppdaterTimer(int prosjektid, int ansattid, double timer) {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			
			Prosjektdeltakelse prosjektdeltakelse = em.find(Prosjektdeltakelse.class, new ProsjektdeltakelseID(prosjektid, ansattid));
			prosjektdeltakelse.setTimer(prosjektdeltakelse.getTimer() + timer);
						
			tx.commit();
		} catch (Throwable e) {
			e.printStackTrace();
			if (tx.isActive()) {
				tx.rollback();
			}
		} finally {
			em.close();
		}
	}

	public void lagreNyProsjektdeltakelse(Prosjektdeltakelse nyProsjektdeltakelse) {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			
			em.persist(nyProsjektdeltakelse);
			
			tx.commit();

		} catch (Throwable e) {
			e.printStackTrace();
			if (tx.isActive()) {
				tx.rollback();
			}
		} finally {
			em.close();
		}
	}
	
	public Prosjektdeltakelse getProsjektdeltakelseReference (int id) {
		EntityManager em = emf.createEntityManager();
				
		try {
			
			return em.getReference(Prosjektdeltakelse.class, id);			
		} 
		 finally {
			em.close();
		}
	}
	
	public double antallTimer(int prosjektid) {
		EntityManager em = emf.createEntityManager();
				
		try {
			
			//Fann ikke ut av dette, gjør noke nedgraderingar (manuell filtrering)
			//TypedQuery<Prosjektdeltakelse> query = em.createQuery ("SELECT a,prosjektid FROM Prosjektdeltakelse a WHERE a.prosjektid = :prosjektid", Prosjektdeltakelse.class);
			
			TypedQuery<Prosjektdeltakelse> query = em.createQuery ("SELECT a FROM Prosjektdeltakelse a", Prosjektdeltakelse.class);
			List<Prosjektdeltakelse> list = query.getResultList();
			
			double sum = 0;
			for (int i = 0; i < list.size(); ++i) {
				if (list.get(i).getProsjektid().getProsjektID() != prosjektid) continue;	//se kommentar "Fann ikke ut av dette". Filtrerer manuelt i staden for.
				sum += list.get(i).getTimer();
			}
			
			return sum;
			
		} finally {
			em.close();
		}
		
	}
}
