package br.com.chiquitto.aula.jdbcescola.dao;

import br.com.chiquitto.aula.jdbcescola.Conexao;
import br.com.chiquitto.aula.jdbcescola.exception.RowNotFoundException;
import br.com.chiquitto.aula.jdbcescola.vo.Aluno;
import br.com.chiquitto.aula.jdbcescola.vo.Endereco;
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
public class AlunoDao extends PessoaDao {

    public void apagar(Aluno aluno) {
        try {
            Endereco endereco = new Endereco();
            endereco.setIdpessoa(aluno.getIdpessoa());
            new EnderecoDao().apagar(endereco);

            String sqlDelete = "Delete From pessoa Where (idpessoa = ?) And (tipo = 1)";
            PreparedStatement stmtDelete = Conexao.getConexao().prepareStatement(sqlDelete);
            stmtDelete.setInt(1, aluno.getIdpessoa());
            stmtDelete.executeUpdate();
            stmtDelete.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void cadastrar(Aluno aluno) {
        // TODO: Verificar se email esta repetido

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
        // TODO: Verificar se email esta repetido

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

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

            while (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setIdpessoa(rs.getInt("idpessoa"));
                aluno.setNumero(rs.getInt("numero"));
                aluno.setNome(rs.getString("nome"));
                aluno.setFone(rs.getString("fone"));
                aluno.setEmail(rs.getString("email"));

                try {
                    aluno.setNascimento(df.parse(rs.getString("nascimento")));
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }

                alunos.add(aluno);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return alunos;
    }

    public Aluno getOne(int idpessoa) throws RowNotFoundException {
        try {
            String sql = "Select idpessoa, nome, fone, email, salario, nascimento From pessoa Where (tipo=1) And (idpessoa = ?)";

            PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql);
            stmt.setInt(1, idpessoa);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

                Aluno aluno = new Aluno();
                aluno.setIdpessoa(rs.getInt("idpessoa"));
                aluno.setNome(rs.getString("nome"));
                aluno.setFone(rs.getString("fone"));
                aluno.setEmail(rs.getString("email"));

                try {
                    aluno.setNascimento(df.parse(rs.getString("nascimento")));
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }

                return aluno;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        throw new RowNotFoundException();
    }
}
