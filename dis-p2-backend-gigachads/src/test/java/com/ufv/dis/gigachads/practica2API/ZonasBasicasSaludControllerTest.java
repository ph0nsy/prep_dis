package com.ufv.dis.gigachads.practica2API;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;
import com.ufv.dis.gigachads.practica2API.ZonasBasicasSaludMayores;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ZonasBasicasSaludControllerTest {

    Lectorjson lectorjson = new Lectorjson();
    List<ZonasBasicasSaludMayores> listaZonasBasicasMayores = lectorjson.leeFicheroJson();
    List<ZonasBasicasSalud> listaZonasBasicas = lectorjson.leeFicheroJsonSaludBasicas();
    ZonasBasicasSaludController zController ;

    ZonasBasicasSaludMayores zonaMayoresNombre = new ZonasBasicasSaludMayores("2000","Simancas",219.31,24,"2022/04/05 11:34:00");
    ZonasBasicasSaludMayores zonaMayoresMax = new ZonasBasicasSaludMayores("2000","asdfqwerty",219.31,24,"2022/04/05 11:34:00");

    ZonasBasicasSalud zonaNombre = new ZonasBasicasSalud("2000","Abrantes", 4.25224404839339, 1084.7001458874,14,45,"2022/04/05 11:34:00");
    ZonasBasicasSalud zonaMax = new ZonasBasicasSalud("2000","asdfweewerty", 8.25224404839339,75,4545.22226,34,"2022/04/05 11:34:00");

    @Test
    boolean zonasBasicas() {

        List<ZonasBasicasSaludMayores> listaFuncion = zController.zonasBasicas();
        boolean isEqual = Objects.equals(listaZonasBasicasMayores,listaFuncion);
        return isEqual;
        //assertEquals(listaZonasBasicasMayores,listaFuncion);
    }

    @Test
    boolean zonasBasicasSalud() {
        List<ZonasBasicasSalud> listaFuncion = zController.zonasBasicasSalud();
        boolean isEqual = Objects.equals(listaZonasBasicas,listaFuncion);
        return isEqual;
    }
/*
    @Test
    void postMayores() {
       List<ZonasBasicasSaludMayores> listaPostMayores = zController.postMayores(3,"022","pepon",11,11,"2022/04/05 11:34:00 ");
       assertEquals(listaPostMayores.get(3).getCodigo_geometria(),listaZonasBasicasMayores.get(3).getCodigo_geometria());

    }*/

    @Test
    void postBasicasCrear() {

    }

    @Test
    void postBasicasEditar() {
    }
}