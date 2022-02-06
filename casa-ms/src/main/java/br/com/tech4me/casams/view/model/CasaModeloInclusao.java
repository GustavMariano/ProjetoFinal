package br.com.tech4me.casams.view.model;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class CasaModeloInclusao {
    
    @NotBlank(message = "Dono não pode estar em branco")
    @NotEmpty(message = "Dono não pode estar vazio")
    private String dono;
    private Integer numero;
    private String bairro;
    private String cidade;
    
    public String getDono() {
        return dono;
    }
    public void setDono(String dono) {
        this.dono = dono;
    }
    public Integer getNumero() {
        return numero;
    }
    public void setNumero(Integer numero) {
        this.numero = numero;
    }
    public String getBairro() {
        return bairro;
    }
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    
}
