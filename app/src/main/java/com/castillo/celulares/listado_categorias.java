package com.castillo.celulares;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class listado_categorias extends AppCompatActivity {
    ListView lista;
    ArrayList<String> listado;

    @Override
    protected void onPostResume() {
        super.onPostResume();
        cargalistado();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_categorias);
        lista = (ListView) findViewById(R.id.lvcategoria);

        cargalistado();
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(listado_categorias.this,listado.get(i),Toast.LENGTH_SHORT).show();
                int clave = Integer.parseInt(listado.get(i).split(" ")[0]);
                String categoria = listado.get(i).split(" ")[1];
                Intent intent = new Intent(listado_categorias.this,modificarcategoria.class);
                intent.putExtra("id",clave);
                intent.putExtra("categoria",categoria);
                startActivity(intent);
            }
        });
    }
private void cargalistado(){
    listado = lista_categorias();
    ArrayAdapter<String>adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listado);
    lista.setAdapter(adapter);

}
    private ArrayList<String> lista_categorias(){
        ArrayList<String> datos = new ArrayList<String>();
        DbHelper h = new DbHelper(this);
        SQLiteDatabase db = h.getReadableDatabase();
        String sql="select * from categorias";
        Cursor c = db.rawQuery(sql,null);
        if (c.moveToFirst()){
            do{

                String linea = c.getInt(0)+" "+c.getString(1);
                datos.add(linea);

            }while (c.moveToNext());

        }
        db.close();
        return datos;
    }
}
