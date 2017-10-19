package com.castillo.celulares;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText categoria;
    Button btguardar,btmostrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        categoria = (EditText)findViewById(R.id.txtcategoriaedit);
        btguardar = (Button) findViewById(R.id.bt_modificar);
        btmostrar = (Button) findViewById(R.id.bt_ver);
        btguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarcategoria(categoria.getText().toString());
            }
        });
        btmostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,listado_categorias.class));
            }
        });
    }

    public void guardarcategoria(String categoria){

        DbHelper h = new DbHelper(this);
        SQLiteDatabase db = h.getWritableDatabase();
        try{
            ContentValues c = new ContentValues();
            c.put("categoria",categoria);
            db.insert("categorias",null,c);
            db.close();
            Toast.makeText(this,"Guardado",Toast.LENGTH_SHORT).show();
        }catch (Exception e){

            Toast.makeText(this,"Error" + e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

}
