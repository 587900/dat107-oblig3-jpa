package no.hvl.dat107;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ProsjektDAO {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("firmaPU");

	public Prosjekt finnProsjektMedID(int id) {
		EntityManager em = emf.createEntityManager();

		try {
			return em.find(Prosjekt.class, id);
			

		} finally {
			em.close();
		}
	}
	
	public void lagreNyttProsjekt(Prosjekt nyttProsjekt) {

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();

			em.persist(nyttProsjekt);

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
	
	public Prosjekt getProsjektReference (int id) {
		EntityManager em = emf.createEntityManager();
				
		try {
			
			return em.getReference(Prosjekt.class, id);			
		} 
		 finally {
			em.close();
		}
	}

}
