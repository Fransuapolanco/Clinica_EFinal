/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import javax.ws.rs.core.Response;
import com.google.gson.Gson;
import models.Resp;

/**
 *
 * @author erika
 */
public class ResponseWriter {

    public static Response Respond(int statusCode, Resp responseStructure) {
        Gson gson = new Gson();
        String jsonResp = gson.toJson(responseStructure);

        return Response.status(statusCode).entity(jsonResp).build();
    }

    public static Response InternalServerError(Exception error) {
        System.err.println(error);
        return Response.serverError().entity("Internal Server Error").build();
    }
}
