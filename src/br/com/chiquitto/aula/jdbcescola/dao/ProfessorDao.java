package br.com.chiquitto.aula.jdbcescola.dao;

import br.com.chiquitto.aula.jdbcescola.Conexao;
import br.com.chiquitto.aula.jdbcescola.exception.RowNotFoundException;
import br.com.chiquitto.aula.jdbcescola.vo.Pessoa;
import br.com.chiquitto.aula.jdbcescola.vo.Professor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chiquitto
 */
public class ProfessorDao extends PessoaDao {

    public void cadastrar(Professor professor) {
        String sql = "Insert Into pessoa"
                + " (tipo, nome, fone, email, salario, nascimento)"
                + " Values"
                + " (2, ?, ?, ?, ?, ?)";

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        try {

            PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, professor.getNome());
            stmt.setString(2, professor.getFone());
            stmt.setString(3, professor.getEmail());
            stmt.setBigDecimal(4, professor.getSalario());
            stmt.setString(5, df.format(professor.getNascimento()));
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            generatedKeys.next();
            professor.setIdpessoa(generatedKeys.getInt(1));

            stmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void editar(Professor professor) {
        String sql = "Update pessoa"
                + " Set "
                + " nome = ?,"
                + " fone = ?,"
                + " email = ?,"
                + " salario = ?,"
                + " nascimento = ?"
                + " Where (idpessoa = ?)"
                + "     And (tipo = 2)";

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        try {

            PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql);
            stmt.setString(1, professor.getNome());
            stmt.setString(2, professor.getFone());
            stmt.setString(3, professor.getEmail());
            stmt.setBigDecimal(4, professor.getSalario());
            stmt.setString(5, df.format(professor.getNascimento()));
            stmt.setInt(6, professor.getIdpessoa());
            stmt.executeUpdate();
            stmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<Professor> getAll() {
        List<Professor> professores = new ArrayList<>();

        try {
            Statement st = Conexao.getConexao().createStatement();
            ResultSet rs = st.executeQuery("Select idpessoa, nome, fone, email, salario, nascimento From pessoa Where tipo=2");

            while (rs.next()) {
                Professor professor = (Professor) recordset2Vo(rs, Pessoa.TIPO_PROFESSOR);

                professores.add(professor);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return professores;
    }

    public Professor getOne(int idpessoa) throws RowNotFoundException {
        return (Professor) super.getOne(idpessoa);
    }
}
