package com.ufv.plantilla.clases;

import java.util.Date;

public class ZonasBasicasSaludMayores {

private String codigo_geometria;
private String zona_basica_salud;
private Number tasa_incidencia_acumulada_P60mas_ultimos_14dias;
private Number casos_confirmados_P60mas_ultimos_14dias;
private String fecha_informe;

    public String getCodigo_geometria() {
        return codigo_geometria;
    }

    public void setCodigo_geometria(String codigo_geometria) {
        this.codigo_geometria = codigo_geometria;
    }

    public String getZona_basica_salud() {
        return zona_basica_salud;
    }

    public void setZona_basica_salud(String zona_basica_salud) {
        this.zona_basica_salud = zona_basica_salud;
    }

    public Number getTasa_incidencia_acumulada_P60mas_ultimos_14dias() {
        return tasa_incidencia_acumulada_P60mas_ultimos_14dias;
    }

    public void setTasa_incidencia_acumulada_P60mas_ultimos_14dias(Number tasa_incidencia_acumulada_P60mas_ultimos_14dias) {
        this.tasa_incidencia_acumulada_P60mas_ultimos_14dias = tasa_incidencia_acumulada_P60mas_ultimos_14dias;
    }

    public Number getCasos_confirmados_P60mas_ultimos_14dias() {
        return casos_confirmados_P60mas_ultimos_14dias;
    }

    public void setCasos_confirmados_P60mas_ultimos_14dias(Number casos_confirmados_P60mas_ultimos_14dias) {
        this.casos_confirmados_P60mas_ultimos_14dias = casos_confirmados_P60mas_ultimos_14dias;
    }

    public String getFecha_informe() {
        return fecha_informe;
    }

    public void setFecha_informe(String fecha_informe) {
        this.fecha_informe = fecha_informe;
    }

    public ZonasBasicasSaludMayores(String codigo_geometria, String zona_basica_salud, Number tasa_incidencia_acumulada_P60mas_ultimos_14dias, Number casos_confirmados_P60mas_ultimos_14dias, String fecha_informe) {
        this.codigo_geometria = codigo_geometria;
        this.zona_basica_salud = zona_basica_salud;
        this.tasa_incidencia_acumulada_P60mas_ultimos_14dias = tasa_incidencia_acumulada_P60mas_ultimos_14dias;
        this.casos_confirmados_P60mas_ultimos_14dias = casos_confirmados_P60mas_ultimos_14dias;
        this.fecha_informe = fecha_informe;
    }

    public ZonasBasicasSaludMayores() {
    }
}
