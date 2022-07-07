package ucs.android.aulas.trabalho03_v1.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import ucs.android.aulas.trabalho03_v2.model.Json;

public class BDSQLiteHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "WEMSG";
    private static final String TABELA = "USUARIO";
    private static final String ID = "id";
    private static final String NOME = "nome";
    private static final String[] COLUNAS = {ID, NOME};

    public BDSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE USUARIO (" +
                "id INTEGER ," +
                "nome TEXT" +
                ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS USUARIO");
        this.onCreate(db);
    }

}
