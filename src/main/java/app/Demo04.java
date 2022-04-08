package app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import models.Usuario;

public class Demo04 {

	public static void main(String[] args) {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		
		List<Usuario> usuarios = em.createQuery("select a from Usuario a", Usuario.class).getResultList();
		
		for(Usuario u : usuarios) {
			System.out.println(u);
		}
		
		em.close();
	}
}
