/*Este service es especifico ante cualquier consulta que tenga que ver con una reservacion
ya hecha, o sea  basicamente agrupar todas las consultas sobre una reserva en un service, en vez
de hacerlas todas separadas, o sea como en calendarizacion, actividades, estadisiticas

son solo distintas formas de leer y resumir los mismos datos de Reservation/DetailReservation.
Como los 4 métodos (getCalendar, getWeeklySchedule, getResourceStatistics, getActivityStatistics)
leen exactamente la misma fuente de datos, tenerlos juntos evita duplicar el data.getReservations()
en 4 archivos distintos sin necesidad real de separarlos.
*/

package org.logic;

import org.data.*;
import org.domain.*;

import org.presentation.statistics.CategoryStatistics;
import org.presentation.statistics.ActivityStatistics;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.time.DayOfWeek;


public class ReservationQueryService {
    private static final String[] HORAS = {"06:00","07:00","08:00","09:00","10:00",
            "11:00","12:00","13:00","14:00", "15:00","16:00","17:00","18:00", "19:00",
            "20:00","21:00","22:00","23:00"};

    private final ReservationDAO reservationDAO;
    private final ResourceDAO resourceDAO;
    private final CategoryDAO categoryDAO;

    public ReservationQueryService(){
        this.reservationDAO = new ReservationDAO();
        this.resourceDAO = new ResourceDAO();
        this.categoryDAO = new CategoryDAO();
    }

    public CalendarData getCalendar(LocalDate fecha,Category categoria) throws Exception{
       List<Resource> recursos = resourceDAO.findbyCategory(categoria.getId());
       List<Reservation> reservas = reservationDAO.findAll();

        List<String> horas = Arrays.asList(HORAS);
        Map<String,Map<String,String>> celdas = new HashMap<>();

        for (Reservation res : reservas){
            if (!"ACTIVA".equalsIgnoreCase(res.getStatus())) continue;
            if (!fecha.equals(res.getDate())) continue;

            String nomEmp = (res.getEmployee() != null) ? res.getEmployee().getName() : "No asignado";
            String texto = res.getActivity() + " - " + nomEmp;

            List<Resource> asignados = (res.getResources() != null) ? res.getResources() : new ArrayList<>();

            for (Resource asignado : asignados) {
                boolean categoriaseleccionada = recursos.stream().anyMatch(r -> r.getId().equals(asignado.getId()));

                if (!categoriaseleccionada) continue;

                for (String hora : horas){
                    if (horaEnRango(hora,res.getStartTime(), res.getEndTime())){
                        celdas.computeIfAbsent(hora, h -> new HashMap<>())
                                .put(asignado.getId(),texto);
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

    public ActivityData getHorarioSemanal(LocalDate fecharef) throws Exception{
        LocalDate lunes = fecharef.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)); //agarra el dia lunes
        List<LocalDate> dias = new ArrayList<>();
        for(int i = 0; i<7;i++){
            dias.add(lunes.plusDays(i)); //va agregando los demas dias y crea una ;ista de
        }

        List<Reservation> reservas = reservationDAO.findAll();
        List<String> horas = Arrays.asList(HORAS);
        Map<String, Map<LocalDate,String>> celdas = new HashMap<>();

        for (Reservation reserva : reservas){
            if (!"ACTIVA".equals(reserva.getStatus())) continue;
            if (!dias.contains(reserva.getDate())) continue;

            String nomEmp = (reserva.getEmployee()!=null) ? reserva.getEmployee().getName() : "No asignado";

            for (String hora : horas){
                if (horaEnRango(hora, reserva.getStartTime(), reserva.getEndTime())){
                    celdas.computeIfAbsent(hora, h -> new HashMap<>())
                            .put(reserva.getDate(), reserva.getActivity() + " (" + nomEmp + ")");
                }
            }
        }
            return new ActivityData(dias, horas,celdas);
    }


    public List <CategoryStatistics> getResourceStatistics(LocalDate desde, LocalDate hasta) throws Exception{
        List<Category> categories = categoryDAO.findAll();
        List<Reservation> reservas = reservationDAO.findAll();


        List<CategoryStatistics> estadisticas = new ArrayList<>();
        for (Category c : categories){
            estadisticas.add(new CategoryStatistics(c,0));
        }

        for (Reservation reserva : reservas){
            if (!"ACTIVA".equalsIgnoreCase(reserva.getStatus())) continue;
            if (reserva.getDate().isBefore(desde) || reserva.getDate().isAfter(hasta)) continue;

            List<Resource> asignados = (reserva.getResources() != null) ? reserva.getResources() : new ArrayList<>();

            for (Resource recurso : asignados){
                if (recurso.getCategory() == null) continue;
                String categoriaId = recurso.getCategory().getId();

                for (CategoryStatistics ce: estadisticas){
                    if (ce.getCategory().getId().equals(categoriaId)){
                        ce.setCantidad(ce.getCantidad() + 1);
                    }
                }
            }
        }
        return estadisticas;
    }

    public List<ActivityStatistics> getActivityStatistics(LocalDate desde, LocalDate hasta) throws Exception{
        List<Reservation> reservas = reservationDAO.findAll();

        List<ActivityStatistics> estadisticas = new ArrayList<>();
        List<LocalDate> fechas = new ArrayList<>();

        LocalDate lunes = desde.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        for (LocalDate date = lunes; !date.isAfter(hasta); date = date.plusDays(7)){
            estadisticas.add(new ActivityStatistics(date.toString(),0));
            fechas.add(date);
        }

        reservas.stream()
            .filter(r -> "ACTIVA".equalsIgnoreCase(r.getStatus()))
            .filter(r -> !r.getDate().isBefore(desde) && !r.getDate().isAfter(hasta))
            .forEach(r -> {
                for (int i = fechas.size() - 1; i>=0; i--){
                    if (!r.getDate().isBefore(fechas.get(i))){
                        ActivityStatistics actividad = estadisticas.get(i);
                        actividad.setCantidad(actividad.getCantidad() + 1);
                        break;
                    }
                }
            });
        return estadisticas;
    }
}
