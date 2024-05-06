import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)throws Exception  {

        Scanner scanner = new Scanner(System.in);
        int Svårhetsgrad;
       

       while(true){
        System.out.println("Skriv in vilken Svårhetsgrad du vill ha (1-3):");
    try {
        Svårhetsgrad = (scanner.nextInt());
        if (Svårhetsgrad < 1 || Svårhetsgrad > 3) {
            throw new InputMismatchException();
        }
        break;
    } catch (InputMismatchException e) {
        System.out.println("Ogiltigt val försök igen");
        scanner.nextLine(); 
    }

       } 

    if(Svårhetsgrad == 1){
        Minesweeper minesweeper = new Minesweeper(6);
    }
    else if(Svårhetsgrad == 2){
        Minesweeper minesweeper = new Minesweeper(8);
    }
    else if(Svårhetsgrad == 3){
        Minesweeper minesweeper = new Minesweeper(12);
    }
        

    


    }
}


       

