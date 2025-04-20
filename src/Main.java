import java.util.Map;

import static Print.Print.print;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        Purse wallet = new Purse();

        // Set the default change strategy (you can change it to DynamicProgrammingChangeStrategy)
        Register.setChangeStrategy(new GreedyChangeStrategy());

        java.util.Scanner scanner = new java.util.Scanner(System.in);

        String str = "";
        do {
            print("[q] to quit\n[mk] <Double> to makeChange\n[rm] <Double> <Integer> to remove something\n[ad] <Double> <Integer> to add something\n[ls] to list Denominations\n[st] [g/d] to set strategy (g=greedy, d=dynamic)");
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
                            rm = wallet.remove(DenominationController.getInstance().getTypes().get(amount), count);
                            print("Removed: " + rm + ", now: " + wallet);
                        } catch (ArithmeticException e) {
                            print("You do not have sufficient funds.");
                        }
                        break;

                    case "ad":
                        amount = Double.parseDouble(strs[1]);
                        count = Integer.parseInt(strs[2]);
                        wallet.add(DenominationController.getInstance().getTypes().get(amount), count);
                        print(wallet);
                        break;

                    case "ls":
                        count = 0;
                        String outString = "";
                        // \t is tab. We use these to make ls take less vertical space.
                        for (Map.Entry<Double, Denomination> entry : DenominationController.getInstance().getTypes().entrySet()) {
                            outString += entry.getValue().displayName() + (count <= 2 ? "\t\t" : "\n");
                            count++;
                            if (count > 3) {
                                count = 0;
                            }
                        }
                        print(outString);
                        break;
                        
                    case "st":
                        if (strs.length > 1 && strs[1].equals("g")) {
                            Register.setChangeStrategy(new GreedyChangeStrategy());
                            print("Change strategy set to Greedy (uses largest denominations first)");
                        } else if (strs.length > 1 && strs[1].equals("d")) {
                            Register.setChangeStrategy(new DynamicProgrammingChangeStrategy());
                            print("Change strategy set to Dynamic Programming (optimizes for fewest coins)");
                        } else {
                            print("Unknown strategy. Use 'g' for greedy or 'd' for dynamic programming.");
                        }
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