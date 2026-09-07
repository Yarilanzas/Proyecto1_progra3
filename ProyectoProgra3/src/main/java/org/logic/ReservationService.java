package org.logic;

import org.data.Data;
import org.data.XMLRepository;
import org.domain.Reservation;

import java.util.List;

public class ReservationService {

    public void save(Reservation newReservation) throws Exception {
        Data data = XMLRepository.instance().load();

        List<Reservation> currentList = data.getReservations();

        int nextNumber = currentList.size() + 1;

        String generatedId = String.format("RES-%03d", nextNumber);
        newReservation.setId(generatedId);

        newReservation.setStatus("ACTIVA ");

        data.getReservations().add((newReservation));
    }

    public static String generateNextId(int lastNumber) {
        return String.format("RES-%03d", lastNumber + 1);
    }
}
