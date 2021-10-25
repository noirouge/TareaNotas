package com.example.notassqlite;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import android.widget.ListView;

import android.widget.Toast;

import com.example.notassqlite.db.Datos;
import com.example.notassqlite.db.DbHelper;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {


ListView lista;

Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       DbHelper dbHelper = new DbHelper(MainActivity.this);



CrearDB();



ArrayList<Datos> datos = getData();




if(datos.size() > 0){

    lista = findViewById(R.id.lista);
    adapter = new Adapter(MainActivity.this, datos);
    lista.setAdapter(adapter);


    lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Datos data = (Datos) adapterView.getItemAtPosition(i);
            Intent intent = new Intent(MainActivity.this, ReadActivity.class);
            intent.putExtra("option", "2");
            int id1 = data.getId();
            String id2 = ""+id1;
            intent.putExtra("id", id2);
            startActivity(intent);

        }
    });


}









    }



    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.first, menu);


        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id2 = item.getItemId();

        if (id2 == R.id.add) {
AddNote();
        }
        return super.onOptionsItemSelected(item);

    }

private void AddNote(){
    Intent i = new Intent(MainActivity.this, ReadActivity.class);
    i.putExtra("option", "1");
    startActivity(i);
}


   public void CrearDB(){
       DbHelper dbHelper = new DbHelper(MainActivity.this);
        String db = dbHelper.getDatabaseName();
        if(db.equals("notas.db") ){
            Toast.makeText(MainActivity.this, "Base de Datos Cargada Correctamente!", Toast.LENGTH_SHORT).show();
        }else{
            SQLiteDatabase db2 = dbHelper.getWritableDatabase();
            if(db2 != null){
                Toast.makeText(MainActivity.this, "Base de Datos Creada Correctamente!", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(MainActivity.this, "Error en la Base de Datos!", Toast.LENGTH_SHORT).show();
            }

        }



    }

    public void Agregar(String title, String des, Context context){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("description", des);
        db.insert("t_notas", null, values);
        db.close();

    }




public ArrayList<Datos> getData(){
    DbHelper dbHelper = new DbHelper(MainActivity.this);
        ArrayList<Datos> notas = new ArrayList<>();

        String sql = "select * from t_notas where deleted = 0 ORDER BY id DESC";
    SQLiteDatabase database = dbHelper.getWritableDatabase();

    Cursor datos = database.rawQuery(sql, null);

    if(datos.moveToFirst()){
        do{
          @SuppressLint("Range") int id = Integer.parseInt(datos.getString(datos.getColumnIndex("id")));
            @SuppressLint("Range") String title = datos.getString(datos.getColumnIndex("title"));
           @SuppressLint("Range") String description = datos.getString(datos.getColumnIndex("description"));
           @SuppressLint("Range") int deleted = Integer.parseInt(datos.getString(datos.getColumnIndex("deleted")));
       Datos data;
      data = new Datos(id, title, description, deleted);

notas.add(data);

        }while(datos.moveToNext());

    }
    datos.close();
    database.close();

    return notas;

}

    public ArrayList<Datos> getDataID(int idi, Context context){
        DbHelper dbHelper = new DbHelper(context);
        ArrayList<Datos> notas = new ArrayList<>();

        String sql = "select * from t_notas where deleted = 0 AND id = "+idi;
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        Cursor datos = database.rawQuery(sql, null);

        if(datos.moveToFirst()){

             int id = Integer.parseInt(datos.getString(0));
                String title = datos.getString(1);
               String description = datos.getString(2);
    int deleted = Integer.parseInt(datos.getString(3));
                Datos data;
                data = new Datos(id, title, description, deleted);

                notas.add(data);



        }
        datos.close();
        database.close();

        return notas;

    }

    public void Delete(int id, Context context){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("UPDATE t_notas SET deleted = 1 WHERE id ="+id);
        db.close();

    }
    public void Update(int id,String title,String des, Context context){
        DbHelper dbHelper = new DbHelper(context);
       SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("description", des);
String selection = "id = ?";
String[] selectionArgs = {""+id};

int count = db.update("t_notas", values, selection, selectionArgs);


    }

}