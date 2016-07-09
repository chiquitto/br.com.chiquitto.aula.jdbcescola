package br.com.chiquitto.aula.jdbcescola.dao;

import br.com.chiquitto.aula.jdbcescola.Conexao;
import br.com.chiquitto.aula.jdbcescola.exception.RowNotFoundException;
import br.com.chiquitto.aula.jdbcescola.vo.Pessoa;
import br.com.chiquitto.aula.jdbcescola.vo.Usuario;
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
public class UsuarioDao extends PessoaDao {

    public void cadastrar(Usuario usuario) {
        String sql = "Insert Into pessoa"
                + " (tipo, nome, fone, email, senha, nascimento)"
                + " Values"
                + " (3, ?, ?, ?, ?, ?)";

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        try {

            PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getFone());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getSenha());
            stmt.setString(5, df.format(usuario.getNascimento()));
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            generatedKeys.next();
            usuario.setIdpessoa(generatedKeys.getInt(1));

            stmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void editar(Usuario usuario) {
        String sql = "Update pessoa"
                + " Set "
                + " nome = ?,"
                + " fone = ?,"
                + " email = ?,"
                + " senha = ?,"
                + " nascimento = ?"
                + " Where (idpessoa = ?)"
                + "     And (tipo = ?)";

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        try {

            PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getFone());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getSenha());
            stmt.setString(5, df.format(usuario.getNascimento()));
            stmt.setInt(6, usuario.getIdpessoa());
            stmt.setInt(7, usuario.getTipo());
            stmt.executeUpdate();
            stmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<Usuario> getAll() {
        List<Usuario> usuarios = new ArrayList<>();

        try {
            Statement st = Conexao.getConexao().createStatement();
            ResultSet rs = st.executeQuery("Select idpessoa, senha, nome, fone, email, nascimento From pessoa Where tipo=3");

            while (rs.next()) {
                Usuario usuario = (Usuario) recordset2Vo(rs, Pessoa.TIPO_USUARIO);
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    public Usuario getByEmailSenha(Usuario usuario) throws RowNotFoundException {
        try {
            String sql = "Select idpessoa, nome, fone, email, senha, nascimento From pessoa Where (tipo=3) And (email = ?) And (senha = ?)";

            PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql);
            stmt.setString(1, usuario.getEmail());
            stmt.setString(2, usuario.getSenha());
            
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Usuario usuario2 = (Usuario) recordset2Vo(rs, Pessoa.TIPO_USUARIO);
                return usuario2;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        throw new RowNotFoundException();
    }

    public Usuario getOne(int idpessoa) throws RowNotFoundException {
        return (Usuario) super.getOne(idpessoa);
    }
}
