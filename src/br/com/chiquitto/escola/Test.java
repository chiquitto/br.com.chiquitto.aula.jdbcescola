package br.com.chiquitto.escola;

import br.com.chiquitto.escola.dao.AlunoDao;
import br.com.chiquitto.escola.dao.CidadeDao;
import br.com.chiquitto.escola.dao.ProfessorDao;
import br.com.chiquitto.escola.exception.RowNotFoundException;
import br.com.chiquitto.escola.vo.Aluno;
import br.com.chiquitto.escola.vo.Cidade;
import br.com.chiquitto.escola.vo.Professor;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author chiquitto
 */
public class Test {

    public static void main(String[] args) {
        Conexao.setUrl("jdbc:sqlite:/Users/chiquitto/work/aula/java-caso-uso-escola/data/escola.sqlite.db");

        // professor();

        //aluno();
        
        cidade();
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

}