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
	
	public Prosjektdeltakelse finnProsjektdeltakelseMedID(int id) {
		
		EntityManager em = emf.createEntityManager();

		try {
			return em.find(Prosjektdeltakelse.class, id);

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
	
	public void oppdaterTimer(int id, double timer) {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			
			Prosjektdeltakelse prosjektdeltakelse = em.find(Prosjektdeltakelse.class, id);
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
}
