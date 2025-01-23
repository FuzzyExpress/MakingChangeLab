import static Print.Print.print;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        Purse wallet = new Purse();
        // wallet.add(DenominationController.Types().get(0.50), 5);
        // print (wallet);

        java.util.Scanner scanner = new java.util.Scanner(System.in);

        String str = "";
        do {
            print("\n Input a cash amount [q to leave]: ");
            str = scanner.nextLine();
            try
            {
                Purse change = Register.makeChange( Double.parseDouble(str) );
                print(change);
            }
            catch (NumberFormatException e)
            {
                if (str.equals("q"))
                    break;
                print("That isn't a number.");
            }

        } while (true);
        scanner.close();

    }
}