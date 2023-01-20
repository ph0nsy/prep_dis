package com.ufv.dis.gigachads.practica2API;

public class ZonasBasicasSalud {
    private String codigo_geometria;
    private String zona_basica_salud;
    private Number tasa_incidencia_acumulada_ultimos_14dias;
    private Number tasa_incidencia_acumulada_total;
    private Number casos_confirmados_totales;
    private Number casos_confirmados_ultimos_14dias;
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

    public Number getTasa_incidencia_acumulada_ultimos_14dias() {
        return tasa_incidencia_acumulada_ultimos_14dias;
    }

    public void setTasa_incidencia_acumulada_ultimos_14dias(Number tasa_incidencia_acumulada_ultimos_14dias) {
        this.tasa_incidencia_acumulada_ultimos_14dias = tasa_incidencia_acumulada_ultimos_14dias;
    }

    public Number getTasa_incidencia_acumulada_total() {
        return tasa_incidencia_acumulada_total;
    }

    public void setTasa_incidencia_acumulada_total(Number tasa_incidencia_acumulada_total) {
        this.tasa_incidencia_acumulada_total = tasa_incidencia_acumulada_total;
    }

    public Number getCasos_confirmados_totales() {
        return casos_confirmados_totales;
    }

    public void setCasos_confirmados_totales(Number casos_confirmados_totales) {
        this.casos_confirmados_totales = casos_confirmados_totales;
    }

    public String getFecha_informe() {
        return fecha_informe;
    }

    public void setFecha_informe(String fecha_informe) {
        this.fecha_informe = fecha_informe;
    }

    public Number getCasos_confirmados_ultimos_14dias() {
        return casos_confirmados_ultimos_14dias;
    }

    public void setCasos_confirmados_ultimos_14dias(Number casos_confirmados_ultimos_14dias) {
        this.casos_confirmados_ultimos_14dias = casos_confirmados_ultimos_14dias;
    }

    public ZonasBasicasSalud() {
    }

    public ZonasBasicasSalud(String codigo_geometria, String zona_basica_salud, Number tasa_incidencia_acumulada_ultimos_14dias, Number tasa_incidencia_acumulada_total, Number casos_confirmados_totales, Number casos_confirmados_ultimos_14dias, String fecha_informe) {
        this.codigo_geometria = codigo_geometria;
        this.zona_basica_salud = zona_basica_salud;
        this.tasa_incidencia_acumulada_ultimos_14dias = tasa_incidencia_acumulada_ultimos_14dias;
        this.tasa_incidencia_acumulada_total = tasa_incidencia_acumulada_total;
        this.casos_confirmados_totales = casos_confirmados_totales;
        this.casos_confirmados_ultimos_14dias = casos_confirmados_ultimos_14dias;
        this.fecha_informe = fecha_informe;
    }
}
