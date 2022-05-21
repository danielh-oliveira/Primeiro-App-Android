package br.com.alura.agenda.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.agenda.R;
import br.com.alura.agenda.data.AppDatabase;
import br.com.alura.agenda.model.Aluno;

public class ListaAlunosAdapter extends BaseAdapter {


    private final List<Aluno> alunos = new ArrayList<>();
    private final Context context;

    public ListaAlunosAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Aluno getItem(int posicao) {
        return alunos.get(posicao);
    }

    @Override
    public long getItemId(int posicao) {
        return alunos.get(posicao).getId();
    }

    @Override
    public View getView(int posicao, View view, ViewGroup viewGroup) {
        View viewCriada = inflaView(viewGroup);
        Aluno aluno = alunos.get(posicao);
        vincula(viewCriada, aluno);
        return viewCriada;
    }

    private void vincula(View viewCriada, Aluno aluno) {
        TextView alunoNome = viewCriada.findViewById(R.id.item_aluno_nome);
        TextView alunoTelefone = viewCriada.findViewById(R.id.item_aluno_telefone);

        alunoNome.setText(aluno.getNome());
        alunoTelefone.setText(aluno.getTelefone());
    }

    private View inflaView(ViewGroup viewGroup) {
        return LayoutInflater
                .from(context)
                .inflate(R.layout.item_aluno, viewGroup, false);
    }

    public void remove(Aluno aluno) {
        AppDatabase.getInstance(context).alunoDAO().remove(aluno);
        alunos.remove(aluno);
        notifyDataSetChanged();
    }

    public void atualiza(List<Aluno> alunos) {
        this.alunos.clear();
        this.alunos.addAll(alunos);
        notifyDataSetChanged();
    }

}
