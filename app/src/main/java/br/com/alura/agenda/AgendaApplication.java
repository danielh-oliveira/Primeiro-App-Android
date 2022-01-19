package br.com.alura.agenda;

import android.app.Application;

import br.com.alura.agenda.dao.AlunoDAO;
import br.com.alura.agenda.model.Aluno;

public class AgendaApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        criaAlunosDeTeste();
    }

    private void criaAlunosDeTeste() {
//        AlunoDAO dao = new AlunoDAO();
//        dao.salva(new Aluno("Daniel", "1234556789", "dan@gmail.com"));
//        dao.salva(new Aluno("Vitor", "987654321", "vitin@gmail.com"));
    }
}
