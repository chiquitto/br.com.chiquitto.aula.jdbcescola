package br.com.chiquitto.aula.jdbcescola.dao;

import br.com.chiquitto.aula.jdbcescola.Conexao;
import br.com.chiquitto.aula.jdbcescola.exception.RowNotFoundException;
import br.com.chiquitto.aula.jdbcescola.vo.Cidade;
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
public class CidadeDao extends AbstractDao {
    
    public void apagar(Cidade cidade) {
        try {
            String sqlDelete = "Delete From cidade Where (idcidade = ?)";
            PreparedStatement stmtDelete = Conexao.getConexao().prepareStatement(sqlDelete);
            stmtDelete.setInt(1, cidade.getIdcidade());
            stmtDelete.executeUpdate();
            stmtDelete.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void cadastrar(Cidade cidade) {
        String sql = "Insert Into cidade"
                + " (cidade)"
                + " Values"
                + " (?)";

        try {

            PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, cidade.getCidade());
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            generatedKeys.next();
            cidade.setIdcidade(generatedKeys.getInt(1));

            stmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void editar(Cidade cidade) {
        String sql = "Update cidade"
                + " Set "
                + " cidade = ?"
                + " Where (idcidade = ?)";

        try {

            PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql);
            stmt.setString(1, cidade.getCidade());
            stmt.setInt(2, cidade.getIdcidade());
            stmt.executeUpdate();
            stmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public List<Cidade> getAll() {
        List<Cidade> cidades = new ArrayList<>();

        try {
            Statement st = Conexao.getConexao().createStatement();
            ResultSet rs = st.executeQuery("Select idcidade, cidade From cidade");
            
            while (rs.next()) {
                Cidade cidade = new Cidade();
                cidade.setIdcidade(rs.getInt("idcidade"));
                cidade.setCidade(rs.getString("cidade"));

                cidades.add(cidade);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cidades;
    }
    
    public Cidade getOne(int idcidade) throws RowNotFoundException {
        try {
            String sql = "Select idcidade, cidade From cidade Where (idcidade = ?)";

            PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql);
            stmt.setInt(1, idcidade);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Cidade cidade = new Cidade();
                cidade.setIdcidade(rs.getInt("idcidade"));
                cidade.setCidade(rs.getString("cidade"));

                return cidade;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        throw new RowNotFoundException();
    }
}
