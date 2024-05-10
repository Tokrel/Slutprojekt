//klass för att spara spelare

public class Player {

    private String playerName;
    private int elapesedPLayerTime;


    public Player( String playerName, int elapesedPLayerTime) {
        this.playerName = playerName;
        this.elapesedPLayerTime = elapesedPLayerTime;
    }
    public Player() {

    }

    public String getPlayerName() {
        return this.playerName;
    }

    public int getElapesedPLayerTime() {
        return this.elapesedPLayerTime;
    }


}