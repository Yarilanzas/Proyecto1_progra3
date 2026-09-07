package org.logic;

import org.data.Data;
import org.data.XMLRepository;
import org.domain.Category;
import org.domain.Reservation;
import org.domain.Resource;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReservationService {

    public void save(Reservation newReservation, List<Category> categoriasSeleccionadas) throws Exception {
        Data data = XMLRepository.instance().load();

        int nextNumber = data.getReservations().size() + 1;
        newReservation.setId(String.format("RES-%03d", nextNumber));
        newReservation.setStatus("ACTIVA");

        List<Resource> recursosAsignados = new ArrayList<>();

        for (Category cat : categoriasSeleccionadas) {
            List<Resource> recursosDeCat = data.getResources().stream()
                    .filter(r -> r.getCategory() != null && r.getCategory().getId().equals(cat.getId()))
                    .collect(Collectors.toList());

            Resource disponible = buscarRecursoDisponible(recursosDeCat, newReservation, data.getReservations());

            if (disponible == null) {
                throw new Exception("No hay recursos disponibles para la categoría '"
                        + cat.getDescription() + "' en el rango de horario seleccionado.");
            }

            recursosAsignados.add(disponible);
        }

        newReservation.setResources(recursosAsignados);
        data.getReservations().add(newReservation);
        XMLRepository.instance().store(data);
    }

    private Resource buscarRecursoDisponible(List<Resource> recursos, Reservation nuevaReserva, List<Reservation> reservasExistentes) {
        LocalTime nuevaInicio = LocalTime.parse(nuevaReserva.getStartTime());
        LocalTime nuevaFin = LocalTime.parse(nuevaReserva.getEndTime());

        for (Resource res : recursos) {
            boolean ocupado = false;

            for (Reservation r : reservasExistentes) {
                if ("ACTIVA".equalsIgnoreCase(r.getStatus()) && r.getDate().equals(nuevaReserva.getDate())) {

                    boolean tieneRecurso = r.getResources() != null &&
                            r.getResources().stream().anyMatch(rec -> rec.getId().equals(res.getId()));

                    if (tieneRecurso) {
                        LocalTime existInicio = LocalTime.parse(r.getStartTime());
                        LocalTime existFin = LocalTime.parse(r.getEndTime());


                        if (nuevaInicio.isBefore(existFin) && nuevaFin.isAfter(existInicio)) {
                            ocupado = true;
                            break;
                        }
                    }
                }
            }

            if (!ocupado) {
                return res;
            }
        }

        return null;
    }

    public static String generateNextId(int lastNumber) {
        return String.format("RES-%03d", lastNumber + 1);
    }

    public List<Reservation> findAllResources() throws Exception{
        Data data = XMLRepository.instance().load();
        return data.getReservations();
    }
    public void delete(String id) throws Exception {
        Data data = XMLRepository.instance().load();
        Reservation existe = data.getReservations().stream().filter
                        (c -> c.getId().equals(id))
                .findFirst().orElse(null);

        if (existe != null) {
            data.getReservations().remove(existe);
            XMLRepository.instance().store(data);
        }
    }

    public void cancel(String id) throws Exception {
        Data data = XMLRepository.instance().load();

        Reservation existe = data.getReservations().stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (existe == null) {
            throw new Exception("No se encontró la reservación solicitada.");
        }

        if ("CANCELADA".equalsIgnoreCase(existe.getStatus())) {
            throw new Exception("La reservación ya se encuentra cancelada.");
        }

        existe.setStatus("CANCELADA");

        XMLRepository.instance().store(data);
    }
}
