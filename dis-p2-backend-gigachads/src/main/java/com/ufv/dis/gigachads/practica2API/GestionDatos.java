package com.ufv.dis.gigachads.practica2API;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class GestionDatos {

    ZonasBasicasSaludMayores getZonasInfobyCodigoGeometria(String codigo_geometria){
        ZonasBasicasSaludMayores zonaEncontrada = null;
         Lectorjson lector = new Lectorjson ();

        List<ZonasBasicasSaludMayores> listazonas = lector.leeFicheroJson();
        for (ZonasBasicasSaludMayores zona: listazonas){
            if(zona.getCodigo_geometria().equals(codigo_geometria)){
                zonaEncontrada =zona;
            }
        }
        return zonaEncontrada;
    }

    ZonasBasicasSaludMayores getZonasInfobyZonaBasicaSalud(String zona_basica_salud){
        ZonasBasicasSaludMayores zonaEncontrada = null;
        Lectorjson lector = new Lectorjson ();

        List<ZonasBasicasSaludMayores> listazonas = lector.leeFicheroJson();
        for (ZonasBasicasSaludMayores zona: listazonas){
            if(zona.getZona_basica_salud().equals(zona_basica_salud)){
                zonaEncontrada =zona;
            }
        }
        return zonaEncontrada;
    }


    ZonasBasicasSaludMayores gestionarCodigoGeometria(List<ZonasBasicasSaludMayores> listaZonasSalud,ZonasBasicasSaludMayores zonaSalud){



        int max_cod_geo=0;
        int una_vez=1;
        int nuevaZona=1;
        for (ZonasBasicasSaludMayores zona: listaZonasSalud){
            int cod_geometria = Integer.parseInt(zona.getCodigo_geometria());
            if (una_vez==1){
                max_cod_geo = cod_geometria;
                una_vez=0;
            }
            else {if (cod_geometria>max_cod_geo){
                max_cod_geo=cod_geometria;
            }}

            if (zona.getZona_basica_salud().equalsIgnoreCase(zonaSalud.getZona_basica_salud())){

                zonaSalud.setCodigo_geometria(zona.getCodigo_geometria());
                nuevaZona=0;
            }


        }
        if (nuevaZona==1){
            max_cod_geo = max_cod_geo+1;
            String max_cod_geo_str= Integer.toString(max_cod_geo);
            zonaSalud.setCodigo_geometria(max_cod_geo_str);
        }

        return  zonaSalud;
    }

    ZonasBasicasSalud gestionarCodigoGeometriaBasicas(List<ZonasBasicasSalud> listaZonasSalud,ZonasBasicasSalud zonaSalud){



        int max_cod_geo=0;
        int una_vez=1;
        int nuevaZona=1;
        for (ZonasBasicasSalud zona: listaZonasSalud){
            int cod_geometria = Integer.parseInt(zona.getCodigo_geometria());
            if (una_vez==1){
                max_cod_geo = cod_geometria;
                una_vez=0;
            }
            else {if (cod_geometria>max_cod_geo){
                max_cod_geo=cod_geometria;
            }}

            if (zona.getZona_basica_salud().equalsIgnoreCase(zonaSalud.getZona_basica_salud())){

                zonaSalud.setCodigo_geometria(zona.getCodigo_geometria());
                nuevaZona=0;
            }


        }
        if (nuevaZona==1){
            max_cod_geo = max_cod_geo+1;
            String max_cod_geo_str= Integer.toString(max_cod_geo);
            zonaSalud.setCodigo_geometria(max_cod_geo_str);
        }

        return  zonaSalud;
    }



}
