package br.com.chiquitto.aula.jdbcescola.vo;

import java.util.Date;
import java.io.Serializable;

/**
 *
 * @author chiquitto
 */
abstract public class Pessoa implements Serializable {

    private int idpessoa;
    private int tipo;
    private String nome;
    private String fone;
    private String email;
    private Date nascimento;
    
    public static final int TIPO_ALUNO = 1;
    public static final int TIPO_PROFESSOR = 2;
    public static final int TIPO_USUARIO = 3;

    public int getIdpessoa() {
        return idpessoa;
    }

    public void setIdpessoa(int idpessoa) {
        this.idpessoa = idpessoa;
    }

    public int getTipo() {
        return tipo;
    }

    protected void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    
}
