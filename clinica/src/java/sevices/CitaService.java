package sevices;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Cita;
import storage.CitaDB;

/**
 *
 * @author erika
 */
public class CitaService {

    public static final String[] states = {"PENDIENTE", "‌ACEPTADA", "‌CANCELADA‌", "pendiente", "aceptada", "cancelada"};
    CitaDB citaDB = new CitaDB();

    public Cita createCita(Cita cita) {

        if (!Includes(states, cita.getEstado())) {
            throw new Error("El estado es invalido!");
        }

        if (cita.getPaciente_idpaciente().length() == 0) {
            throw new Error("El id del paciente no puede estar vacio");
        }

        return citaDB.createCita(cita);
    }

    public Cita updateCita(Cita cita, String uuid) {

        if (!Includes(states, cita.getEstado())) {
            throw new Error("El estado es invalido!");
        }

        if (cita.getPaciente_idpaciente().length() == 0) {
            throw new Error("El id del paciente no puede estar vacio");
        }
        return citaDB.updateCita(cita, uuid);
    }

    public static boolean Includes(String[] arr, String targetValue) {
        return Arrays.asList(arr).contains(targetValue);
    }
}
