package com.example.fyg.calculadoray;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Calculadora extends AppCompatActivity {

    private TextView dato, operacion;
    private Button suma, resta, multiplicacion, division, punto, logaritmo, exponente, seno, raiz;
    private MediaPlayer mp;
    private Boolean extra;

    private Button uno, dos, tres, cuatro, cinco, seis, siete, ocho, nueve, cero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);

        uno = (Button) findViewById(R.id.uno);
        dos = (Button) findViewById(R.id.dos);
        tres = (Button) findViewById(R.id.tres);
        cuatro = (Button) findViewById(R.id.cuatro);
        cinco = (Button) findViewById(R.id.cinco);
        seis = (Button) findViewById(R.id.seis);
        siete = (Button) findViewById(R.id.siete);
        ocho = (Button) findViewById(R.id.ocho);
        nueve = (Button) findViewById(R.id.nueve);
        cero = (Button) findViewById(R.id.cero);
        punto = (Button) findViewById(R.id.punto);

        suma = (Button) findViewById(R.id.suma);
        resta = (Button) findViewById(R.id.resta);
        multiplicacion = (Button) findViewById(R.id.multiplicacion);
        division = (Button) findViewById(R.id.division);
        exponente = (Button) findViewById(R.id.exponente);

        seno = (Button) findViewById(R.id.seno);
        raiz = (Button) findViewById(R.id.raiz);
        logaritmo = (Button) findViewById(R.id.logaritmo);

        dato = (TextView) findViewById(R.id.dato);
        operacion = (TextView) findViewById(R.id.operacion);
        mp = MediaPlayer.create(this, R.raw.sonido);
        extra = false;
    }

    public void deshabilitaSignosComunes(){
        suma.setEnabled(false);
        resta.setEnabled(false);
        multiplicacion.setEnabled(false);
        division.setEnabled(false);
        exponente.setEnabled(false);
    }

    public void deshabilitaSignosExtras(){
        logaritmo.setEnabled(false);
        raiz.setEnabled(false);
        seno.setEnabled(false);
    }

    public void habilitaSignosComunes(){
        suma.setEnabled(true);
        resta.setEnabled(true);
        multiplicacion.setEnabled(true);
        division.setEnabled(true);
        punto.setEnabled(true);
        exponente.setEnabled(true);
    }

    public void habilitaSignosExtra(){
        logaritmo.setEnabled(true);
        raiz.setEnabled(true);
        seno.setEnabled(true);
    }

    public void numeros(View v){
        String cadena = "";
        mp.start();
        if(extra){
            deshabilitaSignosExtras();
            deshabilitaSignosComunes();
        }else
            habilitaSignosComunes();
            deshabilitaSignosExtras();

        if(v == uno){
            cadena = dato.getText().toString() + "1";
        }
        if(v == dos){
            cadena = dato.getText().toString() + "2";
        }
        if(v == tres){
            cadena = dato.getText().toString() + "3";
        }
        if(v == cuatro){
            cadena = dato.getText().toString() + "4";
        }
        if(v == cinco){
            cadena = dato.getText().toString() + "5";
        }
        if(v == seis){
            cadena = dato.getText().toString() + "6";
        }
        if(v == siete){
            cadena = dato.getText().toString() + "7";
        }
        if(v == ocho){
            cadena = dato.getText().toString() + "8";
        }
        if(v == nueve){
            cadena = dato.getText().toString() + "9";
        }
        if(v == cero){
            cadena = dato.getText().toString() + "0";
        }
        dato.setText(cadena);
    }

    public void signosComunes(View v){
        mp.start();
        String cadena = "";
        extra = false;

        deshabilitaSignosComunes();
        deshabilitaSignosExtras();
        punto.setEnabled(true);

        if(v==suma){
            cadena = dato.getText().toString() + "+";
        }
        if(v==resta){
            cadena = dato.getText().toString() + "-";
        }
        if(v==multiplicacion){
            cadena = dato.getText().toString() + "x";
        }
        if(v==division){
            cadena = dato.getText().toString() + "/";
        }
        if(v==exponente){
            cadena = dato.getText().toString() + "^";
        }
        dato.setText(cadena);
    }

    public void signosExtras(View v){
        mp.start();
        String cadena = "";
        extra = true;

        deshabilitaSignosComunes();
        deshabilitaSignosExtras();

        if(v==raiz){
            cadena = dato.getText().toString() + "√";
        }
        if(v==logaritmo){
            cadena = dato.getText().toString() + "ln";
        }
        if(v==seno){
            cadena = dato.getText().toString() + "sen";
        }
        dato.setText(cadena);
    }

    public void borrar(View view){
        mp.start();
        dato.setText("");
        habilitaSignosComunes();
        habilitaSignosExtra();
        extra = false;
    }

    public void punto(View view){
        mp.start();
        punto.setEnabled(false);
        deshabilitaSignosExtras();
        String cadena = dato.getText().toString() + ".";
        dato.setText(cadena);
    }

    public void enviaDatos(View view) {
        mp.start();
        String cadenaFinal = dato.getText().toString();

        if (cadenaFinal.isEmpty()) {
            Toast.makeText(this, "El campo esta vacio", Toast.LENGTH_SHORT).show();
        } else {
            String resultado;
            obtener obtener = new obtener();

            if (extra) {
                resultado = obtener.obtenerResultadoExtras(cadenaFinal);
            } else {
                resultado = obtener.obtenerResultado(cadenaFinal);
            }

            if (resultado.equals("errorSignos"))
                Toast.makeText(this, "Error: Revisa los signos, Nota: No calculo numeros negativos", Toast.LENGTH_SHORT).show();
            else if (resultado.equals("Infinity"))
                Toast.makeText(this, "Resultado fuera de rango", Toast.LENGTH_SHORT).show();
            else if (resultado.equals("ERROR"))
                Toast.makeText(this, "Ocurrio un error", Toast.LENGTH_SHORT).show();
                else {
                    operacion.setText(cadenaFinal);
                    dato.setText(resultado);
                    extra = false;
                    habilitaSignosComunes();
                }
        }
    }

}
