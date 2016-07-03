package br.com.chiquitto.aula.jdbcescola.dao;

import br.com.chiquitto.aula.jdbcescola.Conexao;
import br.com.chiquitto.aula.jdbcescola.vo.Endereco;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author chiquitto
 */
public class EnderecoDao extends AbstractDao {

    public void apagar(Endereco endereco) {
        try {
            String sql = "Delete from endereco Where idpessoa = ?";
            PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql);
            stmt.setInt(1, endereco.getIdpessoa());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        System.out.println(endereco.getIdpessoa());
    }

    public void salvar(Endereco endereco) {
        apagar(endereco);

        String sql = "Insert Into endereco"
                + " (idcidade, idpessoa, logradouro, numero)"
                + " Values"
                + "(?, ?, ?, ?)";

        try {
            PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql);
            stmt.setInt(1, endereco.getIdcidade());
            stmt.setInt(2, endereco.getIdpessoa());
            stmt.setString(3, endereco.getLogradouro());
            stmt.setString(4, endereco.getNumero());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
