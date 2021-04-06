package no.hvl.dat107;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 * @author lph-lokal
 *
 */
public class AnsattDAO {
	
	private EntityManagerFactory emf 
			= Persistence.createEntityManagerFactory("firmaPU");
	
	/**
	 * @param pk
	 * @return
	 */
	public Ansatt finnAnsattMedID(int id) {
		
		EntityManager em = emf.createEntityManager();

		try {
			return em.find(Ansatt.class, id);

		} finally {
			em.close();
		}
	}

	/**
	 * @return
	 */
	public List<Ansatt> finnAlleAnsatt() {
		
		EntityManager em = emf.createEntityManager();
		
		try {
			TypedQuery<Ansatt> query = em.createQuery ("SELECT t FROM Ansatt t", Ansatt.class);
			return query.getResultList(); //returnerer liste av ansatt-objekter
		
		} finally {
			em.close();
		}
	}

	/**
	 * @param tekst
	 * @return
	 */
	public Ansatt finnAnsattMedTekst(String tekst) {
		
		EntityManager em = emf.createEntityManager();
		
		try { // *START* kode skrevet i forelesning
			TypedQuery<Ansatt> query = em.createQuery(
					"SELECT t FROM Ansatt t WHERE t.tekst = :tekst", Ansatt.class); // :tekst er parameternavn
			query.setParameter("tekst", tekst);
			return query.getSingleResult(); // NB! Unntak dersom 0 eller flere
			// *SLUTT* kode skrevet i forelesning
		} finally {
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
}
