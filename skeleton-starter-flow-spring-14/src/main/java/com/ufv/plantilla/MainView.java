package com.ufv.plantilla;

import com.ufv.plantilla.clases.ZonasBasicasSaludMayores;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * A sample Vaadin view class.
 * <p>
 * To implement a Vaadin view just extend any Vaadin component and
 * use @Route annotation to announce it in a URL as a Spring managed
 * bean.
 * Use the @PWA annotation make the application installable on phones,
 * tablets and some desktop browsers.
 * <p>
 * A new instance of this class is created for every new user and every
 * browser tab/window.
 */
@Route
@PWA(name = "Vaadin Application",
        shortName = "Vaadin App",
        description = "This is an example Vaadin application.",
        enableInstallPrompt = false)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class MainView extends VerticalLayout {

    public final Tab general = new Tab("General");
    private final Tab mayores =  new Tab("Mayores de 59 años");
    private final VerticalLayout content;
    private int gridRow;
    /**
     * Construct a new Vaadin view.
     * <p>
     * Build the initial UI state for the user accessing the application.
     *
     * @param service The message service. Automatically injected Spring managed bean.
     */
    public MainView(@Autowired serviceAPI service) throws URISyntaxException, IOException, InterruptedException, ParseException {

        content = new VerticalLayout();
        try {
            List<ZonasBasicasSaludMayores> lista =  service.leerSaludMayores();
            H1 headline = new H1("Tasa de infección acumulada por zonas básicas de salud\n");
            headline.getStyle()
                    .set("margin", "var(--lumo-space-m) 0 0 0")
                    .set("font-size", "1.5em").set("font-weight", "bold");

            Tabs tabs = new Tabs(general, mayores);
            tabs.addSelectedChangeListener(event -> setContent(event.getSelectedTab(),lista, service));
            content.setSpacing(false);
            setContent(tabs.getSelectedTab(),lista, service);
            add(tabs, content);
        }catch (Exception e){
            System.out.println(e);
        }
    }
    private void setContent(Tab tab, List<ZonasBasicasSaludMayores> dataGeneral, serviceAPI service) {
        content.removeAll();
        if (tab.equals(general)) {
            Grid<ZonasBasicasSaludMayores> gridG = new Grid<>();
            gridG.setItems(dataGeneral);
            gridG.addColumn(ZonasBasicasSaludMayores::getCodigo_geometria).setHeader("codigo_geometría");
            gridG.addColumn(ZonasBasicasSaludMayores::getZona_basica_salud).setHeader("zona_basica_salud");
            gridG.addColumn(ZonasBasicasSaludMayores::getTasa_incidencia_acumulada_P60mas_ultimos_14dias).setHeader("tasa_incidencia_acumulada_P60mas_ultimos_14dias");
            gridG.addColumn(ZonasBasicasSaludMayores::getCasos_confirmados_P60mas_ultimos_14dias).setHeader("casos_confirmados_P60mas_ultimos_14dias");
            gridG.addColumn(ZonasBasicasSaludMayores::getFecha_informe).setHeader("fecha_informe");

            Dialog dialogN = new Dialog();
            Dialog dialogE = new Dialog();
            dialogN.getElement().setAttribute("aria-label", "Nuevo_elemento");
            dialogE.getElement().setAttribute("aria-label", "Modificar_elemento");

            VerticalLayout dialogLayoutN = createDialogLayoutNuevo(dialogN);
            dialogN.add(dialogLayoutN);

            gridG.addItemClickListener(e -> {
                gridRow = checkPositionG(e.getItem(), dataGeneral);
                VerticalLayout dialogLayoutE = createDialogLayoutElement(dialogE, dataGeneral);
                dialogE.removeAll();
                dialogE.add(dialogLayoutE);
                dialogE.open();
            });

            Button buttonN = new Button("Nuevo", e -> dialogN.open());
            Button buttonD = new Button("Delete", e -> {
                try {
                    service.deleteSaludMayores(1);
                } catch (IOException ex) {
                    System.out.println("fsfdsda");
                    ex.printStackTrace();
                } catch (InterruptedException ex) {
                    System.out.println("fsfdsda");
                    ex.printStackTrace();
                }
            });
            add(dialogN, buttonN, buttonD);

            content.add(new H2("Tasa de infección acumulada general por zonas básicas de salud.\n"), gridG, buttonN);
        } else {
            Grid<ZonasBasicasSaludMayores> gridM = new Grid<>();
            gridM.setItems(dataGeneral);
            gridM.addColumn(ZonasBasicasSaludMayores::getCodigo_geometria).setHeader("codigo_geometria");
            gridM.addColumn(ZonasBasicasSaludMayores::getZona_basica_salud).setHeader("zona_basica_salud");
            gridM.addColumn(ZonasBasicasSaludMayores::getTasa_incidencia_acumulada_P60mas_ultimos_14dias).setHeader("tasa_incidencia_acumulada_P60mas_ultimos_14dias");
            gridM.addColumn(ZonasBasicasSaludMayores::getCasos_confirmados_P60mas_ultimos_14dias).setHeader("casos_confirmados_P60mas_ultimos_14dias");
            gridM.addColumn(ZonasBasicasSaludMayores::getFecha_informe).setHeader("fecha_informe");

            Dialog dialogM = new Dialog();
            dialogM.getElement().setAttribute("aria-label", "Modificar elemento en mayores");

            gridM.addItemClickListener(e -> {
                gridRow = checkPositionM(e.getItem(), dataGeneral);
                VerticalLayout dialogLayoutE = createDialogLayoutMayor(dialogM, dataGeneral, service);
                dialogM.removeAll();
                dialogM.add(dialogLayoutE);
                dialogM.open();
            });

            content.add(new H2("Tasa de infección acumulada para mayores de 59 años por zonas básicas de salud.\n"), gridM);
        }
    }
    private int checkPositionG(ZonasBasicasSaludMayores datos, List<ZonasBasicasSaludMayores> dataGeneral){
        int row = 0;
        for (ZonasBasicasSaludMayores dg:dataGeneral) {
            if(datos == dg){
                return row;
            }
            row++;
        }
        return row;
    }
    private int checkPositionM(ZonasBasicasSaludMayores datos, List<ZonasBasicasSaludMayores> dataMayores){
        int row = 0;
        for (ZonasBasicasSaludMayores dm:dataMayores) {
            if(datos == dm){
                return row;
            }
            row++;
        }
        return row;

    }

    private static VerticalLayout createDialogLayoutNuevo(Dialog dialog) {
        H2 headline = new H2("Nuevo elemento general");
        headline.getStyle().set("margin", "var(--lumo-space-m) 0 0 0")
                .set("font-size", "1.5em").set("font-weight", "bold");

        TextField codigo_geometria = new TextField("codigo_geometria");
        TextField zona_basica_salud = new TextField("zona_basica_salud");
        TextField tasa_incidencia_acumulada_ultimos_14dias = new TextField("tasa_incidencia_acumulada_ultimos_14dias");
        TextField tasa_incidencia_acumulada_total = new TextField("tasa_incidencia_acumulada_total");
        TextField casos_confirmados_totales = new TextField("casos_confirmados_totales");
        TextField casos_confirmados_ultimos_14dias = new TextField("casos_confirmados_ultimos_14dias");
        TextField fecha_informe = new TextField("fecha_informe");
        VerticalLayout fieldLayout = new VerticalLayout(codigo_geometria,zona_basica_salud,tasa_incidencia_acumulada_ultimos_14dias,
                tasa_incidencia_acumulada_total,casos_confirmados_totales,casos_confirmados_ultimos_14dias,fecha_informe);
        fieldLayout.setSpacing(false);
        fieldLayout.setPadding(false);
        fieldLayout.setAlignItems(FlexComponent.Alignment.STRETCH);

        Button cancelButton = new Button("Cancelar", e -> dialog.close());
        Button saveButton = new Button("Aceptar", e -> {
            System.out.println(codigo_geometria);
            System.out.println(zona_basica_salud);
            System.out.println(tasa_incidencia_acumulada_ultimos_14dias);
            System.out.println(tasa_incidencia_acumulada_total);
            System.out.println(casos_confirmados_totales);
            System.out.println(casos_confirmados_ultimos_14dias);
            System.out.println(fecha_informe);
            dialog.close();
        });
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        HorizontalLayout buttonLayout = new HorizontalLayout(cancelButton,
                saveButton);
        buttonLayout
                .setJustifyContentMode(FlexComponent.JustifyContentMode.END);

        VerticalLayout dialogLayout = new VerticalLayout(headline, fieldLayout,
                buttonLayout);
        dialogLayout.setPadding(false);
        dialogLayout.setAlignItems(FlexComponent.Alignment.STRETCH);
        dialogLayout.getStyle().set("width", "500px").set("max-width", "100%");

        return dialogLayout;
    }

    private VerticalLayout createDialogLayoutElement(Dialog dialog, List<ZonasBasicasSaludMayores> dataGeneral) {
        H2 headline = new H2("Modificar el elemento " + (gridRow+1) + "º general");
        headline.getStyle().set("margin", "var(--lumo-space-m) 0 0 0")
                .set("font-size", "1.5em").set("font-weight", "bold");

        TextField codigo_geometria = new TextField("codigo_geometria");
        codigo_geometria.setPlaceholder(dataGeneral.get(gridRow).getCodigo_geometria());
        TextField zona_basica_salud = new TextField("zona_basica_salud");
        zona_basica_salud.setPlaceholder(dataGeneral.get(gridRow).getZona_basica_salud());
        /*
        TextField tasa_incidencia_acumulada_ultimos_14dias = new TextField("tasa_incidencia_acumulada_ultimos_14dias");
        tasa_incidencia_acumulada_ultimos_14dias.setPlaceholder(dataGeneral.get(gridRow).getTasa_incidencia_acumulada_ultimos_14dias().toString());
        TextField tasa_incidencia_acumulada_total = new TextField("tasa_incidencia_acumulada_total");
        tasa_incidencia_acumulada_total.setPlaceholder(dataGeneral.get(gridRow).getTasa_incidencia_acumulada_total().toString());
        TextField casos_confirmados_totales = new TextField("casos_confirmados_totales");
        casos_confirmados_totales.setPlaceholder(dataGeneral.get(gridRow).getCasos_confirmados_totales().toString());
        TextField casos_confirmados_ultimos_14dias = new TextField("casos_confirmados_ultimos_14dias");
        casos_confirmados_ultimos_14dias.setPlaceholder(dataGeneral.get(gridRow).getCasos_confirmados_ultimos_14dias().toString());
        */
        TextField fecha_informe = new TextField("fecha_informe");
        fecha_informe.setPlaceholder(dataGeneral.get(gridRow).getFecha_informe().toString());
        VerticalLayout fieldLayout = new VerticalLayout(codigo_geometria,zona_basica_salud/*,tasa_incidencia_acumulada_ultimos_14dias,
                tasa_incidencia_acumulada_total,casos_confirmados_totales,casos_confirmados_ultimos_14dias*/,fecha_informe);
        fieldLayout.setSpacing(false);
        fieldLayout.setPadding(false);
        fieldLayout.setAlignItems(FlexComponent.Alignment.STRETCH);

        Button cancelButton = new Button("Cancelar", e -> dialog.close());
        Button saveButton = new Button("Aceptar", e -> {
            System.out.println(codigo_geometria);
            System.out.println(zona_basica_salud);/*
            System.out.println(tasa_incidencia_acumulada_ultimos_14dias);
            System.out.println(tasa_incidencia_acumulada_total);
            System.out.println(casos_confirmados_totales);
            System.out.println(casos_confirmados_ultimos_14dias);*/
            System.out.println(fecha_informe);
            dialog.close();
        });
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        HorizontalLayout buttonLayout = new HorizontalLayout(cancelButton,
                saveButton);
        buttonLayout
                .setJustifyContentMode(FlexComponent.JustifyContentMode.END);

        VerticalLayout dialogLayout = new VerticalLayout(headline, fieldLayout,
                buttonLayout);
        dialogLayout.setPadding(false);
        dialogLayout.setAlignItems(FlexComponent.Alignment.STRETCH);
        dialogLayout.getStyle().set("width", "500px").set("max-width", "100%");

        return dialogLayout;
    }

    private VerticalLayout createDialogLayoutMayor(Dialog dialog, List<ZonasBasicasSaludMayores> dataMayores, serviceAPI service) {
        H2 headline = new H2("Modificar el elemento " + (gridRow+1) + "º en mayores");
        headline.getStyle().set("margin", "var(--lumo-space-m) 0 0 0")
                .set("font-size", "1.5em").set("font-weight", "bold");

        TextField codigo_geometria = new TextField("codigo_geometria");
        codigo_geometria.setPlaceholder(dataMayores.get(gridRow).getCodigo_geometria());
        TextField zona_basica_salud = new TextField("zona_basica_salud");
        zona_basica_salud.setPlaceholder(dataMayores.get(gridRow).getZona_basica_salud());
        TextField tasa_incidencia_acumulada_P60mas_ultimos_14dias = new TextField("tasa_incidencia_acumulada_P60mas_ultimos_14dias");
        tasa_incidencia_acumulada_P60mas_ultimos_14dias.setPlaceholder(dataMayores.get(gridRow).getTasa_incidencia_acumulada_P60mas_ultimos_14dias().toString());
        TextField casos_confirmados_P60mas_ultimos_14dias = new TextField("casos_confirmados_P60mas_ultimos_14dias");
        casos_confirmados_P60mas_ultimos_14dias.setPlaceholder(dataMayores.get(gridRow).getCasos_confirmados_P60mas_ultimos_14dias().toString());
        TextField fecha_informe = new TextField("fecha_informe");
        fecha_informe.setPlaceholder(dataMayores.get(gridRow).getFecha_informe().toString());
        VerticalLayout fieldLayout = new VerticalLayout(codigo_geometria,zona_basica_salud,tasa_incidencia_acumulada_P60mas_ultimos_14dias,
                casos_confirmados_P60mas_ultimos_14dias,fecha_informe);
        fieldLayout.setSpacing(false);
        fieldLayout.setPadding(false);
        fieldLayout.setAlignItems(FlexComponent.Alignment.STRETCH);

        Button cancelButton = new Button("Cancel", e -> dialog.close());
        Button saveButton = new Button("Aceptar", e -> {
            try {
                String code;
                String zone;
                Number acum;
                Number conf;
                String date;

                if(!(codigo_geometria.getValue().isEmpty())){
                    code = codigo_geometria.getValue();
                } else {
                    code = dataMayores.get(gridRow).getCodigo_geometria();
                }

                if(!(zona_basica_salud.getValue().isEmpty())){
                    zone = zona_basica_salud.getValue();
                } else {
                    zone = dataMayores.get(gridRow).getZona_basica_salud();
                }

                if(!(tasa_incidencia_acumulada_P60mas_ultimos_14dias.getValue().isEmpty())){
                    acum = Double.parseDouble(tasa_incidencia_acumulada_P60mas_ultimos_14dias.getValue());
                } else {
                    acum = dataMayores.get(gridRow).getTasa_incidencia_acumulada_P60mas_ultimos_14dias();
                }
                if(!(casos_confirmados_P60mas_ultimos_14dias.getValue().isEmpty())){
                    conf = Double.parseDouble(casos_confirmados_P60mas_ultimos_14dias.getValue());
                } else {
                    conf = dataMayores.get(gridRow).getTasa_incidencia_acumulada_P60mas_ultimos_14dias();
                }

                if(!(fecha_informe.getValue().isEmpty())){
                    date = fecha_informe.getValue();
                } else {
                    date = dataMayores.get(gridRow).getFecha_informe();
                }

                ZonasBasicasSaludMayores elementoCambio = new ZonasBasicasSaludMayores(code, zone, acum, conf, date);
                service.cambiarSaludMayores(gridRow, elementoCambio);
            } catch (URISyntaxException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            dialog.close();
        });
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        HorizontalLayout buttonLayout = new HorizontalLayout(cancelButton,
                saveButton);
        buttonLayout
                .setJustifyContentMode(FlexComponent.JustifyContentMode.END);

        VerticalLayout dialogLayout = new VerticalLayout(headline, fieldLayout,
                buttonLayout);
        dialogLayout.setPadding(false);
        dialogLayout.setAlignItems(FlexComponent.Alignment.STRETCH);
        dialogLayout.getStyle().set("width", "500px").set("max-width", "100%");

        return dialogLayout;
    }
}
