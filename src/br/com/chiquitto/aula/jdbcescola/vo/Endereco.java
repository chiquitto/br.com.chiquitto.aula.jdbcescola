package br.com.chiquitto.aula.jdbcescola.vo;

import java.io.Serializable;

/**
 *
 * @author chiquitto
 */
public class Endereco implements Serializable {

    private int idcidade;
    private int idpessoa;
    private String logradouro;
    private String numero;

    public int getIdcidade() {
        return idcidade;
    }

    public void setIdcidade(int idcidade) {
        this.idcidade = idcidade;
    }

    public int getIdpessoa() {
        return idpessoa;
    }

    public void setIdpessoa(int idpessoa) {
        this.idpessoa = idpessoa;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

}
