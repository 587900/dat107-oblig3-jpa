package no.hvl.dat107;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class AvdelingDAO {
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("firmaPU");
	
	public Avdeling finnAvdelingMedID(int id) {
		EntityManager em = emf.createEntityManager();

		try {
			return em.find(Avdeling.class, id);
			

		} finally {
			em.close();
		}
	}
	
	public void lagreNyAvdeling(Avdeling nyAvdeling) {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			
			em.persist(nyAvdeling);
			
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
	
	public Avdeling getAvdelingReference (int id) {
		EntityManager em = emf.createEntityManager();
				
		try {
			
			return em.getReference(Avdeling.class, id);			
		} 
		 finally {
			em.close();
		}
	}

}
