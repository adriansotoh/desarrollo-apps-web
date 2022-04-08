package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import models.Usuario;

public class Demo03 {

	public static void main(String[] args) {

		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		
		em.getTransaction().begin();
		
		Usuario u = new Usuario();
		
		u = em.find(Usuario.class, 3);
		
		if(u == null) {
			System.out.println("Usuario o codigo no existe");
		} else {
			em.remove(u);
		}
		
		em.getTransaction().commit();
		
		em.close();
	}
}
