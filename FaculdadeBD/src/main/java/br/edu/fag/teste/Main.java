package br.edu.fag.teste;

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
    }
}
