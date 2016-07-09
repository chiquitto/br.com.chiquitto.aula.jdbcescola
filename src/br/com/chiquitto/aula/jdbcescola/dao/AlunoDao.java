package br.com.chiquitto.aula.jdbcescola.dao;

import br.com.chiquitto.aula.jdbcescola.Conexao;
import br.com.chiquitto.aula.jdbcescola.exception.RowNotFoundException;
import br.com.chiquitto.aula.jdbcescola.vo.Aluno;
import br.com.chiquitto.aula.jdbcescola.vo.Pessoa;
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
public class AlunoDao extends PessoaDao {

    public void cadastrar(Aluno aluno) {
        String sql = "Insert Into pessoa"
                + " (tipo, nome, fone, email, numero, nascimento)"
                + " Values"
                + " (1, ?, ?, ?, ?, ?)";

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        try {

            PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getFone());
            stmt.setString(3, aluno.getEmail());
            stmt.setInt(4, aluno.getNumero());
            stmt.setString(5, df.format(aluno.getNascimento()));
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            generatedKeys.next();
            aluno.setIdpessoa(generatedKeys.getInt(1));

            stmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void editar(Aluno aluno) {
        String sql = "Update pessoa"
                + " Set "
                + " nome = ?,"
                + " fone = ?,"
                + " email = ?,"
                + " numero = ?,"
                + " nascimento = ?"
                + " Where (idpessoa = ?)"
                + "     And (tipo = 1)";

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        try {

            PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql);
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getFone());
            stmt.setString(3, aluno.getEmail());
            stmt.setInt(4, aluno.getNumero());
            stmt.setString(5, df.format(aluno.getNascimento()));
            stmt.setInt(6, aluno.getIdpessoa());
            stmt.executeUpdate();
            stmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<Aluno> getAll() {
        List<Aluno> alunos = new ArrayList<>();

        try {
            Statement st = Conexao.getConexao().createStatement();
            ResultSet rs = st.executeQuery("Select idpessoa, numero, nome, fone, email, nascimento From pessoa Where tipo=1");

            while (rs.next()) {
                Aluno aluno = (Aluno) recordset2Vo(rs, Pessoa.TIPO_ALUNO);
                alunos.add(aluno);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return alunos;
    }

    public Aluno getOne(int idpessoa) throws RowNotFoundException {
        return (Aluno) super.getOne(idpessoa);
    }
}
