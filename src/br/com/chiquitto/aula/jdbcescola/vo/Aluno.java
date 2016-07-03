package br.com.chiquitto.aula.jdbcescola.vo;

/**
 *
 * @author chiquitto
 */
public class Aluno extends Pessoa {

    private int numero;
    
    public Aluno() {
        this.setTipo(Pessoa.TIPO_ALUNO);
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

}
