/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import rest.JsonResponseChecker;
import services.AirlineConnector;

/**
 *
 * @author Magnus
 */
public class Tester {

    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonResponseChecker jrc = new JsonResponseChecker();
        AirlineConnector ac = new AirlineConnector();
        List<Future<String>> list = ac.ConnectToAirlinesFromToDatePersons("CPH", "STN", "2016-04-25T19:00:00.000Z", 2);

        for (Future<String> list1 : list) {

            JsonObject jsonObject = (new JsonParser()).parse(list1.get()).getAsJsonObject();
            JsonObject hj = new JsonObject();
            hj.addProperty("hej", "hej");
            String test = jsonObject.get("flights").getAsJsonArray().get(0).getAsJsonObject().get("flightID").getAsString();
            System.out.println(test);

            boolean test2 = jrc.checkJson(hj);
            System.out.println(test2);
            
        }

    }

}
