package br.edu.fag.controller;

import br.com.caelum.stella.validation.CPFValidator;
import br.edu.fag.modelo.Endereco;
import br.edu.fag.modelo.Estado;
import br.edu.fag.modelo.Pessoa;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import jdk.nashorn.internal.scripts.JO;
import org.json.JSONObject;

import javax.persistence.NoResultException;
import javax.swing.*;
import java.io.IOException;

public class PessoaController {

    public  void inserir() {
        try {
            Pessoa pessoa = new Pessoa();
            pessoa.setCpf(JOptionPane.showInputDialog("Informe Cpf"));
            validaCPF(pessoa.getCpf());
            Pessoa byCpf = findByCpf(pessoa.getCpf());
            if(byCpf != null){
                JOptionPane.showMessageDialog(null,
                        "CPF ("+pessoa.getCpf()+") cadastrado ");
                return;
            }
            pessoa.setNome(JOptionPane.showInputDialog("Informe o nome"));
            pessoa.setRg(JOptionPane.showInputDialog("Informe o Rg"));



            carregaEndereco(new Pessoa());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Pessoa findByCpf(String cpf) {
        try {
            return  (Pessoa) entityManager.createNativeQuery(
                            "select * from Pessoa" +
                                    " where cpf = :cpf ",
                            Estado.class)
                    .setMaxResults(1)
                    .setParameter("cpf", cpf)
                    .getSingleResult();
        }catch (NoResultException nre){
        }
        return  null;
    }

    private boolean validaCPF(String cpf){
        try {
            CPFValidator cpfValidator = new CPFValidator();
            cpfValidator.assertValid(cpf);
            return true;
        }catch (Exception e){
            return  false;
        }
    }

    private Endereco createEndereco() throws IOException {
        String cep = JOptionPane.showInputDialog("Informe o cep");
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://viacep.com.br/ws/"+cep+"/json/")
                .get().build();
        Response response = client.newCall(request).execute();
        JSONObject json = new JSONObject(response.body().string());
        Endereco e = new Endereco();
        e.setBairro(json.getString("bairro"));
        e.setLogradouro(json.getString("logradouro"));
        e.setCep(cep);
        e.setNumero(JOptionPane.showInputDialog("Informe o numero"));
    }


}
