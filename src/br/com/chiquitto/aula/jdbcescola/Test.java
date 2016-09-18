package br.com.chiquitto.aula.jdbcescola;

import br.com.chiquitto.aula.jdbcescola.dao.AlunoDao;
import br.com.chiquitto.aula.jdbcescola.dao.CidadeDao;
import br.com.chiquitto.aula.jdbcescola.dao.EnderecoDao;
import br.com.chiquitto.aula.jdbcescola.dao.ProfessorDao;
import br.com.chiquitto.aula.jdbcescola.dao.UsuarioDao;
import br.com.chiquitto.aula.jdbcescola.exception.RowNotFoundException;
import br.com.chiquitto.aula.jdbcescola.vo.Aluno;
import br.com.chiquitto.aula.jdbcescola.vo.Cidade;
import br.com.chiquitto.aula.jdbcescola.vo.Endereco;
import br.com.chiquitto.aula.jdbcescola.vo.Professor;
import br.com.chiquitto.aula.jdbcescola.vo.Usuario;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chiquitto
 */
public class Test {

    public static void main(String[] args) {
        Conexao.setUrl("jdbc:sqlite:/Users/chiquitto/work/aula/br.com.chiquitto.aula.jdbcescola/data/escola.sqlite.db");

        //professor();
        //usuario();
        //aluno();
        //cidade();
        endereco();
    }

    private static void aluno() {
        Calendar c = Calendar.getInstance();
        Date hoje = c.getTime();
        c.add(Calendar.MONTH, -1);
        Date mesPassado = c.getTime();

        Aluno a = new Aluno();
        a.setEmail("chiquitto@gmail.com");
        a.setFone("44-9999-8888");
        a.setNascimento(hoje);
        a.setNome("Aluno 123");
        a.setNumero(1111);

        AlunoDao alunoDao = new AlunoDao();
        alunoDao.cadastrar(a);

        System.out.println("Aluno cadastrado: " + a.getIdpessoa());

        a.setNascimento(mesPassado);
        alunoDao.editar(a);

        List<Aluno> alunos = alunoDao.getAll();
        for (Aluno aluno : alunos) {
            System.out.println(aluno.getIdpessoa() + ":" + aluno.getNome() + ":" + aluno.getNascimento());
        }
    }

    private static void professor() {
        Calendar c = Calendar.getInstance();
        Date hoje = c.getTime();
        c.add(Calendar.MONTH, -1);
        Date mesPassado = c.getTime();

        Professor p = new Professor();
        p.setEmail("chiquitto@gmail.com");
        p.setFone("44-9999-6666");
        p.setNascimento(hoje);
        p.setSalario(10000);
        p.setNome("Alisson Chiquitto");

        ProfessorDao professorDao = new ProfessorDao();
        professorDao.cadastrar(p);

        System.out.println("Professor cadastrado: " + p.getIdpessoa());

        p.setNascimento(mesPassado);
        professorDao.editar(p);

        try {
            System.out.println("Pegar prof:" + p.getIdpessoa());
            Professor prof = professorDao.getOne(p.getIdpessoa());
            System.out.println("Professor (getOne): " + prof.getIdpessoa());
        } catch (RowNotFoundException ex) {
            ex.printStackTrace();
        }

        List<Professor> professores = professorDao.getAll();
        for (Professor professor : professores) {
            System.out.println(professor.getIdpessoa() + ":" + professor.getNome() + ":" + professor.getNascimento());
        }
    }

    private static void cidade() {
        Cidade p = new Cidade();
        p.setCidade("São José do Rio Preto");

        CidadeDao cidadeDao = new CidadeDao();
        cidadeDao.cadastrar(p);

        System.out.println("Cidade cadastrado: " + p.getCidade());

        p.setCidade("Rio de Janeiro");
        cidadeDao.editar(p);

        try {
            Cidade cid = cidadeDao.getOne(p.getIdcidade());
            System.out.println("Cidade (getOne): " + cid.getIdcidade());
        } catch (RowNotFoundException ex) {
            ex.printStackTrace();
        }

        List<Cidade> cidades = cidadeDao.getAll();
        for (Cidade cidade : cidades) {
            System.out.println(cidade.getIdcidade()+ ":" + cidade.getCidade());
        }
    }

