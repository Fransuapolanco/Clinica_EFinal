package rest;

import com.google.gson.Gson;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import models.Paciente;
import models.Resp;
import storage.PacienteDB;

/**
 * REST Web Service
 *
 * @author erika
 */
@Path("Pacientes")
public class Pacientes {

    PacienteDB pacDb = new PacienteDB();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Pacientes
     */
    public Pacientes() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPacientes() {
        return ResponseWriter.Respond(201, new Resp(true, "Registros encontrados", pacDb.AllPacientes()));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPaciente(Paciente paciente) {
        return ResponseWriter.Respond(200, new Resp(true, "Registros ingresado correctamente", pacDb.createPaciente(paciente)));
    }

    @GET
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOne(@PathParam("id") String id) {
        Paciente paciente = pacDb.getPaciente(id);

        if (paciente == null) {
            Resp resp = new Resp(false, "No se encontro registro", paciente);
            return ResponseWriter.Respond(404, resp);
        }

        Resp resp = new Resp(true, "Encontrado!", paciente);
        return ResponseWriter.Respond(200, resp);
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePaciente(Paciente paciente, @PathParam("id") String id) {
        return ResponseWriter.Respond(200, new Resp(true, pacDb.updatePaciente(paciente, id), null));
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletePaciente(@PathParam("id") String id) {
        Gson gson = new Gson();
        return ResponseWriter.Respond(200, new Resp(true, pacDb.deletePaciente(id), null));
    }
}
