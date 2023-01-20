package com.ufv.dis.gigachads.practica2API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Lectorjson {
    public List<ZonasBasicasSaludMayores> leeFicheroJson(){

        try {
            //lee el fichero que le pasamos y lo carga en un reader
            BufferedReader reader = new BufferedReader(new
                    InputStreamReader(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Covid19-TIA_ZonasBásicasSalud_Mayores60.json"))));
            // convierte el array JSON a un arraylist de Zonas basicas de salud
            DataZonasBasicasSaludMayores listaZonasBasicas =
                    new Gson().fromJson(reader, new TypeToken<DataZonasBasicasSaludMayores>()
                    {}.getType());
            reader.close();// close reader
            return listaZonasBasicas.getData();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return new ArrayList<>(); //si no ha leido nada, devuelve un array vacio
        }
    }

    public List<ZonasBasicasSalud> leeFicheroJsonSaludBasicas(){

        try {
            //lee el fichero que le pasamos y lo carga en un reader
            BufferedReader reader = new BufferedReader(new
                    InputStreamReader(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Covid19-TIA_ZonasBásicasSalud.json"))));
            // convierte el array JSON a un arraylist de Zonas basicas de salud
            DataZonasBasicasSalud listaZonasBasicas =
                    new Gson().fromJson(reader, new TypeToken<DataZonasBasicasSalud>()
                    {}.getType());
            reader.close();// close reader
            return listaZonasBasicas.getData();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return new ArrayList<>(); //si no ha leido nada, devuelve un array vacio
        }
    }


    public void actualizarJson (List<ZonasBasicasSaludMayores> nuevaZona){


        try {
            //lee el fichero que le pasamos y lo carga en un reader

            BufferedWriter contenido = new BufferedWriter(new FileWriter("./src/main/resources/Covid19-TIA_ZonasBásicasSalud_Mayores60.json",false));
            // convierte el array JSON a un arraylist de Zonas basicas de salud
            DataZonasBasicasSaludMayores listaZonasBasicas = new DataZonasBasicasSaludMayores(nuevaZona);

            new GsonBuilder()
                    .setPrettyPrinting()
                    .create()
                    .toJson(listaZonasBasicas, contenido);



            contenido.close();// close writer

        } catch (Exception ex) {

            System.out.println(ex.getMessage());
            throw new RuntimeException(ex);
            //si no ha escrito nada,  array devuelve una excepcion
        }
    }



    public void actualizarJsonBasicas (List<ZonasBasicasSalud> nuevaZona){


        try {
            //lee el fichero que le pasamos y lo carga en un reader

            BufferedWriter contenido = new BufferedWriter(new FileWriter("./src/main/resources/Covid19-TIA_ZonasBásicasSalud.json",false));
            // convierte el array JSON a un arraylist de Zonas basicas de salud
            DataZonasBasicasSalud listaZonasBasicas = new DataZonasBasicasSalud(nuevaZona);

            new GsonBuilder()
                    .setPrettyPrinting()
                    .create()
                    .toJson(listaZonasBasicas, contenido);



            contenido.close();// close writer

        } catch (Exception ex) {

            System.out.println(ex.getMessage());
            throw new RuntimeException(ex);
            //si no ha escrito nada,  array devuelve una excepcion
        }
    }
}
