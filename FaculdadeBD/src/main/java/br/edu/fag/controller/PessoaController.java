package br.edu.fag.controller;

import br.com.caelum.stella.validation.CPFValidator;
import br.edu.fag.modelo.Endereco;
import br.edu.fag.modelo.Pessoa;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.json.JSONObject;

import javax.swing.*;
import java.io.IOException;

public class PessoaController {

    public  void inserir() {
        try {
            carregaEndereco(new Pessoa());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void validaCPF(String cpf){
        CPFValidator cpfValidator = new CPFValidator();
        cpfValidator.assertValid(cpf);
    }

    private void carregaEndereco(Pessoa pessoa) throws IOException {
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
