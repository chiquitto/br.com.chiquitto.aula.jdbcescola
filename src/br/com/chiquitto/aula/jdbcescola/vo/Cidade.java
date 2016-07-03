package br.com.chiquitto.aula.jdbcescola.vo;

import java.io.Serializable;

/**
 *
 * @author chiquitto
 */
public class Cidade implements Serializable {

    private int idcidade;
    private String cidade;

    public int getIdcidade() {
        return idcidade;
    }

    public void setIdcidade(int idcidade) {
        this.idcidade = idcidade;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

}
