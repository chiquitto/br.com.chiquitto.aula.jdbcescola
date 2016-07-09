package br.com.chiquitto.aula.jdbcescola.dao;

import br.com.chiquitto.aula.jdbcescola.Conexao;
import br.com.chiquitto.aula.jdbcescola.vo.Aluno;
import br.com.chiquitto.aula.jdbcescola.vo.Endereco;
import br.com.chiquitto.aula.jdbcescola.vo.Pessoa;
import br.com.chiquitto.aula.jdbcescola.vo.Professor;
import br.com.chiquitto.aula.jdbcescola.vo.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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

    protected Pessoa recordset2Vo(ResultSet rs, int tipo) throws SQLException {
        Pessoa pessoa;

        switch (tipo) {
            case Pessoa.TIPO_ALUNO:
                pessoa = new Aluno();
                ((Aluno) pessoa).setNumero(rs.getInt("numero"));
                break;
                
            case Pessoa.TIPO_PROFESSOR:
                pessoa = new Professor();
                ((Professor) pessoa).setSalario(rs.getBigDecimal("salario"));
                break;
                
            case Pessoa.TIPO_USUARIO:
                pessoa = new Usuario();
                ((Usuario) pessoa).setSenha(rs.getString("senha"));
                break;

            default:
                return null;
        }

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        pessoa.setIdpessoa(rs.getInt("idpessoa"));
        pessoa.setNome(rs.getString("nome"));
        pessoa.setFone(rs.getString("fone"));
        pessoa.setEmail(rs.getString("email"));

        try {
            pessoa.setNascimento(df.parse(rs.getString("nascimento")));
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        return pessoa;
    }

}
