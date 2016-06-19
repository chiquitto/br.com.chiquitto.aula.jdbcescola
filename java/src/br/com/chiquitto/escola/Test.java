package br.com.chiquitto.escola;

import br.com.chiquitto.escola.dao.AlunoDao;
import br.com.chiquitto.escola.dao.ProfessorDao;
import br.com.chiquitto.escola.vo.Aluno;
import br.com.chiquitto.escola.vo.Professor;
import java.sql.Connection;
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
        
        List<Professor> professores = professorDao.getAll();
        for(Professor professor : professores) {
            System.out.println(professor.getIdpessoa() + ":" + professor.getNome() + ":" + professor.getNascimento());
        }
        
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
        for(Aluno aluno : alunos) {
            System.out.println(aluno.getIdpessoa() + ":" + aluno.getNome() + ":" + aluno.getNascimento());
        }
    }
}
