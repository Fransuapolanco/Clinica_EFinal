package storage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import models.Cita;

/**
 *
 * @author erika
 */
public class CitaDB {

    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rows = null;
    Statement st = null;

    public ArrayList<Cita> AllCitas() {
        ArrayList<Cita> citas = new ArrayList();

        try {
            conn = DB.DataBase.Conect();
            st = conn.createStatement();
            rows = st.executeQuery("SELECT "
                    + "c.idcita, c.fecha, "
                    + "c.hora, c.estado, "
                    + "c.observaciones, "
                    + "c.paciente_idpaciente, "
                    + "p.primerNombre, "
                    + "p.primerApellido FROM cita c "
                    + "INNER JOIN "
                    + "paciente p ON "
                    + "c.paciente_idpaciente = p.idpaciente");

            while (rows.next()) {
                Cita c = new Cita();
                c.setIdcita(rows.getString("idcita"));
                c.setFecha(rows.getString("fecha"));
                c.setHora(rows.getString("hora"));
                c.setEstado(rows.getString("estado"));
                c.setObservaciones(rows.getString("observaciones"));
                c.setPaciente_idpaciente(rows.getString("paciente_idpaciente"));
                c.setPrimerNombre(rows.getString("primerNombre"));
                c.setSegundoNombre(rows.getString("primerApellido"));
                citas.add(c);
            }

            return citas;
        } catch (SQLException e) {
            System.out.println("services.CitaDB.AllPacientes(); error: " + e);
            return citas;
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    st.close();
                }
            } catch (SQLException e) {
                System.out.println("services.CitaDB.AllPacientes(); error: " + e);
            }
        }

    }

    public Cita createCita(Cita cita) {
        try {
            conn = DB.DataBase.Conect();
            pst = conn.prepareStatement("INSERT INTO cita (fecha, hora, estado, observaciones, paciente_idpaciente) VALUES (?,?,?,?,?);");
            pst.setString(1, cita.getFecha());
            pst.setString(2, cita.getHora());
            pst.setString(3, cita.getEstado());
            pst.setString(4, cita.getObservaciones());
            pst.setString(5, cita.getPaciente_idpaciente());
            pst.execute();

            return cita;
        } catch (SQLException e) {
            System.err.println("services.PacienteDB.createCita();" + e);
            return null;
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    pst.close();
                }
            } catch (SQLException e) {
                System.out.println("services.CitaDB.AllPacientes(); error: " + e);
            }
        }
    }

    public Cita getCita(String ID) {
        Cita cita = null;
        try {
            conn = DB.DataBase.Conect();
            pst = conn.prepareStatement("SELECT "
                    + "c.idcita, c.fecha, "
                    + "c.hora, c.estado, "
                    + "c.observaciones, "
                    + "c.paciente_idpaciente, "
                    + "p.primerNombre, "
                    + "p.primerApellido FROM cita c "
                    + "INNER JOIN "
                    + "paciente p ON "
                    + "c.paciente_idpaciente = p.idpaciente "
                    + "WHERE c.idcita = ?");
            pst.setString(1, ID);
            rows = pst.executeQuery();

            while (rows.next()) {
                cita = new Cita();
                cita.setIdcita(rows.getString("idcita"));
                cita.setFecha(rows.getString("fecha"));
                cita.setHora(rows.getString("hora"));
                cita.setEstado(rows.getString("estado"));
                cita.setObservaciones(rows.getString("observaciones"));
                cita.setPaciente_idpaciente(rows.getString("paciente_idpaciente"));
                cita.setPrimerNombre(rows.getString("primerNombre"));
                cita.setSegundoNombre(rows.getString("primerApellido"));
            }

            return cita;
        } catch (SQLException e) {
            System.out.println("services.CitaDB.getCita(): error:" + e);
            return null;
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    pst.close();
                }
            } catch (SQLException e) {
                System.out.println("services.CitaDB.getCita(); error: " + e);
            }
        }
    }

    public Cita updateCita(Cita cita, String id) {
        try {
            conn = DB.DataBase.Conect();
            pst = conn.prepareStatement("UPDATE cita "
                    + "SET fecha = ?, "
                    + "hora = ?, "
                    + "estado = ?, "
                    + "observaciones = ?, "
                    + "paciente_idpaciente = ? "
                    + "WHERE idCita = ?;");
            pst.setString(1, cita.getFecha());
            pst.setString(2, cita.getHora());
            pst.setString(3, cita.getEstado());
            pst.setString(4, cita.getObservaciones());
            pst.setString(5, cita.getPaciente_idpaciente());
            pst.setString(6, id);
            pst.executeUpdate();

            return cita;
        } catch (SQLException e) {
            System.err.println("services.PacienteDB.updateCita();" + e);
            return null;
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    pst.close();
                }
            } catch (SQLException e) {
                System.out.println("services.CitaDB.updateCita(); error: " + e);
            }
        }
    }

    public String deleteCita(String id) {
        try {
            conn = DB.DataBase.Conect();
            pst = conn.prepareStatement("DELETE FROM cita WHERE idcita = ?;");
            pst.setString(1, id);
            pst.executeUpdate();

            return "Registro borrado exitosamente";
        } catch (SQLException e) {
            System.out.println("services.PacienteDB.deletePaciente(); error: " + e);
            return "Fallo al borrar el registro o no existe";
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    pst.close();
                }
            } catch (SQLException e) {
                System.out.println("services.CitaDB.deleteCita(); error: " + e);
            }
        }
    }

}
