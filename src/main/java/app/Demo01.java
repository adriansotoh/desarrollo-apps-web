package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import models.Usuario;

public class Demo01 {

	public static void main(String[] args) {
		//valores del nuevo Usuario
		Usuario u = new Usuario(11, "Juan", "Perez", "jperez@gmail.com", "root", "2000/01/15", 1, 1);
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(u);
		
		em.getTransaction().commit();
		
		em.close();
	}
}
