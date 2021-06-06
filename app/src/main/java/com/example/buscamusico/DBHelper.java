package com.example.buscamusico;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import static android.os.Build.ID;

public class DBHelper extends SQLiteOpenHelper {
    private byte[] nascimento;

    public DBHelper(Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Userdetails(nome TEXT Primary key ,nascimento TEXT,endereco TEXT,telefone TEXT,rg TEXT,cpf TEXT,email TEXT,usuario TEXT,senha TEXT,genero TEXT,instrumento TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists Userdetails");

    }

    public Boolean insertuserdata( String nome,String nascimento,String endereco,String telefone,String rg,String cpf,String email,String usuario,String senha,String genero,String instrumento)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("nome",nome);
        contentValues.put("nascimento",nascimento);
        contentValues.put("endereco",endereco);
        contentValues.put("telefone",telefone);
        contentValues.put("rg",rg);
        contentValues.put("cpf",cpf);
        contentValues.put("email",email);
        contentValues.put("usuario",usuario);
        contentValues.put("senha",senha);
        contentValues.put("genero",genero);
        contentValues.put("instrumento",instrumento);
        long results= db.insert("Userdetails",null,contentValues);
        if(results==-1){
            return false;
        }else {
            return true;
        }

    }

    public Boolean updateuserdata(String nome, String nascimento, String endereco, String telefone, String rg, String cpf, String email, String usuario, String senha, String genero, String instrumento) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("nascimento", nascimento);
        contentValues.put("endereco", endereco);
        contentValues.put("telefone", telefone);
        contentValues.put("rg", rg);
        contentValues.put("cpf", cpf);
        contentValues.put("email", email);
        contentValues.put("usuario", usuario);
        contentValues.put("senha", senha);
        contentValues.put("genero", genero);
        contentValues.put("instrumento", instrumento);
        Cursor cursor = db.rawQuery("Select * from Userdetails where name=?", new String[]{nome});
        if (cursor.getCount() > 0) {

            long results = db.update("Userdetails", contentValues, "nome=?", new String[]{nome});
            if (results == -1) {
                return false;
            } else {
                return true;
            }


        }
        return true;


    }

    public Boolean deletedata(String nome) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select* from Userdetails where nome=?", new String[]{nome});
        if(cursor.getCount()>0){


            long results = db.delete("Userdetails", "nome=?", new String[]{nome});
            if (results == -1) {
                return false;
            } else {
                return true;
            }

        } else {
            return false;
        }
    }

    public Cursor getdata()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select* from Userdetails",null);
        return cursor;
    }



}
