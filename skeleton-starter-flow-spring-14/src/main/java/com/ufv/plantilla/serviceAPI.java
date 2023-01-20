package com.ufv.plantilla;

import com.google.gson.Gson;
import com.googlecode.gentyref.TypeToken;
import com.ufv.plantilla.clases.ZonasBasicasSaludMayores;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.util.List;

@Service
public class serviceAPI {
    public List<ZonasBasicasSaludMayores> leerSaludMayores() throws URISyntaxException, IOException, InterruptedException {
        API api = new API();
        String apiRes = api.getZonasMayores();
        Gson gson = new Gson();
        Type listaTipo = new TypeToken<List<ZonasBasicasSaludMayores>>(){}.getType();
        List<ZonasBasicasSaludMayores> lista = gson.fromJson(apiRes,listaTipo);
        return lista;
    }
    public void cambiarSaludMayores(int pos, ZonasBasicasSaludMayores zBSM) throws URISyntaxException, IOException, InterruptedException {
        API api = new API();
        api.postMayores(pos, zBSM.getCodigo_geometria(), zBSM.getZona_basica_salud(),  zBSM.getTasa_incidencia_acumulada_P60mas_ultimos_14dias(), zBSM.getCasos_confirmados_P60mas_ultimos_14dias(), zBSM.getFecha_informe());
    }

    public void deleteSaludMayores(int pos) throws IOException, InterruptedException {
        API api = new API();
        api.deleteMayores(pos);
    }
}
