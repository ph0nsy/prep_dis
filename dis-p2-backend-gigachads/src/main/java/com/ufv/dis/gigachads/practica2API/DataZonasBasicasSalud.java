package com.ufv.dis.gigachads.practica2API;

import java.util.List;

public class DataZonasBasicasSalud {

    private List<ZonasBasicasSalud> data;


    public DataZonasBasicasSalud() {
    }

    public DataZonasBasicasSalud(List<ZonasBasicasSalud> data) {
        this.data = data;
    }

    public List<ZonasBasicasSalud> getData() {
        return data;
    }

    public void setData(List<ZonasBasicasSalud> data) {
        this.data = data;
    }
}
