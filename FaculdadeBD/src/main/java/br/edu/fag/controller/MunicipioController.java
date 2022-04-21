package br.edu.fag.controller;

import br.edu.fag.modelo.Municipio;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.swing.*;
import java.util.List;

public class MunicipioController {
    private  EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("BancoPU");
    private  EntityManager entityManager =
            entityManagerFactory.createEntityManager();

    public void delete() {
        String sg = JOptionPane.showInputDialog("Informe a codigo do Municipio ");
        Municipio Municipio = null;
        try {
            Municipio = (Municipio) entityManager.createNativeQuery(
                            "select * from Municipio" +
                                    " where codigo = :sg ", Municipio.class)
                    .setMaxResults(1)
                    .setParameter("sg", sg)
                    .getSingleResult();
        }catch (NoResultException nre){
            JOptionPane.showMessageDialog(null,
                    "Municipio  (" + sg + ") não encontrado ");
        }
        if(0 == JOptionPane.showConfirmDialog(null,
                "Deseja Excluir "+Municipio.toString())){
            entityManager.getTransaction().begin();
            entityManager.remove(Municipio);
            entityManager.getTransaction().commit();
            entityManager.close();
        }
    }

    public void update() {
        String sg = JOptionPane.showInputDialog("Informe a codigo do Municipio ");

        Municipio Municipio = null;
        try {
            Municipio = (Municipio) entityManager.createNativeQuery(
                            "select * from Municipio" +
                                    " where codigo = :codigo ", Municipio.class)
                    .setMaxResults(1)
                    .setParameter("sg", sg)
                    .getSingleResult();
        }catch (NoResultException nre){
            String cadastrar = JOptionPane.showInputDialog(
                    "Municipio  (" + sg + ") não encontrado  \n" +
                            "Digite S para cadastrar um novo Municipio ");
            if("S".equals(cadastrar)){
                inserir();
            }
        }
        if (Municipio == null) {
            String cadastrar = JOptionPane.showInputDialog(
                    "Municipio  (" + sg + ") não encontrado  \n" +
                            "Digite S para cadastrar um novo Municipio ");
            if("S".equals(cadastrar)){
                inserir();
            }
        }
        Municipio.setCodigo(JOptionPane.showInputDialog("Informe o novo codigo"));
        Municipio.setNome(JOptionPane.showInputDialog("Informe o novo nome"));
        entityManager.getTransaction().begin();
        entityManager.merge(Municipio);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Municipio find() {
        entityManager.getTransaction();
        long id = Long.valueOf(JOptionPane.showInputDialog("Informe o id"));
        Municipio resultado = entityManager.find(Municipio.class, id);
        if(resultado == null){
            JOptionPane.showMessageDialog(null,
                    "Id não encontrado");
        }
        entityManager.close();
        return  resultado;
    }

    public  void inserir() {
        Municipio municipio = new Municipio();
        municipio.setCodigo(JOptionPane.showInputDialog("Informe Código"));
        municipio.setNome(JOptionPane.showInputDialog("Informe Nome"));
        EstadoController
        municipio.setE
//
        entityManager.getTransaction().begin();
        entityManager.persist(municipio);
        entityManager.getTransaction().commit();

    }

    public  List<Municipio> listMunicipio(){
        entityManager.getTransaction().begin();
        List<Municipio> retorno =
                entityManager.createNativeQuery("select * from Municipio ",
                                Municipio.class)
                        .getResultList();
        entityManager.close();
        return retorno;
    }
}
