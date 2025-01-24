import java.util.Map;

import static Print.Print.print;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        Purse wallet = new Purse();

        java.util.Scanner scanner = new java.util.Scanner(System.in);

        String str = "";
        do {
            print("[q] to quit\n[mk] <Double> to makeChange\n[rm] <Double> <Integer> to remove something\n[ad] <Double> <Integer> to add something\n[ls] to list Denominations");
            print("\nwhat do you want to do?");
            str = scanner.nextLine();
            // command arguments
            String[] strs = str.strip().split(" ");
            double amount; int count;

            try
            {
                switch (strs[0]) {
                    case "q":
                        System.exit(0);

                    case "mk":
                        if (Double.parseDouble(strs[1]) < 0.01)
                        {
                            print("You can't have negative cash.");
                        }
                        wallet = new Purse(Double.parseDouble(strs[1]));
                        print(wallet);
                        break;

                    case "rm":
                        amount = Double.parseDouble(strs[1]);
                        count = Integer.parseInt(strs[2]);
                        double rm = 0;
                        try {
                            rm = wallet.remove(DenominationController.Types().get(amount), count);
                            print("Removed: " + rm + ", now: " + wallet);
                        } catch (ArithmeticException e) {
                            print("You do not have sufficient funds.");
                        }
                        break;

                    case "ad":
                        amount = Double.parseDouble(strs[1]);
                        count = Integer.parseInt(strs[2]);
                        wallet.add(DenominationController.Types().get(amount), count);
                        print(wallet);
                        break;

                    case "ls":
                        count = 0;
                        String outString = "";
                        // \t is tab. We use these to make ls take less vertical space.
                        for (Map.Entry<Double, Denomination> entry : DenominationController.Types().entrySet()) {
                            outString += entry.getValue().displayName() + (count <= 2 ? "\t\t" : "\n");
                            count++;
                            if (count > 3) {
                                count = 0;
                            }
                        }
                        print(outString);
                        break;

                    default:
                        print("Unknown command.");
                        break;
                }
            } catch (NumberFormatException e)
            {
                print("Invalid Input.");
            } catch (ArrayIndexOutOfBoundsException e)
            {
                print("Invalid Parameters.");
            }
        } while (true);
    }
}