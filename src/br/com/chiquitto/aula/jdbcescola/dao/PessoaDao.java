package br.com.chiquitto.aula.jdbcescola.dao;

import br.com.chiquitto.aula.jdbcescola.Conexao;
import br.com.chiquitto.aula.jdbcescola.vo.Endereco;
import br.com.chiquitto.aula.jdbcescola.vo.Pessoa;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author chiquitto
 */
abstract public class PessoaDao extends AbstractDao {

    public void apagar(Pessoa pessoa) {
        try {
            Endereco endereco = new Endereco();
            endereco.setIdpessoa(pessoa.getIdpessoa());
            new EnderecoDao().apagar(endereco);

            String sqlDelete = "Delete From pessoa Where (idpessoa = ?) And (tipo = ?)";
            PreparedStatement stmtDelete = Conexao.getConexao().prepareStatement(sqlDelete);
            stmtDelete.setInt(1, pessoa.getIdpessoa());
            stmtDelete.setInt(2, pessoa.getTipo());
            stmtDelete.executeUpdate();
            stmtDelete.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
