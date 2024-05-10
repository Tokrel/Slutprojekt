import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

//databas klass 
public class PlayerDatabase {
    //arraylist som innehåller object som har atributen namn och tid av dom som spleade 
    private static ArrayList<Player> dataBase = new ArrayList<Player>();

    static {
        // test spelare 
        dataBase.add(new Player( "Thor", 10));
        dataBase.add(new Player( "Herman", 140));
        dataBase.add(new Player( "Edwin", 38));
        dataBase.add(new Player( "Liv", 49));
    }


    //metod som sorterar objecten efter storlekårdning på tiden 
    public  ArrayList<Player>  sortByElapsedPlayerTime() {
        ArrayList<Player> sortedDatabase = new ArrayList<>(dataBase);
        Collections.sort(sortedDatabase, Comparator.comparingInt(Player::getElapesedPLayerTime));
        return sortedDatabase;
    }
    //metod för att lägga till en ny spelare i database
    public void addDatabase(Player newUser) {
        dataBase.add(newUser);
    }
}