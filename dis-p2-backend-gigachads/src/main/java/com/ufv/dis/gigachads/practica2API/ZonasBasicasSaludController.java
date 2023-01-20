package com.ufv.dis.gigachads.practica2API;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Zonas Básicas Salud", description = "")
public class ZonasBasicasSaludController {

    Lectorjson lectorjson = new Lectorjson();
    List<ZonasBasicasSaludMayores> listaZonasBasicasMayores = lectorjson.leeFicheroJson();
    List<ZonasBasicasSalud> listaZonasBasicas = lectorjson.leeFicheroJsonSaludBasicas();
    GestionDatos gDatos = new GestionDatos();

    @GetMapping("/ZonasBasicasMayores")
    @Operation(summary = "Leer el archivo Covid19-TIA_ZonasBásicasSalud_Mayores.json", description = "Get request que devuelve una lista de tipo ZonasBasicasSaludMayores")
    public List<ZonasBasicasSaludMayores> zonasBasicas() {
        return lectorjson.leeFicheroJson();

    }

    @GetMapping("/ZonasBasicas")
    @Operation(summary = "Leer el archivo Covid19-TIA_ZonasBásicasSalud.json", description = "Get request que devuelve una lista de tipo ZonasBasicasSalud")
    public List<ZonasBasicasSalud> zonasBasicasSalud() {
        return lectorjson.leeFicheroJsonSaludBasicas();

    }


