/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.com.ferbo.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import mx.com.ferbo.dao.RegistroDAO;
import mx.com.ferbo.dto.DetRegistroDTO;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

/**
 *
 * @author Gabo
 */
@Named(value = "asistenciaBean")
@ViewScoped
public class asistenciaBean implements Serializable {

    private ScheduleModel calendario;
    private ScheduleEvent evento;
    private RegistroDAO registroDAO;
    private final SimpleDateFormat sdf = new SimpleDateFormat("hh:mm aa");

    private List<DetRegistroDTO> lstRegistros;

    public asistenciaBean() {
        calendario = new DefaultScheduleModel();
        registroDAO = new RegistroDAO();
        sdf.setTimeZone(TimeZone.getTimeZone(ZoneId.of("America/Mexico_City").normalized()));
    }

    @PostConstruct
    public void init() {
        evento = new DefaultScheduleEvent();
        lstRegistros = registroDAO.consultaRegistrosPorIdEmp(1);
        generaEventos(lstRegistros);
    }

    private void generaEventos(List<DetRegistroDTO> registros) {

        for (DetRegistroDTO registro : registros) {
            DefaultScheduleEvent evento = DefaultScheduleEvent.builder()
                    .title(sdf.format(registro.getFechaEntrada()) + (registro.getFechaSalida() != null
                            ? " - " + sdf.format(registro.getFechaSalida())
                            : "")
                    )
                    .startDate(convertirDateToLocalDateTime(registro.getFechaEntrada()))
                    .endDate(registro.getFechaSalida() != null
                             ? convertirDateToLocalDateTime(registro.getFechaSalida())
                             : convertirDateToLocalDateTime(registro.getFechaEntrada()))
                                        .description(registro.getFechaSalida() != null ? sdf.format(registro.getFechaSalida()) : null)
                    .build();
            calendario.addEvent(evento);
        }

    }

    public LocalDateTime convertirDateToLocalDateTime(Date fecha) {
//        System.out.println(fecha.toInstant()
//                .atZone(ZoneId.of("UTC"))
//                .toLocalDateTime());
//        System.out.println(fecha.toInstant()
//                .atZone(ZoneId.of("Mexico/General"))
//                .toLocalDateTime());
//        
//        System.out.println(fecha.toInstant()
//                .atZone(ZoneId.of("America/Mexico_City"))
//                .toLocalDateTime());
//        
//        System.out.println(fecha.toInstant()
//                .atZone(ZoneId.of("GMT-6"))
//                .toLocalDateTime());
//        System.out.println(fecha.toInstant()
//                .atZone(ZoneId.of("UTC-6"))
//                .toLocalDateTime());

        return fecha.toInstant()
                .atZone(ZoneId.of("America/Mexico_City"))
                .toLocalDateTime();
    }

    public void eventoSeleccionado(SelectEvent<ScheduleEvent> selectEvent) {
        evento = selectEvent.getObject();
    }

    public void diaSeleccionado(SelectEvent<LocalDateTime> selectEvent) {
        evento = DefaultScheduleEvent.builder().startDate(selectEvent.getObject()).endDate(selectEvent.getObject()).build();
    }

    public ScheduleModel getCalendario() {
        return calendario;
    }

    public void setCalendario(ScheduleModel calendario) {
        this.calendario = calendario;
    }

    public ScheduleEvent getEvento() {
        return evento;
    }

    public void setEvento(ScheduleEvent evento) {
        this.evento = evento;
    }

}
