package br.com.alura.agenda.ui.activity;

import static br.com.alura.agenda.ui.activity.ConstantActivities.CHAVE_ALUNO;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import br.com.alura.agenda.R;
import br.com.alura.agenda.dao.alunoDAO;
import br.com.alura.agenda.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR_NOVO_ALUNO = "Cadastrar aluno";
    private static final String TITULO_APPBAR_EDITA_ALUNO = "Editar aluno";
    private static final String NOME_VAZIO_AVISO = "Nome não pode estar vazio";
    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;
    private final alunoDAO dao = new alunoDAO();
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        inicializacaoDosCampos();
        configuraBotaoSalvar();
        carregaAluno();
    }

    private void carregaAluno() {
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_ALUNO)) {
            setTitle(TITULO_APPBAR_EDITA_ALUNO);
            aluno = (Aluno) dados.getSerializableExtra(CHAVE_ALUNO);
            campoNome.setText(aluno.getNome());
            campoTelefone.setText(aluno.getTelefone());
            campoEmail.setText(aluno.getEmail());
        } else {
            setTitle(TITULO_APPBAR_NOVO_ALUNO);
            aluno = new Aluno();
        }
    }

    private void configuraBotaoSalvar() {
        Button botaoSalvarFormulario = findViewById(R.id.activity_formulario_aluno_botao_enviar);
        botaoSalvarFormulario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nomeVazio()) {
                    Toast.makeText(FormularioAlunoActivity.this, NOME_VAZIO_AVISO, Toast.LENGTH_LONG).show();
                } else {
                    finalizaFormulario();
                }
            }
        });
    }

    private void finalizaFormulario() {
        preencheAluno();
        if(aluno.temIdValido()){
            dao.edita(aluno);
        } else {
            dao.salva(aluno);
        }
        finish();
    }

    private boolean nomeVazio() {
        String nome = campoNome.getText().toString().replaceAll(" ", "");
        return nome.isEmpty();
    }

    private void inicializacaoDosCampos() {
        campoNome = findViewById(R.id.activity_formulario_aluno_nome);
        campoTelefone = findViewById(R.id.activity_formulario_aluno_telefone);
        campoEmail = findViewById(R.id.activity_formulario_aluno_email);
    }

    private void preencheAluno() {
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();

        aluno.setNome(nome);
        aluno.setTelefone(telefone);
        aluno.setEmail(email);
    }
}
