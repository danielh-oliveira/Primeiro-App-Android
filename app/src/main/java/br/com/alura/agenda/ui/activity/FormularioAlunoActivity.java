package br.com.alura.agenda.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        setTitle(TITULO_APPBAR);
        inicializacaoDosCampos();
        configuraBotaoSalvar();
    }

    private void configuraBotaoSalvar() {
        Button botaoSalvarFormulario = findViewById(R.id.activity_formulario_aluno_botao_enviar);
        botaoSalvarFormulario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(vazio()) {
                    Toast.makeText(FormularioAlunoActivity.this, NOME_VAZIO_AVISO, Toast.LENGTH_LONG).show();
                } else {
                    Aluno alunoCriado = criaAluno();
                    salva(alunoCriado);
                }
            }
        });
    }

    private boolean vazio() {
        String nome = campoNome.getText().toString().replaceAll(" ", "");
        return nome.isEmpty();
    }

    private void inicializacaoDosCampos() {
        campoNome = findViewById(R.id.activity_formulario_aluno_nome);
        campoTelefone = findViewById(R.id.activity_formulario_aluno_telefone);
        campoEmail = findViewById(R.id.activity_formulario_aluno_email);
    }

    private void salva(Aluno aluno) {
        dao.salva(aluno);

        finish();
    }

    @NonNull
    private Aluno criaAluno() {
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();
        return new Aluno(nome, telefone, email);
    }
}