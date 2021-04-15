package no.hvl.dat107;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class AnsattDAO {
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("firmaPU");
	
	public Ansatt finnAnsattMedID(int id) {
		
		EntityManager em = emf.createEntityManager();

		try {
			return em.find(Ansatt.class, id);

		} catch (NoResultException e) {
			return null;
			
		}finally {
			em.close();
		}
	}

	public List<Ansatt> finnAlleAnsatt() {
		
		EntityManager em = emf.createEntityManager();
		
		try {
			
			TypedQuery<Ansatt> query = em.createQuery ("SELECT a FROM Ansatt a", Ansatt.class);
			return query.getResultList(); //returnerer liste av ansatt-objekter
		
		} finally {
			em.close();
		}
	}

	/**
	 * @param tekst
	 * @return
	 */
	public Ansatt finnAnsattMedBrukernavn(String tekst) {
		
		EntityManager em = emf.createEntityManager();
		
		try {
			TypedQuery<Ansatt> query = em.createQuery(
					"SELECT a FROM Ansatt a WHERE a.brukernavn = :tekst", Ansatt.class); // :tekst er parameternavn
			query.setParameter("tekst", tekst);
			return query.getSingleResult();
		} catch (NoResultException e) { return null; } 
		finally {
			em.close();
		}
	}

	/**
	 * @param nyAnsatt
	 */
	public void lagreNyAnsatt(Ansatt nyAnsatt) {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			
			em.persist(nyAnsatt); //lagrer en ny rad i databasen
			
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

	/**
	 * @param pk
	 */
	public void slettAnsattMedID(int id) {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			
			Ansatt ansatt = em.find(Ansatt.class, id);
			em.remove(ansatt);
			
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

	/**
	 * @param todo
	 */
	public void oppdaterAnsatt(Ansatt ansatt) {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			
			em.merge(ansatt);
			
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

	public void oppdaterStilling(int id, String nyStilling) {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			
			Ansatt ansatt = em.find(Ansatt.class, id);
			ansatt.setStilling(nyStilling);
						
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
	
	public Ansatt getAnsattReference (int id) {
		EntityManager em = emf.createEntityManager();
				
		try {
			
			return em.getReference(Ansatt.class, id);			
		} 
		 finally {
			em.close();
		}
	}
}
