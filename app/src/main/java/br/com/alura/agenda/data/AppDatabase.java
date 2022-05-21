package br.com.alura.agenda.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;

import br.com.alura.agenda.dao.AlunoDAOroom;
import br.com.alura.agenda.model.Aluno;

@Database(entities = {Aluno.class}, version = 1)
public abstract class AppDatabase extends androidx.room.RoomDatabase {
    public abstract AlunoDAOroom alunoDAO();

    private static AppDatabase INSTANCIA;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCIA == null) {
            INSTANCIA = Room.databaseBuilder(
                    context,
                    AppDatabase.class,
                    "agenda.db"
            ).allowMainThreadQueries().build();
            return INSTANCIA;
        } else {
            return INSTANCIA;
        }
    }
}
