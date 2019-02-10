package com.example.fyg.calculadoray;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText usuario;
    private EditText contraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void boton(View v){
        usuario = (EditText) findViewById(R.id.usuario);
        contraseña = (EditText) findViewById(R.id.contraseña);
        if(usuario.getText().toString().equals("FYG") && contraseña.getText().toString().equals("2425")){
            Toast.makeText(this, "Bienvenido...!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, Calculadora.class);
            startActivity(intent);
        }else
            Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
    }
}
