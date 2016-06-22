package br.com.chiquitto.escola.vo;

import java.math.BigDecimal;

/**
 *
 * @author chiquitto
 */
public class Professor extends Pessoa {

    private BigDecimal salario;

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
