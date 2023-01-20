package com.ufv.dis.gigachads.practica2API;

import org.junit.jupiter.api.Test;


import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

class GestionDatosTest {

    Lectorjson lectorjson = new Lectorjson();
    List<ZonasBasicasSaludMayores> listaZonasBasicasMayores = lectorjson.leeFicheroJson();
    List<ZonasBasicasSalud> listaZonasBasicas = lectorjson.leeFicheroJsonSaludBasicas();
    GestionDatos gDatos = new GestionDatos();

    ZonasBasicasSaludMayores zonaMayoresNombre = new ZonasBasicasSaludMayores("2000","Simancas",219.31,24,"2022/04/05 11:34:00");
    ZonasBasicasSaludMayores zonaMayoresMax = new ZonasBasicasSaludMayores("2000","asdfqwertyqqq",219.31,24,"2022/04/05 11:34:00");

    ZonasBasicasSalud zonaNombre = new ZonasBasicasSalud("2000","Abrantes", 4.25224404839339, 1084.7001458874,14,422,"2022/04/05 11:34:00");
    ZonasBasicasSalud zonaMax = new ZonasBasicasSalud("2000","asdfweewerty", 8.25224404839339,75,4545.22226,47,"2022/04/05 11:34:00");



    @Test
    void gestionarCodigoGeometria_maximo() {

        int max_cod_geo=0;
        int una_vez=1;

        for (ZonasBasicasSaludMayores zona: listaZonasBasicasMayores) {
            int cod_geometria = Integer.parseInt(zona.getCodigo_geometria());
            if (una_vez == 1) {
                max_cod_geo = cod_geometria;
                una_vez = 0;
            } else {
                if (cod_geometria > max_cod_geo) {
                    max_cod_geo = cod_geometria;
                }
            }
        }

        gDatos.gestionarCodigoGeometria(listaZonasBasicasMayores,zonaMayoresMax);
        int cod_geometria = Integer.parseInt(zonaMayoresMax.getCodigo_geometria());
        assertEquals(max_cod_geo+1,cod_geometria);
    }

    @Test
    void gestionarCodigoGeometria_nombre() {

        gDatos.gestionarCodigoGeometria(listaZonasBasicasMayores,zonaMayoresNombre);
        int cod_geometria = Integer.parseInt(zonaMayoresNombre.getCodigo_geometria());
        assertEquals(253,cod_geometria);
    }

    @Test
    void gestionarCodigoGeometriaBasicas_maximo() {

        int max_cod_geo=0;
        int una_vez=1;

        for (ZonasBasicasSalud zona: listaZonasBasicas) {
            int cod_geometria = Integer.parseInt(zona.getCodigo_geometria());
            if (una_vez == 1) {
                max_cod_geo = cod_geometria;
                una_vez = 0;
            } else {
                if (cod_geometria > max_cod_geo) {
                    max_cod_geo = cod_geometria;
                }
            }
        }

        gDatos.gestionarCodigoGeometriaBasicas(listaZonasBasicas,zonaMax);
        int cod_geometria = Integer.parseInt(zonaMax.getCodigo_geometria());
        assertEquals(max_cod_geo+1,cod_geometria);
    }

    @Test
    void gestionarCodigoGeometriaBasicas_nombre() {

        gDatos.gestionarCodigoGeometriaBasicas(listaZonasBasicas,zonaNombre);
        int cod_geometria = Integer.parseInt(zonaNombre.getCodigo_geometria());
        assertEquals(1,cod_geometria);
    }
}