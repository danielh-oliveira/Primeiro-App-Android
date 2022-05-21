package br.com.alura.agenda.ui.activity;

import static br.com.alura.agenda.ui.activity.ConstantActivities.CHAVE_ALUNO_ID;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import br.com.alura.agenda.R;
import br.com.alura.agenda.dao.AlunoDAOroom;
import br.com.alura.agenda.data.AppDatabase;
import br.com.alura.agenda.databinding.ActivityFormularioAlunoBinding;
import br.com.alura.agenda.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR_NOVO_ALUNO = "Cadastrar aluno";
    private static final String TITULO_APPBAR_EDITA_ALUNO = "Editar aluno";
    private static final String NOME_VAZIO_AVISO = "Nome não pode estar vazio";
    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;
    private Aluno aluno;
    private ActivityFormularioAlunoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFormularioAlunoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        inicializacaoDosCampos();
        carregaAluno();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_formulario_aluno_menu_salvar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activity_formulario_aluno_menu_salvar) {
            if (nomeVazio()) {
                Toast.makeText(FormularioAlunoActivity.this, NOME_VAZIO_AVISO, Toast.LENGTH_LONG).show();
            } else {
                finalizaFormulario();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void inicializacaoDosCampos() {
        campoNome = binding.activityFormularioAlunoNome;
        campoTelefone = binding.activityFormularioAlunoTelefone;
        campoEmail = binding.activityFormularioAlunoEmail;
    }

    private boolean nomeVazio() {
        String nome = campoNome.getText().toString().replaceAll(" ", "");
        return nome.isEmpty();
    }

    private void finalizaFormulario() {
        preencheAluno();
        AlunoDAOroom alunoDAO = AppDatabase.getInstance(this.getApplicationContext()).alunoDAO();
        alunoDAO.salva(aluno);
        finish();
    }

    private void preencheAluno() {
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();

        aluno.setNome(nome);
        aluno.setTelefone(telefone);
        aluno.setEmail(email);
    }

    private void carregaAluno() {
        int idAluno = getIntent().getIntExtra(CHAVE_ALUNO_ID, 0);
        if (idAluno != 0) {
            setTitle(TITULO_APPBAR_EDITA_ALUNO);
            AlunoDAOroom alunoDAO = AppDatabase.getInstance(this.getApplicationContext()).alunoDAO();
            aluno = alunoDAO.buscaPorId(idAluno);
            campoNome.setText(aluno.getNome());
            campoTelefone.setText(aluno.getTelefone());
            campoEmail.setText(aluno.getEmail());
        } else {
            setTitle(TITULO_APPBAR_NOVO_ALUNO);
            aluno = new Aluno();
        }
    }

}
