/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

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
import models.Cita;
import models.Resp;
import sevices.CitaService;
import storage.CitaDB;

/**
 * REST Web Service
 *
 * @author erika
 */
@Path("Citas")
public class Citas {

    CitaDB citaDb = new CitaDB();
    CitaService citaService = new CitaService();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Cita
     */
    public Citas() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCitas() {
        return ResponseWriter.Respond(200, new Resp(true, "Registros encontrados", citaDb.AllCitas()));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCita(Cita cita) {
        try {
            return ResponseWriter.Respond(200, new Resp(true, "Creaada correctamente", citaService.createCita(cita)));
        } catch (Exception e) {
            if (e.getMessage() == "El estado es invalido!") {
                return ResponseWriter.Respond(400, new Resp(true, "El estado es invalido!", null));
            }

            if (e.getMessage() == "El id del paciente no puede estar vacio") {
                return ResponseWriter.Respond(400, new Resp(true, "El id del paciente no puede estar vacio", null));
            }

            return ResponseWriter.InternalServerError(e);
        }
    }

    @GET
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOneCita(@PathParam("id") String id) {
        Cita cita = citaDb.getCita(id);

        if (cita == null) {
            Resp resp = new Resp(false, "No se encontro ningun registro", cita);
            return ResponseWriter.Respond(404, resp);

        }

        Resp resp = new Resp(true, "Cita encontrada!", cita);
        return ResponseWriter.Respond(200, resp);
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCita(Cita cita, @PathParam("id") String id) {

        try {
            return ResponseWriter.Respond(200, new Resp(true, "Actualizado correctamente", citaService.updateCita(cita, id)));
        } catch (Exception e) {
            if (e.getMessage() == "El estado es invalido!") {
                return ResponseWriter.Respond(400, new Resp(true, "El estado es invalido!", null));
            }

            if (e.getMessage() == "El id del paciente no puede estar vacio") {
                return ResponseWriter.Respond(400, new Resp(true, "El id del paciente no puede estar vacio", null));
            }

            return ResponseWriter.InternalServerError(e);
        }
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePaciente(@PathParam("id") String id) {
        return ResponseWriter.Respond(200, new Resp(true, citaDb.deleteCita(id), null));
    }
}
