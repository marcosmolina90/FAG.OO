package br.edu.fag.teste;

import br.edu.fag.controller.EstadoController;
import br.edu.fag.controller.MunicipioController;
import br.edu.fag.modelo.Estado;
import br.edu.fag.modelo.Municipio;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.swing.*;
import java.util.List;

public class Main {



    public static void main(String[] args) {
        String classe = JOptionPane.showInputDialog(
                "E - Estado \n" +
                "M - Municipio \n");
        if("E".equals(classe)) {
            menuEstado();
        }else if("M".equals(classe)){
            menuMunicipio();
        }

    }

    private static void menuMunicipio() {
        MunicipioController municipioController =
                new MunicipioController();
        String opc = JOptionPane.showInputDialog(
                "1 - listar \n " +
                        "2 - Inserir \n " +
                        "3 - Find por id \n " +
                        "4 - Update \n " +
                        "5 - Delete ");
        switch (opc) {
            case "1":
                List<Municipio> listMunicipio =
                        municipioController.listMunicipio();
                for (Municipio e : listMunicipio) {
                    JOptionPane.showMessageDialog(null,
                            e.getNome()+"/"+e.getEstado().getSigla());
                }
                break;
            case "2":
                municipioController.inserir();
                break;
            case "3":
                JOptionPane.showMessageDialog(null,
                        municipioController.find());
                break;
            case "4":
                municipioController.update();
                break;
            case "5":
                municipioController.delete();
                break;
        }
    }

    private static void menuEstado() {
        EstadoController estadoController = new EstadoController();
        String opc = JOptionPane.showInputDialog(
                "1 - listar \n " +
                        "2 - Inserir \n " +
                        "3 - Find por id \n " +
                        "4 - Update \n " +
                        "5 - Delete ");
        switch (opc) {
            case "1":
                List<Estado> listEstado = estadoController.listEstado();
                for (Estado e : listEstado) {
                    JOptionPane.showMessageDialog(null,
                            e.toString());
                }
                break;
            case "2":
                estadoController.inserir();
                break;
            case "3":
                JOptionPane.showMessageDialog(null,
                        estadoController.find());
                break;
            case "4":
                estadoController.update();
                break;
            case "5":
                estadoController.delete();
                break;
        }
    }


}
