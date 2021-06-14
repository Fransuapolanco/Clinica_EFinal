package models;

/**
 *
 * @author erika
 */
public class Cita extends Paciente {

    private String idcita;
    private String fecha;
    private String hora;
    private String estado;
    private String observaciones;
    private String paciente_idpaciente;

    public Cita(String idcita, String fecha, String hora, String estado, String observaciones, String paciente_idpaciente) {
        this.idcita = idcita;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
        this.observaciones = observaciones;
        this.paciente_idpaciente = paciente_idpaciente;
    }

    public Cita() {

    }

    public String getIdcita() {
        return idcita;
    }

    public void setIdcita(String idcita) {
        this.idcita = idcita;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getPaciente_idpaciente() {
        return paciente_idpaciente;
    }

    public void setPaciente_idpaciente(String paciente_idpaciente) {
        this.paciente_idpaciente = paciente_idpaciente;
    }

}
