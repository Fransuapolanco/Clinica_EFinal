package storage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import models.Paciente;

/**
 *
 * @author erika
 */
public class PacienteDB {

    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rows = null;
    Statement st = null;

    public ArrayList<Paciente> AllPacientes() {
        ArrayList<Paciente> pacientes = new ArrayList();

        try {
            conn = DB.DataBase.Conect();
            st = conn.createStatement();
            rows = st.executeQuery("SELECT * FROM paciente;");

            while (rows.next()) {
                Paciente p = new Paciente();
                p.setIdpaciente(rows.getString("idpaciente"));
                p.setPrimerNombre(rows.getString("primerNombre"));
                p.setSegundoNombre(rows.getString("segundoNombre"));
                p.setPrimerApellido(rows.getString("primerApellido"));
                p.setSegundoApellido(rows.getString("segundoApellido"));
                p.setEdad(rows.getString("edad"));
                pacientes.add(p);
            }
        } catch (SQLException e) {
            System.out.println("services.PacienteDB.AllPacientes(): error:" + e);
            return pacientes;
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

        return pacientes;
    }

    public String createPaciente(Paciente paciente) {
        try {
            conn = DB.DataBase.Conect();
            pst = conn.prepareStatement("INSERT INTO paciente "
                    + "(primerNombre, "
                    + "segundoNombre, "
                    + "primerApellido, "
                    + "segundoApellido, "
                    + "edad) VALUES (?,?,?,?,?);");
            pst.setString(1, paciente.getPrimerNombre());
            pst.setString(2, paciente.getSegundoNombre());
            pst.setString(3, paciente.getPrimerApellido());
            pst.setString(4, paciente.getSegundoApellido());
            pst.setString(5, paciente.getEdad());
            pst.execute();

            return "Creado correctamente!";
        } catch (SQLException e) {
            System.err.println("services.PacienteDB.createPaciente();" + e);
            return "No se pudo guardar";
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

    public Paciente getPaciente(String ID) {
        Paciente paciente = null;
        try {
            conn = DB.DataBase.Conect();
            pst = conn.prepareStatement("SELECT * FROM paciente WHERE idpaciente = ?");
            pst.setString(1, ID);
            rows = pst.executeQuery();

            while (rows.next()) {
                paciente = new Paciente();
                paciente.setIdpaciente(rows.getString("idpaciente"));
                paciente.setPrimerNombre(rows.getString("primerNombre"));
                paciente.setSegundoNombre(rows.getString("segundoNombre"));
                paciente.setPrimerApellido(rows.getString("primerApellido"));
                paciente.setSegundoApellido(rows.getString("segundoApellido"));
                paciente.setEdad(rows.getString("edad"));
            }

            return paciente;
        } catch (SQLException e) {
            System.out.println("services.PacienteDB.getPaciente(): error:" + e);
            return null;
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    pst.close();
                }
            } catch (SQLException e) {
                System.out.println("services.CitaDB.getPaciente(); error: " + e);
            }
        }

    }

    public String updatePaciente(Paciente paciente, String ID) {
        try {
            conn = DB.DataBase.Conect();
            pst = conn.prepareStatement("UPDATE paciente "
                    + "SET primerNombre = ?, "
                    + "segundoNombre = ?, "
                    + "primerApellido = ?, "
                    + "segundoApellido = ?, "
                    + "edad = ? "
                    + "WHERE idpaciente = ?;");
            pst.setString(1, paciente.getPrimerNombre());
            pst.setString(2, paciente.getSegundoNombre());
            pst.setString(3, paciente.getPrimerApellido());
            pst.setString(4, paciente.getSegundoApellido());
            pst.setString(5, paciente.getEdad());
            pst.setString(6, ID);
            pst.execute();

            return "Actualizado correctamente!";
        } catch (SQLException e) {
            System.err.println("services.PacienteDB.updatePaciente();" + e);
            return "No se pudo actualizar";
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    pst.close();
                }
            } catch (SQLException e) {
                System.out.println("services.CitaDB.updatePaciente(); error: " + e);
            }
        }

    }

    public String deletePaciente(String id) {
        try {
            conn = DB.DataBase.Conect();
            pst = conn.prepareStatement("DELETE FROM paciente WHERE idpaciente = ?;");
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
                System.out.println("services.CitaDB.deletePaciente(); error: " + e);
            }
        }

    }

}
