package com.ufv.dis.gigachads.practica2API;

import java.util.List;

public class DataZonasBasicasSaludMayores {

    private List<ZonasBasicasSaludMayores> data;


    public DataZonasBasicasSaludMayores() {
    }

    public DataZonasBasicasSaludMayores(List<ZonasBasicasSaludMayores> data) {
        this.data = data;
    }

    public List<ZonasBasicasSaludMayores> getData() {
        return data;
    }

    public void setData(List<ZonasBasicasSaludMayores> data) {
        this.data = data;
    }
}
