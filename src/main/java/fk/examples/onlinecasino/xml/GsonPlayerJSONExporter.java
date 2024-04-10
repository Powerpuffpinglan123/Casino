package fk.examples.onlinecasino.xml;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fk.examples.onlinecasino.model.Player;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GsonPlayerJSONExporter {

    public static void exportPlayersToJson(List<Player> players, String filePath) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(players, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Ange sökvägen till XML-filen med spelarinformation
        String xmlFilePath = "players.xml";
        
        // Importera spelare från XML-filen
        List<Player> players = JcabiPlayerXMLImporter.importPlayers(xmlFilePath);

        // Ange sökvägen till JSON-filen där spelarobjekten ska exporteras
        String jsonFilePath = "players.json";

        // Anropa exportPlayersToJson-metoden med den importerade listan av spelare och sökvägen till JSON-filen
        exportPlayersToJson(players, jsonFilePath);

        // Meddela användaren om att exporten har slutförts
        System.out.println("Players have been exported to JSON file: " + jsonFilePath);
    }
}