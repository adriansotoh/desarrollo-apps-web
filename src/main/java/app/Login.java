package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import models.Usuario;

public class Login {

	public static void main(String[] args) {
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		// select * from tb_usuarios where idtipo = 1
		Query consulta = em.createNativeQuery("{call usp_validaAcceso(:usuario,:clave)}", Usuario.class);
		consulta.setParameter("usuario", "U0001@gmail.com");
		consulta.setParameter("clave", "10001");

		try {
			Usuario u = (Usuario) consulta.getSingleResult();
			System.out.println("Bienvenido...");
		} catch (Exception e) {
			System.err.println("usuario o clave incorrecto");
		}
		
		em.close();
	}
}