    @PostMapping(path = "/ZonasBasicasSaludMayores",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Pide modificar un elemento del archivo Covid19-TIA_ZonasBásicasSalud_Mayores.json", description = "Post request que modifica un elemento de la lista de tipo ZonasBasicasSaludMayores")
    public ResponseEntity<List<ZonasBasicasSaludMayores>> postMayores(@RequestBody int posicion, String codigo_geometria, String zona_basica_salud, Number tasa_incidencia_acumulada_P60mas_ultimos_14dias, Number casos_confirmados_P60mas_ultimos_14dias, String fecha_informe) {

        listaZonasBasicasMayores.get(posicion).setCodigo_geometria(codigo_geometria);
        listaZonasBasicasMayores.get(posicion).setZona_basica_salud(zona_basica_salud);
        listaZonasBasicasMayores.get(posicion).setTasa_incidencia_acumulada_P60mas_ultimos_14dias(tasa_incidencia_acumulada_P60mas_ultimos_14dias);
        listaZonasBasicasMayores.get(posicion).setCasos_confirmados_P60mas_ultimos_14dias(casos_confirmados_P60mas_ultimos_14dias);
        listaZonasBasicasMayores.get(posicion).setFecha_informe(fecha_informe);

        gDatos.gestionarCodigoGeometria(listaZonasBasicasMayores, listaZonasBasicasMayores.get(posicion));

        lectorjson.actualizarJson(listaZonasBasicasMayores);


        return new ResponseEntity<>(listaZonasBasicasMayores, HttpStatus.CREATED);
    }

    @PostMapping(path = "/ZonasBasicasSalud",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Pide añadir un elemento al archivo Covid19-TIA_ZonasBásicasSalud_Mayores.json", description = "Post request que añade un nuevo elemento a la lista de tipo ZonasBasicasSaludMayores")
    public ResponseEntity<List<ZonasBasicasSalud>> postBasicasCrear(@RequestBody ZonasBasicasSalud zonaBasicas) {

        ZonasBasicasSalud newZonaBM = new ZonasBasicasSalud();
        newZonaBM.setCodigo_geometria(zonaBasicas.getCodigo_geometria());
        newZonaBM.setZona_basica_salud(zonaBasicas.getZona_basica_salud());
        newZonaBM.setTasa_incidencia_acumulada_total(zonaBasicas.getTasa_incidencia_acumulada_ultimos_14dias());
        newZonaBM.setTasa_incidencia_acumulada_total(zonaBasicas.getTasa_incidencia_acumulada_total());
        newZonaBM.setCasos_confirmados_totales(zonaBasicas.getCasos_confirmados_totales());
        newZonaBM.setCasos_confirmados_ultimos_14dias(zonaBasicas.getCasos_confirmados_ultimos_14dias());
        newZonaBM.setFecha_informe(zonaBasicas.getFecha_informe());

        gDatos.gestionarCodigoGeometriaBasicas(listaZonasBasicas, newZonaBM);
        listaZonasBasicas.add(newZonaBM);
        lectorjson.actualizarJsonBasicas(listaZonasBasicas);

        return new ResponseEntity<>(listaZonasBasicas, HttpStatus.CREATED);


    }

    @PostMapping(path = "/ZonasBasicasSaludEditar",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Pide añadir un elemento del archivo Covid19-TIA_ZonasBásicasSalud.json", description = "Post request que añade un elemento de la lista de tipo ZonasBasicasSalud")
    public ResponseEntity<List<ZonasBasicasSalud>> postBasicasEditar(@RequestBody int posicion,String codigo_geometria, String zona_basica_salud, Number tasa_incidencia_acumulada_ultimos_14dias, Number tasa_incidencia_acumulada_total, Number casos_confirmados_totales, String fecha_informe) {

        listaZonasBasicas.get(posicion).setCodigo_geometria(codigo_geometria);
        listaZonasBasicas.get(posicion).setZona_basica_salud(zona_basica_salud);
        listaZonasBasicas.get(posicion).setTasa_incidencia_acumulada_ultimos_14dias(tasa_incidencia_acumulada_ultimos_14dias);
        listaZonasBasicas.get(posicion).setTasa_incidencia_acumulada_total(tasa_incidencia_acumulada_total);
        listaZonasBasicas.get(posicion).setCasos_confirmados_totales(casos_confirmados_totales);
        listaZonasBasicas.get(posicion).setFecha_informe(fecha_informe);

        gDatos.gestionarCodigoGeometriaBasicas(listaZonasBasicas, listaZonasBasicas.get(posicion));

        lectorjson.actualizarJsonBasicas(listaZonasBasicas);


        return new ResponseEntity<>(listaZonasBasicas, HttpStatus.CREATED);
    }

    @DeleteMapping("/ZonasBasicasSalud/{elemento}")
    @Operation(summary = "Pide eliminar un elemento del archivo Covid19-TIA_ZonasBásicasSalud.json", description = "Delete request que elimina un elemento de la lista segun su ínidce de tipo ZonasBasicasSalud")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ZonasBasicasSalud>> zbs_Delete(@PathVariable("elemento") int indice) {
        List<ZonasBasicasSalud> lista = lectorjson.leeFicheroJsonSaludBasicas();

        ZonasBasicasSalud listaDel = lista.get(indice);

        lista.remove(indice);
        System.out.println("Borrado el elemento: " + indice + "\n" +
                "Código de Geometría: \"" + listaDel.getCodigo_geometria() + "\"\n" +
                "Zona Básica de Salud: \"" + listaDel.getZona_basica_salud() + "\"\n" +
                "Tasa Incidencia Acc (Ultimos 14 días): \"" + listaDel.getTasa_incidencia_acumulada_ultimos_14dias() + "\"\n" +
                "Tasa Incidencia Acc (Total): \"" + listaDel.getTasa_incidencia_acumulada_total() + "\"\n" +
                "Casos Confirmados (Totales): \"" + listaDel.getCasos_confirmados_totales() + "\"\n" +
                "Casos Confirmados (Últimos 14 días): \"" + listaDel.getCasos_confirmados_ultimos_14dias() + "\"\n" +
                "Fecha de Informe: \"" + listaDel.getFecha_informe() + "\"\n"
        );
        lectorjson.actualizarJsonBasicas(lista);

        return new ResponseEntity<>(lista, HttpStatus.CREATED);

    }
}
