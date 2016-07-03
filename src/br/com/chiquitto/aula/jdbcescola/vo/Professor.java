package br.com.chiquitto.aula.jdbcescola.vo;

import java.math.BigDecimal;

/**
 *
 * @author chiquitto
 */
public class Professor extends Pessoa {

    private BigDecimal salario;

    public Professor() {
        this.setTipo(Pessoa.TIPO_PROFESSOR);
    }
    
    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }
    
    public void setSalario(float salario) {
        this.salario = new BigDecimal(salario);
    }

}
