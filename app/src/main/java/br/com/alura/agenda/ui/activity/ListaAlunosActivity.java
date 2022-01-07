package br.com.alura.agenda.ui.activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

import br.com.alura.agenda.R;

public class ListaAlunosActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        setTitle("Lista de alunos");
        ArrayList<String> alunos = new ArrayList<>(
                Arrays.asList("Vitor", "Chiquinho", "Gavião", "Daniel", "Indibrega", "Bob"));

        ListView listaDeAlunos = findViewById(R.id.activity_lista_alunos_listview);

        //O arrayadapter já possui o ListAdapter configurado dentro dele
        //Como esse projeto eh simples nao precisa

        listaDeAlunos.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                alunos));
        //Ao usar android antes do R temos acesso aos layouts.
        //Esse layout eh antigo e nao eh mais utilizado normalmente
    }
}