    private static void usuario() {
        Calendar c = Calendar.getInstance();
        Date hoje = c.getTime();
        c.add(Calendar.MONTH, -1);
        Date mesPassado = c.getTime();

        Usuario a = new Usuario();
        a.setEmail("chiquitto@gmail.com");
        a.setFone("4499998888");
        a.setNascimento(hoje);
        a.setNome("Usuario 123");
        a.setSenha("teste");

        UsuarioDao usuarioDao = new UsuarioDao();
        usuarioDao.cadastrar(a);

        System.out.println("Usuario cadastrado: " + a.getIdpessoa());

        a.setNascimento(mesPassado);
        usuarioDao.editar(a);
        
        Usuario usuarioByEmailSenha = new Usuario();
        usuarioByEmailSenha.setEmail("user1@test.com");
        usuarioByEmailSenha.setSenha("123456");
        
        try {
            usuarioByEmailSenha = usuarioDao.getByEmailSenha(usuarioByEmailSenha);
            System.out.println(usuarioByEmailSenha.getIdpessoa());
        } catch (RowNotFoundException ex) {
            
        }

        List<Usuario> usuarios = usuarioDao.getAll();
        for (Usuario usuario : usuarios) {
            System.out.println(usuario.getIdpessoa() + ":" + usuario.getNome() + ":" + usuario.getNascimento());
        }
    }
    
    private static void endereco() {
        Usuario usuario = new Usuario();
        usuario.setEmail("teste@gmail.com");
        usuario.setFone("44123456789");
        usuario.setNascimento(Calendar.getInstance().getTime());
        usuario.setNome("Usuario Endereco");
        usuario.setSenha("teste");

        UsuarioDao usuarioDao = new UsuarioDao();
        usuarioDao.cadastrar(usuario);
        
        // Endereco
        EnderecoDao dao = new EnderecoDao();
        
        // Cadastrar endereco
        Endereco enderecoCad = new Endereco();
        enderecoCad.setIdpessoa(usuario.getIdpessoa());
        enderecoCad.setIdcidade(1);
        enderecoCad.setLogradouro("Rua das casas");
        enderecoCad.setNumero("S/N");
        dao.cadastrar(enderecoCad);
        System.out.println("Endereco cadastrado:" + enderecoCad.getIdendereco());
        
        // Pesquisar endereco
        Endereco enderecoCad2;
        try {
            enderecoCad2 = dao.getOne(enderecoCad.getIdendereco());
            System.out.println(enderecoCad2);
        } catch (RowNotFoundException ex) {
            ex.printStackTrace();
        }
        
        // Editar endereco
        Endereco enderecoUpdate = new Endereco();
        enderecoUpdate.setIdendereco(enderecoCad.getIdendereco());
        enderecoUpdate.setIdpessoa(usuario.getIdpessoa());
        enderecoUpdate.setIdcidade(1);
        enderecoUpdate.setLogradouro("Rua das fabricas");
        enderecoUpdate.setNumero("1001");
        dao.editar(enderecoUpdate);
        
        // Pesquisar endereco
        Endereco enderecoUpdate2;
        try {
            enderecoUpdate2 = dao.getOne(enderecoUpdate.getIdendereco());
            System.out.println(enderecoUpdate2);
        } catch (RowNotFoundException ex) {
            System.out.println("Endereco inexistente:" + enderecoUpdate.getIdendereco());
        }
        
        // Testar apagar
        dao.apagar(enderecoCad);
        
        Endereco enderecoApagar;
        try {
            enderecoApagar = dao.getOne(enderecoCad.getIdendereco());
        } catch (RowNotFoundException ex) {
            System.out.println("Endereco apagado:" + enderecoCad.getIdendereco());
        }
        
        // Testar consulta de enderecos
        dao.cadastrar(enderecoCad);
        dao.cadastrar(enderecoCad);
        
        List<Endereco> enderecos = dao.getAll();
        for (Endereco endereco : enderecos) {
            System.out.println(endereco);
        }
        
        enderecos = dao.getAllByIdpessoa(usuario.getIdpessoa());
        for (Endereco endereco : enderecos) {
            System.out.println(endereco);
        }
    }

}
