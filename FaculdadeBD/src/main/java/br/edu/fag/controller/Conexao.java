package br.edu.fag.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Conexao {
    private static EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("BancoPU");
    private static EntityManager entityManager;

    public static EntityManager getConexao(){

        if(entityManager == null){
            entityManager =
                    entityManagerFactory.createEntityManager();
        }
        if(!entityManager.getTransaction().isActive()){

            entityManager.getTransaction().begin();
        }
        return entityManager;
    }

}
