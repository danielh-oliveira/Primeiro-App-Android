package br.com.alura.agenda.ui.activity;

import static br.com.alura.agenda.ui.activity.ConstantActivities.CHAVE_ALUNO_ID;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.com.alura.agenda.R;
import br.com.alura.agenda.dao.AlunoDAOroom;
import br.com.alura.agenda.data.AppDatabase;
import br.com.alura.agenda.databinding.ActivityListaAlunosBinding;
import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.ui.ListaAlunosView;

public class ListaAlunosActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Lista de alunos";
    private final ListaAlunosView listaAlunosView = new ListaAlunosView(this);
    private ActivityListaAlunosBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListaAlunosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle(TITULO_APPBAR);

        configuraFabNovoAluno();
        configuraLista();
    }

    private void configuraFabNovoAluno() {
        FloatingActionButton botaoNovoAluno = binding.activityListaAlunosFabNovoAluno;
        botaoNovoAluno.setOnClickListener(view -> abreFormularioModoInsereAluno());
    }

    private void abreFormularioModoInsereAluno() {
        startActivity(new Intent(this, FormularioAlunoActivity.class));
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_alunos_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activity_lista_menu_remover) {
            listaAlunosView.confirmaRemocao(item);
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        AlunoDAOroom alunoDAO = AppDatabase.getInstance(this.getApplicationContext()).alunoDAO();
        listaAlunosView.atualizaAlunos(alunoDAO.todos());
    }

    private void configuraLista() {
        ListView listaDeAlunos = binding.activityListaAlunosListview;
        listaAlunosView.configuraAdapter(listaDeAlunos);
        configuraListenerCliquePorItem(listaDeAlunos);
        registerForContextMenu(listaDeAlunos);
    }

    private void configuraListenerCliquePorItem(ListView listaDeAlunos) {
        //Se voce usar o SetOnClickListener o app quebra pq nao pode ser usado
//dentro de um ListView
        listaDeAlunos.setOnItemClickListener((adapterView, view, posicao, id) -> {
            Aluno alunoEscolhido = (Aluno) adapterView.getItemAtPosition(posicao);
            abreFormularioModoEditaAluno(alunoEscolhido);
        });
    }

    private void abreFormularioModoEditaAluno(Aluno alunoEscolhido) {
        Intent vaiParaFormularioActivity = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
        vaiParaFormularioActivity.putExtra(CHAVE_ALUNO_ID, alunoEscolhido.getId());
        startActivity(vaiParaFormularioActivity);
    }

}
