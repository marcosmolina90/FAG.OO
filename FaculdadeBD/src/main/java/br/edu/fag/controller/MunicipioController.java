package br.edu.fag.controller;

import br.edu.fag.modelo.Municipio;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.swing.*;
import java.util.List;

public class MunicipioController {
    

    public void delete() {
        String sg = JOptionPane.showInputDialog("Informe a codigo do Municipio ");
        Municipio Municipio = null;
        try {
            Municipio = (Municipio)  Conexao.getConexao().createNativeQuery(
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

            Conexao.getConexao().remove(Municipio);
            Conexao.getConexao().getTransaction().commit();
        }
    }

    public void update() {
        String sg = JOptionPane.showInputDialog("Informe a codigo do Municipio ");

        Municipio Municipio = null;
        try {
            Municipio = (Municipio)  Conexao.getConexao().createNativeQuery(
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

        Conexao.getConexao().merge(Municipio);
        Conexao.getConexao().getTransaction().commit();
        
    }

    public Municipio find() {
        Conexao.getConexao().getTransaction();
        long id = Long.valueOf(JOptionPane.showInputDialog("Informe o id"));
        Municipio resultado =  Conexao.getConexao().find(Municipio.class, id);
        if(resultado == null){
            JOptionPane.showMessageDialog(null,
                    "Id não encontrado");
        }
        return  resultado;
    }

    public  Municipio inserir(Municipio municipio){
       
        municipio =  Conexao.getConexao().merge(municipio);
        Conexao.getConexao().getTransaction().commit();
        return municipio;
    }

    public  void inserir() {
        Municipio municipio = new Municipio();
        municipio.setCodigo(JOptionPane.showInputDialog("Informe Código"));
        municipio.setNome(JOptionPane.showInputDialog("Informe Nome"));
        EstadoController estadoController = new EstadoController();
        municipio.setEstado(
                estadoController.findPorSigla(
                        JOptionPane.showInputDialog(
                                "Informe a sigla do estado ")
                )
        );
//

        Conexao.getConexao().persist(municipio);
        Conexao.getConexao().getTransaction().commit();

    }

    public  List<Municipio> listMunicipio(){
       
        List<Municipio> retorno =
                Conexao.getConexao().createNativeQuery("select * from Municipio ",
                                Municipio.class)
                        .getResultList();
        Conexao.getConexao().close();
        return retorno;
    }

    public Municipio findByCodigo(String codigo) {

        try {
            return  (Municipio)  Conexao.getConexao().createNativeQuery(
                            "select * from Municipio" +
                                    " where codigo = :codigo ",
                            Municipio.class)
                    .setMaxResults(1)
                    .setParameter("codigo", codigo)
                    .getSingleResult();
        }catch (NoResultException nre){
            return  null;
        }

    }
}
