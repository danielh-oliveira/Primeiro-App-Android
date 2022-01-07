package br.com.alura.agenda;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Lista de alunos");
        ArrayList<String> alunos = new ArrayList<>(
                Arrays.asList("Vitor", "Chiquinho", "Gavião", "Daniel", "Indibrega", "Bob"));

        ListView listaDeAlunos = findViewById(R.id.activity_main_lista_alunos);

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
