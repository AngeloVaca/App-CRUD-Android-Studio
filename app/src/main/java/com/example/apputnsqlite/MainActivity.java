package com.example.apputnsqlite;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView txtID,txtName,txtLastname,txtAge,txtCountry;
    autores lsAutores;
    TextView lblTitulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;


        });
        txtID = findViewById(R.id.txtID);
        txtName = findViewById(R.id.txtName);
        txtLastname = findViewById(R.id.txtLastname);
        txtAge = findViewById(R.id.txtAge);
        txtCountry = findViewById(R.id.txtCountry);
        lsAutores = new autores(this,"Biblioteca.db",1);
        lblTitulo = findViewById(R.id.lblTitulo);

        //leer los datos del Diccionario extra
        Bundle extra = getIntent().getExtras();
        lblTitulo.setText("BIENVENIDO  " + extra.getString("usuario"));
    }


    public void cmd_Crear_onClick(View v){
        Autor r = lsAutores.Create(
                Integer.parseInt(txtID.getText().toString()),
                txtName.getText().toString(),
                txtLastname.getText().toString(),
                txtCountry.getText().toString(),
                Integer.parseInt(txtAge.getText().toString())
        );
        if( r != null ) {
            Toast.makeText(this, "Autor creado correctamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error al crear Autor!!", Toast.LENGTH_SHORT).show();
        }

    }
    public void cmdActualizar_onClick(View v){
        Autor r = lsAutores.Update(
                Integer.parseInt(txtID.getText().toString()),
                txtName.getText().toString(),
                txtLastname.getText().toString(),
                txtCountry.getText().toString(),
                Integer.parseInt(txtAge.getText().toString())
        );
        if( r != null ) {
            Toast.makeText(this, "Autor actualizado correctamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error al actualizar Autor!!", Toast.LENGTH_SHORT).show();
        }
    }

    public void cmdLeer_onClick(View v){
        Autor r =lsAutores.Read_ById(Integer.parseInt(txtID.getText().toString()));
        if( r != null ) {
            txtName.setText(r.Nombres);
            txtLastname.setText(r.Apellidos);
            txtCountry.setText(r.IsoPais);
            txtAge.setText(String.valueOf(r.Edad));

        } else {
            Toast.makeText(this, "Error No ENCONTRADO Autor!!", Toast.LENGTH_SHORT).show();
        }

    }
    public void cmdDelete_onClick(View v){
        boolean r = lsAutores.Delete(Integer.parseInt(txtID.getText().toString()));
        if( r ) {
            txtName.setText("");
            txtLastname.setText("");
            txtCountry.setText("");
            txtAge.setText("");
            Toast.makeText(this, "Autor eliminado correctamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error al eliminar Autor!!", Toast.LENGTH_SHORT).show();
        }

    }
    public void cmdRegresar_onClick(View v){
        finish();
    }




}