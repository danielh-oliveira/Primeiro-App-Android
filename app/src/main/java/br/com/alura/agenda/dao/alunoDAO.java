package br.com.alura.agenda.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.agenda.model.Aluno;

public class alunoDAO {
    private static int contadorDeIds = 1;
    private final static List<Aluno> alunos = new ArrayList<>();

    public void salva(Aluno aluno) {
        aluno.setId(contadorDeIds);
        alunos.add(aluno);
        contadorDeIds++;
    }

    public void edita(Aluno aluno) {
        Aluno alunoEncontrado = null;
        for(Aluno a : alunos) {
            if (aluno.getId() == a.getId()) {
                alunoEncontrado = a;
            }
        }
        if (alunoEncontrado != null) {
            int posicaoAluno = alunos.indexOf(alunoEncontrado);
            alunos.set(posicaoAluno, aluno);
        }
    }

    public List<Aluno> todos() {
        return new ArrayList<>(alunos);
    }
}
