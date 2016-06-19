package br.com.chiquitto.escola.vo;

import java.util.Date;

/**
 *
 * @author chiquitto
 */
abstract public class Pessoa {

    private int idpessoa;
    private String nome;
    private String fone;
    private String email;
    private Date nascimento;

    public int getIdpessoa() {
        return idpessoa;
    }

    public void setIdpessoa(int idpessoa) {
        this.idpessoa = idpessoa;
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

    /*public void setNascimento(Date nascimento) {
        Calendar c = Calendar.getInstance();
        c.setTime(nascimento);
        
        this.nascimento = LocalDate.of(c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, c.get(Calendar.DAY_OF_MONTH));
    }*/
}
