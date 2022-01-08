package br.com.alura.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import br.com.alura.agenda.R;
import br.com.alura.agenda.dao.alunoDAO;
import br.com.alura.agenda.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Cadastro aluno";
    public static final String NOME_VAZIO_AVISO = "Nome não pode estar vazio";
    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;
    private final alunoDAO dao = new alunoDAO();
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        setTitle(TITULO_APPBAR);
        inicializacaoDosCampos();
        configuraBotaoSalvar();

        Intent dados = getIntent();
        if (dados.hasExtra("aluno")) {
            aluno = (Aluno) dados.getSerializableExtra("aluno");
            campoNome.setText(aluno.getNome());
            campoTelefone.setText(aluno.getTelefone());
            campoEmail.setText(aluno.getEmail());
        } else {
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
                    preencheAluno();
                    if(aluno.temIdValido()){
                        dao.edita(aluno);
                    } else {
                        dao.salva(aluno);
                    }
                    finish();
                }
            }
        });
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