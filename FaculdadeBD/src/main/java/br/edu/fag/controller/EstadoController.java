package br.edu.fag.controller;

import br.edu.fag.modelo.Estado;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.swing.*;
import java.util.List;

public class EstadoController {
    private  EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("BancoPU");
    private  EntityManager entityManager =
            entityManagerFactory.createEntityManager();

    public void delete() {
        String sg = JOptionPane.showInputDialog("Informe a sigla do estado ");
        Estado estado = null;
        try {
            estado = (Estado) entityManager.createNativeQuery(
                            "select * from Estado" +
                                    " where sigla = :sg ", Estado.class)
                    .setMaxResults(1)
                    .setParameter("sg", sg)
                    .getSingleResult();
        }catch (NoResultException nre){
            JOptionPane.showMessageDialog(null,
                    "Estado  (" + sg + ") não encontrado ");
        }
        if(0 == JOptionPane.showConfirmDialog(null,
                "Deseja Excluir "+estado.toString())){
            entityManager.getTransaction().begin();
            entityManager.remove(estado);
            entityManager.getTransaction().commit();
            entityManager.close();
        }
    }

    public void update() {
        String sg = JOptionPane.showInputDialog("Informe a sigla do estado ");

        Estado estado = null;
        try {
            estado = (Estado) entityManager.createNativeQuery(
                            "select * from Estado" +
                                    " where sigla = :sg ", Estado.class)
                    .setMaxResults(1)
                    .setParameter("sg", sg)
                    .getSingleResult();
        }catch (NoResultException nre){
            String cadastrar = JOptionPane.showInputDialog(
                    "Estado  (" + sg + ") não encontrado  \n" +
                            "Digite S para cadastrar um novo estado ");
            if("S".equals(cadastrar)){
                inserir();
            }
        }
        if (estado == null) {
            String cadastrar = JOptionPane.showInputDialog(
                    "Estado  (" + sg + ") não encontrado  \n" +
                            "Digite S para cadastrar um novo estado ");
            if("S".equals(cadastrar)){
                inserir();
            }
        }
        estado.setCodigo(JOptionPane.showInputDialog("Informe o novo codigo"));
        estado.setNome(JOptionPane.showInputDialog("Informe o novo nome"));
        entityManager.getTransaction().begin();
        entityManager.merge(estado);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Estado find() {
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

    public  void inserir() {
        Estado estado = new Estado();
        estado.setCodigo(JOptionPane.showInputDialog("Informe Código"));
        estado.setSigla(JOptionPane.showInputDialog("Informe Sigla"));
        estado.setNome(JOptionPane.showInputDialog("Informe Nome"));
//
        entityManager.getTransaction().begin();
        entityManager.persist(estado);
        entityManager.getTransaction().commit();

    }

    public  List<Estado> listEstado(){
        entityManager.getTransaction().begin();
        List<Estado> retorno =
                entityManager.createNativeQuery("select * from Estado ",
                                Estado.class)
                        .getResultList();
        entityManager.close();
        return retorno;
    }
}
