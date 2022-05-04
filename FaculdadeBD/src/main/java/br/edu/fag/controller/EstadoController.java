package br.edu.fag.controller;

import br.edu.fag.modelo.Estado;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.swing.*;
import java.util.List;

public class EstadoController {


    public void delete() {
        String sg = JOptionPane.showInputDialog("Informe a sigla do estado ");
        Estado estado = null;
        try {
            estado = (Estado) Conexao.getConexao().createNativeQuery(
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

            Conexao.getConexao().remove(estado);
            Conexao.getConexao().getTransaction().commit();
        }
    }

    public void update() {
        String sg = JOptionPane.showInputDialog("Informe a sigla do estado ");

        Estado estado = null;
        try {
            estado = (Estado) Conexao.getConexao().createNativeQuery(
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

        Conexao.getConexao().merge(estado);
        Conexao.getConexao().getTransaction().commit();

    }

    public Estado findPorSigla(String sigla){
        try {
            return  (Estado) Conexao.getConexao().createNativeQuery(
                            "select * from Estado" +
                                    " where sigla = :sg ",
                            Estado.class)
                    .setMaxResults(1)
                    .setParameter("sg", sigla)
                    .getSingleResult();
        }catch (NoResultException nre){
            JOptionPane.showMessageDialog(null,
                    "Estado  (" + sigla + ") não encontrado ");
        }
        return  null;
    }

    public Estado find() {
        Conexao.getConexao().getTransaction();
        long id = Long.valueOf(JOptionPane.showInputDialog("Informe o id"));
        Estado resultado = Conexao.getConexao().find(Estado.class, id);
        if(resultado == null){
            JOptionPane.showMessageDialog(null,
                    "Id não encontrado");
        }

        return  resultado;
    }

    public  void inserir() {
        Estado estado = new Estado();
        estado.setCodigo(JOptionPane.showInputDialog("Informe Código"));
        estado.setSigla(JOptionPane.showInputDialog("Informe Sigla"));
        estado.setNome(JOptionPane.showInputDialog("Informe Nome"));
//

        Conexao.getConexao().persist(estado);
        Conexao.getConexao().getTransaction().commit();

    }

    public  List<Estado> listEstado(){

        List<Estado> retorno =
                Conexao.getConexao().createNativeQuery("select * from Estado ",
                                Estado.class)
                        .getResultList();
        return retorno;
    }
}
