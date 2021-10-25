package com.example.notassqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;

import android.widget.EditText;


import com.example.notassqlite.db.Datos;

import java.util.ArrayList;

public class ReadActivity extends AppCompatActivity {
EditText txtTitle, txtDes;

    MainActivity ma = new MainActivity();
    String title;
    String des;

    int option;
int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
txtTitle = findViewById(R.id.editTitle);
txtDes = findViewById(R.id.editDes);





getSupportActionBar().setDisplayHomeAsUpEnabled(true);



       option = Integer.parseInt(getIntent().getStringExtra("option"));






    }


public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.second, menu);
        switch (option){
            case 1:
                menu.getItem(0).setEnabled(false);
                break;
            case 2:
                id = Integer.parseInt(getIntent().getStringExtra("id"));
                menu.getItem(0).setEnabled(true);
                ArrayList<Datos> data = ma.getDataID(id, ReadActivity.this);

                txtTitle.setText(data.get(0).getTitle());
                txtDes.setText(data.get(0).getDescription());
                break;
        }

        return true;
}

public boolean onOptionsItemSelected(MenuItem item) {
    int id2 = item.getItemId();

    if (id2 == R.id.save) {

       // Toast.makeText(ReadActivity.this, "GUARDAR", Toast.LENGTH_SHORT).show();

        switch (option){
            case 1:
                title = txtTitle.getText().toString();
                des = txtDes.getText().toString();
                if(title.length() >= 1|| des.length() >= 1)
                {

                    ma.Agregar(title, des, ReadActivity.this);
                    AlertDialog.Builder alert = new AlertDialog.Builder(ReadActivity.this);
                    alert.setTitle("Guardado Exitoso")
                            .setMessage("La Nota se ha guardado correctamente")
                            .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent in = new Intent(ReadActivity.this, MainActivity.class);
                                    startActivity(in);
                                }
                            }).create().show();
                }
                else
                {

                    AlertDialog.Builder alert = new AlertDialog.Builder(ReadActivity.this);
                    alert.setTitle("Campos Vacios")
                            .setMessage("Al menos uno de los campos debe ser llenado")
                            .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            }).create().show();
                }

                break;
            case 2:


                title = txtTitle.getText().toString();
                des = txtDes.getText().toString();
                if(title.length() >= 1|| des.length() >= 1)
                {

                    ma.Update(id,title, des, ReadActivity.this);
                    AlertDialog.Builder alert = new AlertDialog.Builder(ReadActivity.this);
                    alert.setTitle("Guardado Exitoso")
                            .setMessage("La Nota se ha guardado correctamente")
                            .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent in = new Intent(ReadActivity.this, MainActivity.class);
                                    startActivity(in);
                                }
                            }).create().show();
                }
                else
                {

                    AlertDialog.Builder alert = new AlertDialog.Builder(ReadActivity.this);
                    alert.setTitle("Campos Vacios")
                            .setMessage("Al menos uno de los campos debe ser llenado")
                            .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            }).create().show();
                }

                break;
        }
    }
   else if (id2 == R.id.delete) {
        //Toast.makeText(ReadActivity.this, "BORRAR", Toast.LENGTH_SHORT).show();

       ma.Delete(id, ReadActivity.this);
        AlertDialog.Builder alert = new AlertDialog.Builder(ReadActivity.this);
        alert.setTitle("Nota Eliminada!")
                .setMessage("La Nota se ha Eliminado correctamente")
                .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent in = new Intent(ReadActivity.this, MainActivity.class);
                        startActivity(in);
                    }
                }).create().show();
    }

return super.onOptionsItemSelected(item);

}




}