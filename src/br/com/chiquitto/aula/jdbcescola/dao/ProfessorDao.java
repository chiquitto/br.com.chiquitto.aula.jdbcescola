package br.com.chiquitto.aula.jdbcescola.dao;

import br.com.chiquitto.aula.jdbcescola.Conexao;
import br.com.chiquitto.aula.jdbcescola.exception.RowNotFoundException;
import br.com.chiquitto.aula.jdbcescola.vo.Endereco;
import br.com.chiquitto.aula.jdbcescola.vo.Professor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chiquitto
 */
public class ProfessorDao extends PessoaDao {

    public void apagar(Professor professor) {
        try {
            Endereco endereco = new Endereco();
            endereco.setIdpessoa(professor.getIdpessoa());
            new EnderecoDao().apagar(endereco);

            String sql = "Delete From pessoa Where (idpessoa = ?) And (tipo = 2)";
            PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql);
            stmt.setInt(1, professor.getIdpessoa());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void cadastrar(Professor professor) {
        // TODO: Verificar se email esta repetido

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
        // TODO: Verificar se email esta repetido

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

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

            while (rs.next()) {
                Professor professor = new Professor();
                professor.setIdpessoa(rs.getInt("idpessoa"));
                professor.setNome(rs.getString("nome"));
                professor.setFone(rs.getString("fone"));
                professor.setEmail(rs.getString("email"));

                try {
                    professor.setNascimento(df.parse(rs.getString("nascimento")));
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }

                professor.setSalario(rs.getBigDecimal("salario"));

                professores.add(professor);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return professores;
    }

    public Professor getOne(int idpessoa) throws RowNotFoundException {
        try {
            String sql = "Select idpessoa, nome, fone, email, salario, nascimento From pessoa Where (tipo=2) And (idpessoa = ?)";

            PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql);
            stmt.setInt(1, idpessoa);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                
                Professor professor = new Professor();
                professor.setIdpessoa(rs.getInt("idpessoa"));
                professor.setNome(rs.getString("nome"));
                professor.setFone(rs.getString("fone"));
                professor.setEmail(rs.getString("email"));

                try {
                    professor.setNascimento(df.parse(rs.getString("nascimento")));
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }

                professor.setSalario(rs.getBigDecimal("salario"));

                return professor;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        throw new RowNotFoundException();
    }
}
