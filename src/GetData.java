public abstract class GetData {
    private String playerNameEntry;
    private int elapesedPLayerTimeEntry;
    private PlayerDatabase dataBase;

    protected GetData(String playerNameEntry, int elapesedPLayerTimeEntry) {
        this.dataBase = new PlayerDatabase();
        this.playerNameEntry = playerNameEntry;
        this.elapesedPLayerTimeEntry = elapesedPLayerTimeEntry;
    }

    public String getPlayerName() {
        return this.playerNameEntry;
    }

    public int getElapesedPLayerTime() {
        return this.elapesedPLayerTimeEntry;
    }

    public PlayerDatabase getDataBase() {
        return this.dataBase;
    }

    public abstract void execute();
}