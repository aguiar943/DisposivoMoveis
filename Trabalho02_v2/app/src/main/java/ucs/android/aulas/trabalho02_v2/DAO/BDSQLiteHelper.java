package ucs.android.aulas.trabalho02_v2.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import ucs.android.aulas.trabalho02_v2.model.Json;

public class BDSQLiteHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "BANCODADOS";
    private static final String TABELA_CEP = "CEP";
    private static final String ID = "id";
    private static final String CEP = "cep";
    private static final String LOGRADOURO = "logradouro";
    private static final String COMPLEMENTO = "complemento";
    private static final String BAIRRO = "bairro";
    private static final String LOCALIDADE = "localidade";
    private static final String UF = "uf";
    private static final String IBGE = "ibge";
    private static final String[] COLUNAS = {ID, CEP, LOGRADOURO, COMPLEMENTO, BAIRRO, LOCALIDADE, UF, IBGE};

    public BDSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE CEP (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "cep TEXT," +
                "logradouro TEXT," +
                "complemento TEXT," +
                "bairro TEXT," +
                "localidade TEXT," +
                "uf TEXT," +
                "ibge TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS CEP");
        this.onCreate(db);
    }

    public void addCep(Json ceps) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CEP, ceps.getCep());
        values.put(LOGRADOURO, ceps.getLogradouro());
        values.put(COMPLEMENTO, ceps.getComplemento());
        values.put(BAIRRO, ceps.getBairro());
        values.put(LOCALIDADE, ceps.getLocalidade());
        values.put(UF, ceps.getUf());
        values.put(IBGE, ceps.getIbge());

        db.insert(TABELA_CEP, null, values);
        db.close();
    }

    public Json getCeps(Json cep) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABELA_CEP, COLUNAS, "cep = ?",
                new String[]{String.valueOf(cep)},
                null,
                null,
                null,
                null);
        if (cursor == null) {
            return null;
        } else {
            cursor.moveToFirst();
            Json ceps = cursorToCeps(cursor);
            return ceps;
        }
    }

    private Json cursorToCeps(Cursor cursor) {
        Json ceps = new Json();
        ceps.setCep(cursor.getString(0));
        ceps.setLogradouro(cursor.getString(1));
        ceps.setComplemento(cursor.getString(2));
        ceps.setBairro(cursor.getString(4));
        ceps.setLocalidade(cursor.getString(5));
        ceps.setUf(cursor.getString(6));
        ceps.setIbge(cursor.getString(7));
        return ceps;
    }

    public ArrayList<Json> getAllCeps() {
        ArrayList<Json> listaCeps = new ArrayList<Json>();
        String query = "SELECT * FROM " + TABELA_CEP + " ORDER BY " + CEP;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Json ceps = cursorToCeps(cursor);
                listaCeps.add(ceps);
            } while (cursor.moveToNext());
        }
        return listaCeps;
    }

    public int updateCeps(Json ceps) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CEP, ceps.getCep());
        values.put(LOGRADOURO, ceps.getLogradouro());
        values.put(COMPLEMENTO, ceps.getComplemento());
        values.put(BAIRRO, ceps.getBairro());
        values.put(LOCALIDADE, ceps.getLocalidade());
        values.put(UF, ceps.getUf());
        values.put(IBGE, ceps.getIbge());
        int i = db.update(TABELA_CEP, values, CEP + " = ?",
                new String[]{String.valueOf(ceps.getCep())});
        db.close();
        return i;
    }

    public int deleteCeps(Json ceps) {
        SQLiteDatabase db = this.getWritableDatabase();
        int i = db.delete(TABELA_CEP, CEP + " = ?",
                new String[]
                        {String.valueOf(ceps.getCep())});
        db.close();
        return i;
    }
}
