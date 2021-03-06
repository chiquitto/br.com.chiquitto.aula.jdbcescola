package br.com.chiquitto.aula.jdbcescola.dao;

import br.com.chiquitto.aula.jdbcescola.Conexao;
import br.com.chiquitto.aula.jdbcescola.exception.RowNotFoundException;
import br.com.chiquitto.aula.jdbcescola.vo.Endereco;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chiquitto
 */
public class EnderecoDao extends AbstractDao {

    public void apagar(Endereco endereco) {
        try {
            String sql = "Delete from endereco Where idendereco = ?";
            PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql);
            stmt.setInt(1, endereco.getIdendereco());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void apagarByIdpessoa(int idpessoa) {
        try {
            String sql = "Delete from endereco Where idpessoa = ?";
            PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql);
            stmt.setInt(1, idpessoa);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void cadastrar(Endereco endereco) {
        String sql = "Insert Into endereco"
                + " (idcidade, idpessoa, logradouro, numero)"
                + " Values"
                + "(?, ?, ?, ?)";

        try {
            PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, endereco.getIdcidade());
            stmt.setInt(2, endereco.getIdpessoa());
            stmt.setString(3, endereco.getLogradouro());
            stmt.setString(4, endereco.getNumero());
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            generatedKeys.next();
            endereco.setIdendereco(generatedKeys.getInt(1));

            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void editar(Endereco endereco) {
        String sql = "Update endereco Set"
                + " idcidade = ?,"
                + " logradouro = ?,"
                + " numero = ?"
                + " Where idendereco = ?";

        try {
            PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql);
            stmt.setInt(1, endereco.getIdcidade());
            stmt.setString(2, endereco.getLogradouro());
            stmt.setString(3, endereco.getNumero());
            stmt.setInt(4, endereco.getIdendereco());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<Endereco> getAll() {
        List<Endereco> enderecos = new ArrayList<>();

        try {
            Statement st = Conexao.getConexao().createStatement();
            ResultSet rs = st.executeQuery("Select idendereco, idpessoa, idcidade, logradouro, numero From endereco");

            while (rs.next()) {
                enderecos.add(recordset2Vo(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return enderecos;
    }

    public List<Endereco> getAllByIdpessoa(int idpessoa) {
        List<Endereco> enderecos = new ArrayList<>();

        try {
            String sql = "Select idendereco, idpessoa, idcidade, logradouro, numero From endereco Where idpessoa = ?";

            PreparedStatement st = Conexao.getConexao().prepareStatement(sql);
            st.setInt(1, idpessoa);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                enderecos.add(recordset2Vo(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return enderecos;
    }

    public Endereco getOne(int idendereco) throws RowNotFoundException {
        try {
            String sql = "Select idendereco, idpessoa, idcidade, logradouro, numero From endereco Where idendereco = ?";
            PreparedStatement st = Conexao.getConexao().prepareStatement(sql);
            st.setInt(1, idendereco);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return recordset2Vo(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        throw new RowNotFoundException();
    }

    protected Endereco recordset2Vo(ResultSet rs) throws SQLException {
        Endereco endereco = new Endereco();
        endereco.setIdendereco(rs.getInt("idendereco"));
        endereco.setIdpessoa(rs.getInt("idpessoa"));
        endereco.setIdcidade(rs.getInt("idcidade"));
        endereco.setLogradouro(rs.getString("logradouro"));
        endereco.setNumero(rs.getString("numero"));

        return endereco;
    }
}
