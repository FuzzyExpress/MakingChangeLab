import java.util.HashMap;
import java.util.Map;

import static Print.Print.print;

public class Register {
    // takes an amt and returns a Purse containing that amount in the fewest number of bills and coins.
    public static Purse makeChange(double amt)
    {
        Map<Denomination, Integer> cash = new HashMap<Denomination, Integer>();

        do {
            Denomination denomination = switch (amt) {
                case 1 ->
            }

        } while (amt > 0);

    }
}
