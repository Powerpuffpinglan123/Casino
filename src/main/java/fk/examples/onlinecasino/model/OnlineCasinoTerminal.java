package fk.examples.onlinecasino.model;

public class OnlineCasinoTerminal {
    public static void main(String[] args) {
        // Hämta listan över spelare från någonstans
        List<Player> players = ...; // Till exempel, anropa importPlayers-metoden

        // Ange filvägen för att spara JSON-filen
        String jsonFilePath = "players.json";

        // Anropa export-metoden för att konvertera och spara spelardata till JSON
        GsonPlayerJSONExporter.exportPlayersToJson(players, jsonFilePath);
    }
}

}
