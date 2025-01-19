import java.util.ArrayList;
import static Print.Print.print;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");



        Purse wallet = new Purse();

        wallet.add(DenominationController.Types().get(0.50), 5);

        print (wallet);


        java.util.Scanner scanner = new java.util.Scanner(System.in);
        
        while (scanner.hasNextDouble()) {
            double amount = scanner.nextDouble();
            Purse change = Register.makeChange(amount);
            print(change);
        }
        
        scanner.close();

    }
}