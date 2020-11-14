package com.example.appcarro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DaoCarro extends SQLiteOpenHelper {
    private final String tabela = "tbl_carro";
    public DaoCarro(@Nullable Context context) {
        super(context, "bd_carro", null, 1);
    }

    public int excluir(DtoCarro carro) {
        String id = "id=?";
        String[] args = {carro.getId()+""};
        return getWritableDatabase().delete(tabela,id,args);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String comando = "create table " +tabela+"(" +
                         "Id integer primary key," +
                         "Marca varchar(20) not null," +
                         "Modelo varchar(20) not null," +
                         "Cor varchar(20) not null," +
                         "Ano int not null," +
                         "Valor decimal(10.2) not null)";

            sqLiteDatabase.execSQL(comando);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long inserir(DtoCarro carro) {
        ContentValues values = new ContentValues();
        values.put("Marca", carro.getMarca());
        values.put("Modelo", carro.getModelo());
        values.put("Cor", carro.getCor());
        values.put("Ano", carro.getAno());
        values.put("Valor", carro.getValor());

        return getWritableDatabase().insert(tabela, null, values);
    }

    public  ArrayList<DtoCarro> consultar() {
        String comando = "select * from " +tabela;
        Cursor cursor = getReadableDatabase().rawQuery(comando, null);
        ArrayList<DtoCarro> arraylistCarro = new ArrayList<>();

        while(cursor.moveToNext()) {
            DtoCarro dtoCarro = new DtoCarro();
            dtoCarro.setId(cursor.getInt(0));
            dtoCarro.setMarca(cursor.getString(1));
            dtoCarro.setModelo(cursor.getString(2));
            dtoCarro.setCor(cursor.getString(3));
            dtoCarro.setAno(cursor.getInt(4));
            dtoCarro.setValor(cursor.getDouble(5));

            arraylistCarro.add(dtoCarro);
        }
            return arraylistCarro;
    }

        public int Update(DtoCarro dtoCarro){
            ContentValues contentValues = new ContentValues();
            contentValues.put("Marca", dtoCarro.getMarca());
            contentValues.put("Modelo",dtoCarro.getModelo());
            contentValues.put("Ano", dtoCarro.getAno());
            contentValues.put("Cor", dtoCarro.getCor());
            contentValues.put("Valor",dtoCarro.getValor());

            String id = "id = ?";
            String[]args = {dtoCarro.getId()+""};

            return getWritableDatabase().update(tabela,contentValues,id,args);

        }

    public ArrayList<DtoCarro> SelectInOrder(String ordem) {
        String comando;
        if (ordem.equals("asc")) {
            comando = "select * from " + tabela + " order by Valor asc";
        } else {
            comando = "select * from " + tabela + " order by Valor desc";
        }
        Cursor cursor = getReadableDatabase().rawQuery(comando, null);
        ArrayList<DtoCarro> arrayListCarro = new ArrayList<>();

        while (cursor.moveToNext()) {
            DtoCarro dtoCarro = new DtoCarro(
            );

            arrayListCarro.add(dtoCarro);
        }
        return arrayListCarro;
    }
}


