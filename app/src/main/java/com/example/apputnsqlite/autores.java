package com.example.apputnsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class autores {

    private SqliteAdmin sqlAdmin;
    private SQLiteDatabase db;

    public autores(Context ctx, String nombresBDD, int version ){
        sqlAdmin = new SqliteAdmin(ctx,nombresBDD,null,version);
        db = sqlAdmin.getWritableDatabase();
    }

    public Autor Create (int id, String nombres, String apellidos, String isoPais, int edad){
        ContentValues datos;
        datos = new ContentValues();
        datos.put("id",id);
        datos.put("nombres ",nombres);
        datos.put("apellidos ",apellidos);
        datos.put("isoPais ",isoPais);
        datos.put("edad ",edad);

        long result = db.insert("autores", "id, nombres, apellidos, isoPais, edad", datos);

        if(result <= 0){
            return null;
        }
        else{
            return new Autor(id, nombres, apellidos, isoPais, edad);
        }



    }
    public Autor Update (int id, String nombres, String apellidos, String isoPais, int edad){

            ContentValues datos = new ContentValues();
            datos.put("nombres", nombres);
            datos.put("apellidos", apellidos);
            datos.put("isoPais", isoPais);
            datos.put("edad", edad);

            int result = db.update("autores", datos, "id=" + id, null);

            if(result <= 0){
                return null;
            }
            else {
                return new Autor(id, nombres, apellidos, isoPais, edad);
            }
    }



    public Autor Read_ById (int id){
        Cursor cursor;
        Autor registro = null;

        cursor = db.rawQuery("SELECT * FROM autores WHERE id="+id,null);

        if(cursor.getCount()>0){
            cursor.moveToFirst();
            registro = new Autor(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getInt(4)
            );


        }
        cursor.close();
        return registro;



    }
    public Autor[] Read_all (){
        Cursor cursor;
        Autor registro;
        Autor[] datos;
        int i = 0;
        cursor = db.rawQuery("SELECT * FROM autores ORDER BY apellidos,nombres",null);
        datos = new Autor[cursor.getCount()];
        while(cursor.moveToNext()){
            registro = new Autor();
            registro.Id = cursor.getInt(0);
            registro.Nombres = cursor.getString(1);
            registro.Apellidos = cursor.getString(2);
            registro.IsoPais = cursor.getString(3);
            registro.Edad = cursor.getInt(4);
            datos[i++]=registro;

        }
        cursor.close();
        return datos;


    }
    public boolean Delete (int id){
        int r = db.delete("autores", "id="+id, null);
        return r > 0;




    }

}
