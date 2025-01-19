import java.util.ArrayList;
import static Print.Print.print;
import static DenominationController.Types;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");



        Purse wallet = new Purse();

        wallet.add(Types().get(0.50), 5);

        print (wallet);


    }
}