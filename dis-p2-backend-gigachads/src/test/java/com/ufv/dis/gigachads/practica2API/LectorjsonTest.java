package com.ufv.dis.gigachads.practica2API;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LectorjsonTest {



    Lectorjson lectorjson = new Lectorjson();
    List<ZonasBasicasSaludMayores> listaZonasBasicasMayores = null;
    List<ZonasBasicasSalud> listaZonasBasicas = null;

    @Test
    void leeFicheroJson() {
        List<ZonasBasicasSaludMayores> listaZonasBasicasMayoresfich = lectorjson.leeFicheroJson();



    }

    @Test
    void leeFicheroJsonSaludBasicas() {
        List<ZonasBasicasSalud> listaZonasBasicasfich = lectorjson.leeFicheroJsonSaludBasicas();
    }
}