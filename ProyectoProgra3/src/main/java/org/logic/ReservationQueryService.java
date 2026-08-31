package org.logic;

import org.data.*;
import org.domain.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ReservationQueryService {
    private static final String[] HORAS = {"06:00","07:00","08:00","09:00","10:00",
            "11:00","12:00","13:00","14:00", "15:00","16:00","17:00","18:00", "19:00",
            "20:00","21:00","22:00","23:00","24:00"};

    public CalendarData getCalendar(LocalDate fecha,Category categoria) throws Exception{
        Data data = XMLRepository.instance().load();

        List<Resource> recursos = new ArrayList<>();
        for (Resource r : data.getResources()){
            if (r.getCategory().getId().equals(categoria.getId())){
                recursos.add(r);
            }
        }

        List<String> horas = Arrays.asList(HORAS);
        Map<String,Map<String,String>> celdas = new HashMap<>();

        for(Reservation res : data.getReservations()){
            if (!"ACTIVA".equals(res.getStatus())) continue;
            if (!fecha.equals(res.getDate())) continue;

            for (DetailReservation detalle : res.getDetails()){
                Resource asignado = detalle.getAssignedResource();
                boolean categoriaseleccionada = recursos.stream().anyMatch(r -> r.getId().equals(asignado.getId()));
                if (!categoriaseleccionada) continue;

                for (String hora : horas){
                    if (horaEnRango(hora,res.getStartTime(), res.getEndTime())){
                        celdas.computeIfAbsent(hora, h -> new HashMap<>())
                                .put(asignado.getId(), res.getActivity() + " - " +  res.getEmployee().getName());
                    }
                }
            }
        }
        return new CalendarData(horas,recursos,celdas);
    }

    private boolean horaEnRango(String hora,String inicio, String fin){
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime h = LocalTime.parse(hora,fmt);
        LocalTime in = LocalTime.parse(inicio,fmt);
        LocalTime finn = LocalTime.parse(fin,fmt);
        return !h.isBefore(in) && h.isBefore(finn);
    }
}
