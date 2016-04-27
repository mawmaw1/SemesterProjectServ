/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import services.AirlineConnector;

/**
 * REST Web Service
 *
 * @author Magnus
 */
@Path("data")
public class Data {

    AirlineConnector ac = new AirlineConnector();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Data
     */
    public Data() {
    }

    /**
     * Retrieves representation of an instance of rest.Data
     *
     * @param airport
     * @param date
     * @param persons
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{airport}/{date}/{persons}")
    public String getAllFlightsFromDate(
            @PathParam("airport") String airport,
            @PathParam("date") String date,
            @PathParam("persons") int persons) throws InterruptedException, ExecutionException, IOException {

        JsonArray result = new JsonArray();
        List<Future<String>> list = ac.ConnectToAirlinesFromDatePersons(airport, date, persons);
JsonObject errorTest2 = (new JsonParser()).parse("{\n" +
"  \"error\": {\n" +
"    \"code\": 500,\n" +
"    \"message\": \"Flight is sold out, or not enough available tickets.\"\n" +
"  }\n" +
"}").getAsJsonObject();
        result.add(errorTest2);
        for (Future<String> list1 : list) {
            
            
            JsonObject jsonObject = (new JsonParser()).parse(list1.get()).getAsJsonObject();
            
            result.add(jsonObject);

        }
        JsonObject errorTest = (new JsonParser()).parse("{\n" +
"  \"error\": {\n" +
"    \"code\": 500,\n" +
"    \"message\": \"Flight is sold out, or not enough available tickets.\"\n" +
"  }\n" +
"}").getAsJsonObject();
        result.add(errorTest);
        return gson.toJson(result);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{from}/{to}/{date}/{persons}")
    public String getAllFlightsFromToDate(
            @PathParam("from") String from,
            @PathParam("to") String to,
            @PathParam("date") String date,
            @PathParam("persons") int persons) throws InterruptedException, ExecutionException, IOException {

        JsonArray result = new JsonArray();
        List<Future<String>> list = ac.ConnectToAirlinesFromToDatePersons(from,to,date, persons);

        for (Future<String> list1 : list) {
            
            JsonObject jsonObject = (new JsonParser()).parse(list1.get()).getAsJsonObject();
            
            result.add(jsonObject);
            
        }

        return gson.toJson(result);
    }

    /**
     * PUT method for updating or creating an instance of Data
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
