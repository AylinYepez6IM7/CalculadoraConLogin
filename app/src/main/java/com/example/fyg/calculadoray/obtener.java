package com.example.fyg.calculadoray;

import android.util.Log;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class obtener {
    String nume = "";
    String resultado = "";

    public String obtenerResultadoExtras(String cadena){

        String numeros = "", signo = "";
        Float txtNumeros;

        try{

            for (int i = 0; i < cadena.length(); i++) {
                String letra = String.valueOf(cadena.charAt(i));
                if(letra.equals("√") || letra.equals("l") || letra.equals("n") || letra.equals("s") || letra.equals("e") || letra.equals("n")){
                    signo = signo + letra;
                }else
                    numeros = numeros + letra;

            }//Fin del for

            txtNumeros = Float.parseFloat(numeros);

            //RESOLVIENDO

            if(signo.equals("√")){
                resultado = Double.toString(Math.sqrt(txtNumeros));
            }else
                if(signo.equals("ln")){
                    resultado = Double.toString(Math.log(txtNumeros));
                }else
                    if(signo.equals("sen")){
                        resultado = Double.toString(Math.sin((txtNumeros * Math.PI)/180));
                    }

        }catch(Exception e){
            resultado = "ERROR";
            Log.w("Obtener", e.toString());
        }

        return resultado;
    }

    public String obtenerResultado(String cadena){
        try{

            ArrayList<Float> numeros = new ArrayList<Float>();
            ArrayList<String> signos = new ArrayList<String>();
            float resu = 0;

            int ultimoIndex = cadena.length() - 1;
            String ultimaCifra = String.valueOf(cadena.charAt(ultimoIndex));
            String primeraCifra = String.valueOf(cadena.charAt(0));

            if(ultimaCifra.equals("+") || primeraCifra.equals("+") || ultimaCifra.equals("-") || primeraCifra.equals("-") || ultimaCifra.equals("x") || primeraCifra.equals("x") || ultimaCifra.equals("/") || primeraCifra.equals("/") || ultimaCifra.equals("^") || primeraCifra.equals("^")){
                resultado = "errorSignos";
            }else {
                for (int i = 0; i < cadena.length(); i++) {

                    String letra = String.valueOf(cadena.charAt(i));

                    if(letra.equals("+") || letra.equals("-") || letra.equals("x") || letra.equals("/") || letra.equals("^")) {
                        if (!(nume.equals(""))) {
                            numeros.add(Float.parseFloat(nume));
                            nume = "";
                        }
                        if(letra.equals("+"))
                            signos.add("+");
                        else
                            if(letra.equals("-"))
                                signos.add("-");
                            else
                                if(letra.equals("x"))
                                    signos.add("x");
                                else
                                    if(letra.equals("/"))
                                        signos.add("/");
                                    else
                                        if(letra.equals("^"))
                                            signos.add("^");
                    }else
                        nume = nume + letra;

                }//Fin del for
                if (!(nume.equals(""))) {
                    numeros.add(Float.parseFloat(nume));
                    nume = "";
                }

                //RESOLVIENDO
                for (int i = 0; i < signos.size(); i++) {
                    String signo = signos.get(i);
                    if(resu == 0) {
                        if(signo.equals("+"))
                            resu = (float) (numeros.get(i) + numeros.get(i + 1));
                        else
                            if(signo.equals("-"))
                                resu = (float) (numeros.get(i) - numeros.get(i + 1));
                            else
                                if(signo.equals("x"))
                                    resu = (float) (numeros.get(i) * numeros.get(i + 1));
                                else
                                    if(signo.equals("/"))
                                        resu = (float) (numeros.get(i) / numeros.get(i + 1));
                                    else
                                        if(signo.equals("^"))
                                            resu = (float)(Math.pow(numeros.get(i),numeros.get(i + 1)));
                    }else {
                        if(signo.equals("+"))
                            resu = (float) (resu + numeros.get(i + 1));
                        else
                            if(signo.equals("-"))
                                resu = (float) (resu - numeros.get(i + 1));
                            else
                                if(signo.equals("x"))
                                    resu = (float) (resu * numeros.get(i + 1));
                                else
                                    if(signo.equals("/"))
                                        resu = (float) (resu / numeros.get(i + 1));
                                    else
                                        if(signo.equals("^"))
                                            resu = (float)(Math.pow(resu,numeros.get(i + 1)));
                    }
                }//Fin del for
                resultado = Float.toString(resu);
            }
        }catch(Exception e){
            resultado = "ERROR";
        }
        return resultado;
    }
}
