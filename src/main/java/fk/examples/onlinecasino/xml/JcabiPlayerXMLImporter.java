package fk.examples.onlinecasino.xml;

import com.jcabi.xml.XML;
import com.jcabi.xml.XMLDocument;
import fk.examples.onlinecasino.model.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/*  En tom lista av typen ArrayList skapas och tilldelas till variabeln players.
    Spelarinformationen läses in från en XML-fil och bearbetas.
    En förbättrad loop används för att iterera över alla Player-element som hittas i XML-dokumentet.
    För varje Player-element i loopen extraheras informationen och skapas ett nytt Player-objekt.
    Det nya Player-objektet läggs till i players-listan med hjälp av add()-metoden.
    
    Sammanfattningsvis, 
    spelarinformationen behandlas direkt från XML-filen och skapas som Player-objekt innan de läggs till i players-listan. 
    Det finns ingen temporär lagring av spelarinformationen utanför loopen.
 */


public class JcabiPlayerXMLImporter {

    public static List<Player> importPlayers(String filePath) {
        List<Player> players = new ArrayList<>();
        try {
            // Skapa ett XML-objekt baserat på filen
            XML xml = new XMLDocument(new File(filePath));
            /* new File(filePath): 
        	 * Detta skapar ett File-objekt från en fil på filsystemet. 
        	 * filePath är sökvägen till XML-filen.
        	 * new XMLDocument(...): 
        	 * Detta skapar ett XMLDocument-objekt från filen som skapades i steg 1. 
        	 * XMLDocument är en del av biblioteket jcabi-xml, som används för att behandla XML-data i Java.
        	 * XML xml = ...: 
        	 * Detta tilldelar det skapade XMLDocument-objektet till en variabel med namnet xml. 
        	 * Nu kan variabeln xml användas för att arbeta med XML-data från den angivna filen.
        	 * 
        	 * Så sammanfattningsvis, 
        	 * den här koden läser innehållet i en XML-fil från filsystemet 
        	 * och lagrar det i ett XML-objekt för att möjliggöra bearbetning av XML-data i Java-koden.
        	 */
            
            // Hämta alla <Player>-element
            for (XML playerXml : xml.nodes("//Player")) 
            /* xml.nodes("//Player"): 
             * Detta är ett anrop till nodes()-metoden på xml-objektet. 
             * Den här metoden använder ett XPath-uttryck för att hitta alla noder i XML-dokumentet som matchar det angivna uttrycket. 
             * I detta fall är uttrycket "//Player", vilket innebär att den söker efter alla "Player"-element oavsett var de finns i XML-strukturen.
             * XML playerXml : ...: 
             * Det här är en förbättrad loop (enhanced for loop) som används för att iterera över resultatet från xml.nodes("//Player"). 
             * Varje iteration tilldelar playerXml-variabeln en referens till den aktuella noden i loopen.
             * 
             * Så sammanfattningsvis, 
             * den här koden loopar över alla "Player"-element i XML-dokumentet representerat av xml-objektet och behandlar varje element separat inuti loopen.
             */
            {
            	
                // Extrahera information för varje spelare
                String id = playerXml.xpath("@id").get(0);
                /*playerXml.xpath("@id"): 
                 * Detta anropar xpath()-metoden på playerXml-objektet och specificerar ett XPath-uttryck @id. 
                 * Detta uttryck hämtar värdet av attributet "id" från den aktuella noden.
                 * .get(0): 
                 * Eftersom xpath()-metoden returnerar en lista av matchande noder eller attribut, används .get(0) för att hämta det första värdet i listan. 
                 * I det här fallet antas det att det bara finns ett attribut med namnet "id".
                 * String id = ...: 
                 * Det här tilldelar värdet av attributet "id" till en variabel med namnet id. 
                 * Värdet kommer att vara av typen String, eftersom attributvärden vanligtvis representeras som strängar i XML.
                 * 
                 * Så sammanfattningsvis, 
                 * den här koden hämtar värdet av attributet "id" från den aktuella XML-noden och tilldelar det till variabeln id.
                 */
                //String Name = playerXml.xpath("Name/text()").get(0);
                String firstName = playerXml.xpath("FirstName/text()").get(0);
                String lastName = playerXml.xpath("LastName/text()").get(0);
                int credit = Integer.parseInt(playerXml.xpath("Credit/text()").get(0));
                String email = playerXml.xpath("Email/text()").get(0);
                String password = playerXml.xpath("Password/text()").get(0);
                
                // Plocka ut FirstName och LastName från Name
                //String[] parts = Name.split(" ");
                //String firstName = parts[0];
                //String lastName = parts[1];

                // Skapa ett Player-objekt och lägg till det i listan
                players.add(new Player(id, firstName, lastName, credit, email, password));
                /* new Player(id, firstName, lastName, credit, email, password): 
                 * Detta skapar en ny instans av Player-klassen med hjälp av dess konstruktor. 
                 * Konstruktorn tar in flera parametrar som behövs för att skapa en spelare. 
                 * Parametrarna som används verkar vara id, firstName, lastName, credit, email och password.
                 * players.add(...): Här läggs det nyss skapade Player-objektet till listan players med hjälp av add()-metoden. 
                 * Detta gör att spelarobjektet blir en del av listan och kan användas senare i programmet.
                 * 
                 * Sammanfattningsvis, koden skapar en ny spelare med givna attribut och lägger till den i listan players. 
                 * Antagligen representerar listan alla spelare som har extraherats från XML-filen i tidigare delar av koden.
                 */
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return players;
    }

    public static void main(String[] args) {
        // Anropa importPlayers-metoden och skriv ut resultaten
        List<Player> players = importPlayers("players.xml");
        for (Player player : players) {
            System.out.println(player.toString());
        }
    }
}