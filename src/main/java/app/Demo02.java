package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import models.Usuario;

public class Demo02 {
	
	public static void main(String[] args) {
		Usuario u = new Usuario(3, "Carla", "Toro", "U002@gmail.com", "10002", "2022-03-24", 2, 1);
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		
		em.getTransaction().begin();
		em.merge(u);
		
		em.getTransaction().commit();
		
		em.close();
	}

}
