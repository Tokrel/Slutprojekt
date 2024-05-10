import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)throws Exception  {

        Scanner scanner = new Scanner(System.in);
        int difficulty;
       
        //kollar vilken svårhetsgrad spelaren vill köra 
       while(true){
        System.out.println("Skriv in vilken difficulty du vill ha (1-2):");
            try {
                difficulty = (scanner.nextInt());
                if (difficulty < 1 || difficulty > 2) {
                    throw new InputMismatchException();
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Ogiltigt val försök igen");
                scanner.nextLine(); 
            }
        } 

    // startar olika minesweepers baserat difficulty
    if(difficulty == 1){
        Minesweeper minesweeper = new Minesweeper(8,1);
    }
    else if(difficulty == 2){
        Minesweeper minesweeper = new Minesweeper(12,22);
    }
        

    


    }
}


       

