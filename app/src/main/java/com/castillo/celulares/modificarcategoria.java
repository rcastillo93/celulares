package com.castillo.celulares;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class modificarcategoria extends AppCompatActivity {

    EditText et_categoria;

    int id;
    String categoria;
    Button btmodificar,bteliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificarcategoria);


        Bundle b = getIntent().getExtras();
        if(b!=null){
            id= b.getInt("id");
            categoria=b.getString("categoria");
        }
        et_categoria = (EditText) findViewById(R.id.et_cat);
        et_categoria.setText(categoria);

        btmodificar = (Button) findViewById(R.id.bt_modificarcat);

        bteliminar = (Button) findViewById(R.id.bt_eliminar);

        btmodificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificar(id,et_categoria.getText().toString());
               onBackPressed();
            }
        });
        bteliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminar(id);
                onBackPressed();
            }
        });

    }
    private void modificar(int pid, String nuevacategoria){
        DbHelper h = new DbHelper(this);
        SQLiteDatabase db = h.getWritableDatabase();
        String sql = "update categorias set categoria='"+nuevacategoria+"' where id ="+pid;
        db.execSQL(sql);
        db.close();

    }

    private void eliminar(int pid){
        DbHelper h = new DbHelper(this);
        SQLiteDatabase db = h.getWritableDatabase();
        String sql = "delete from categorias where id ="+pid;
        db.execSQL(sql);
        db.close();

    }
}













