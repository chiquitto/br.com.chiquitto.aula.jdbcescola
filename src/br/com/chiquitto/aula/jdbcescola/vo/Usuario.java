package br.com.chiquitto.aula.jdbcescola.vo;

/**
 *
 * @author chiquitto
 */
public class Usuario extends Pessoa {

    private String senha;
    
    public Usuario() {
        this.setTipo(Pessoa.TIPO_USUARIO);
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}
