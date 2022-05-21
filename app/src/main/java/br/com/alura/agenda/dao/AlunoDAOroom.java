package br.com.alura.agenda.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import br.com.alura.agenda.model.Aluno;

@Dao
public interface AlunoDAOroom {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void salva(Aluno aluno);

    @Delete
    void remove(Aluno aluno);

    @Query("SELECT * FROM aluno")
    List<Aluno> todos();

    @Query("SELECT * FROM aluno WHERE id = :id")
    Aluno buscaPorId(int id);
}
