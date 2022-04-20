package br.edu.fag.teste;

import br.edu.fag.modelo.Estado;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.*;
import java.util.List;

public class Main {
    private static EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("BancoPU");
    private static EntityManager entityManager =
            entityManagerFactory.createEntityManager();


    public static void main(String[] args) {
        System.out.println("rodou");

 String opc =        JOptionPane.showInputDialog(
                        "1 - listar \n " +
                        "2 - Inserir \n " +
                        "3 - Find por id " +
                        "4 - Update ");
		switch (opc){
            case "1":
                List<Estado> listEstado = listEstado();
                for (Estado e: listEstado){
                    JOptionPane.showMessageDialog(null,
                                                     e.toString());
                }
                break;
            case "2":
                inserir();
                break;
            case "3":
                JOptionPane.showMessageDialog(null, find());
                break;
            case "4":
                update();
                break;

        }

        // FIND
//		Estado estado = entityManager.find(Estado.class, 1);
//		System.out.println("Nome do estado:" + estado.getNome());
		


		
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

    private static void update() {
        String sg = JOptionPane.showInputDialog("Informe a sigla do estado ");
        entityManager.getTransaction();
        entityManager.createNativeQuery("select * from Estado" +
                " where sigla = :sg ").setParameter("sg", sg)
                .getSingleResult();

    }

    private static Estado find() {
        entityManager.getTransaction();
        long id = Long.valueOf(JOptionPane.showInputDialog("Informe o id"));
        Estado resultado = entityManager.find(Estado.class, id);
        if(resultado == null){
            JOptionPane.showMessageDialog(null,
                    "Id não encontrado");
        }
        entityManager.close();
        return  resultado;
    }

    private static void inserir() {
        Estado estado = new Estado();
        estado.setCodigo(JOptionPane.showInputDialog("Informe Código"));
        estado.setSigla(JOptionPane.showInputDialog("Informe Sigla"));
        estado.setNome(JOptionPane.showInputDialog("Informe Nome"));
//
        entityManager.getTransaction().begin();
        entityManager.persist(estado);
        entityManager.getTransaction().commit();

    }

    public static List<Estado> listEstado(){
        entityManager.getTransaction().begin();
        List<Estado> retorno =
        entityManager.createNativeQuery("select * from Estado ",
                                                           Estado.class)
                .getResultList();
        entityManager.close();
        return retorno;
    }
}
