package br.edu.fag.teste;

import br.edu.fag.modelo.Estado;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    private static EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("BancoPU");
    private static EntityManager entityManager =
            entityManagerFactory.createEntityManager();


    public static void main(String[] args) {
        System.out.println("rodou");
		
		
		// FIND
//		Estado estado = entityManager.find(Estado.class, 1);
//		System.out.println("Nome do estado:" + estado.getNome());
		
		// INSERT
		Estado estado = new Estado();
        estado.setCodigo("41");
        estado.setSigla("PR");
        estado.setNome("Parana");
//		
		entityManager.getTransaction().begin();
		entityManager.persist(estado);
        entityManager.getTransaction().commit();
		
		// DELETE
//		Estado estado = entityManager.find(Estado.class, 2);
//		entityManager.getTransaction().begin();
//		entityManager.remove(estado);
//		entityManager.getTransaction().commit();
		
		// MODIFY
//		Estado estado = new Estado();
//		estado.setId(1);
//		
		
//		entityManager.getTransaction().begin();
//		entityManager.merge(estado);
//		entityManager.getTransaction().commit();

		entityManager.close();
		entityManagerFactory.close();
    }
}
