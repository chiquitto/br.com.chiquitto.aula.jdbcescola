package br.com.chiquitto.escola.dao;

import br.com.chiquitto.escola.vo.Endereco;
import br.com.chiquitto.escola.vo.Professor;
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
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, professor.getIdpessoa());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void cadastrar(Professor professor) {
        String sql = "Insert Into pessoa"
                + " (tipo, nome, fone, email, salario, nascimento)"
                + " Values"
                + " (2, ?, ?, ?, ?, ?)";

        try {

            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, professor.getNome());
            stmt.setString(2, professor.getFone());
            stmt.setString(3, professor.getEmail());
            stmt.setBigDecimal(4, professor.getSalario());
            stmt.setString(5, professor.getNascimento().toString());
            stmt.executeUpdate();
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

        try {

            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, professor.getNome());
            stmt.setString(2, professor.getFone());
            stmt.setString(3, professor.getEmail());
            stmt.setBigDecimal(4, professor.getSalario());
            stmt.setString(5, professor.getNascimento().toString());
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
            Statement st = conexao.createStatement();
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
}
