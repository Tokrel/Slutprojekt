//klass som ärver från klassen Getdata 

public class RegisterPlayer extends GetData{
    //konstructor som matar in namnet och tiden rill getdata 
    public RegisterPlayer(String playerName, int elapesedPLayerTime) {
        super(playerName, elapesedPLayerTime);
    }

    //metod som Overridear metoden execute i get data och registerar spelaren till database 
    @Override
    public void execute() {
        Player newUser = new Player(getPlayerName(), getElapesedPLayerTime());
        getDataBase().addDatabase(newUser);
       


    }


}