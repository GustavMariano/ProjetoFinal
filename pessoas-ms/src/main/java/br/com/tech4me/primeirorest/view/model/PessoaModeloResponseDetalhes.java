package br.com.tech4me.primeirorest.view.model;

import java.util.List;

import br.com.tech4me.primeirorest.compartilhado.Casa;

public class PessoaModeloResponseDetalhes {
    private String id;
    private String nome;
    private String sobrenome;
    private List<Casa> casas;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getSobrenome() {
        return sobrenome;
    }
    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }
    public List<Casa> getCasas() {
        return casas;
    }
    public void setCasas(List<Casa> casas) {
        this.casas = casas;
    }
    
    
    
}
