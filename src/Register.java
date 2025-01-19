import java.util.HashMap;
import java.util.Map;


import static Print.Print.print;

public class Register {
    // takes an amt and returns a Purse containing that amount in the fewest number of bills and coins.
    private static void calculate(Purse purse, double amt)
    {
        purse.add( DenominationController.Types().get(amt), 1 );
        print("purse.add(" + amt + ")");
    };

    public static Purse makeChange(double amt)
    {
        Purse purse = new Purse();
        do {
            amt = Math.round(amt * 100.0) / 100.0;
            
            if      (amt >= 1000)
                { calculate(purse, 1000d); amt -= 1000d; }
            else if (amt >= 500)
                { calculate(purse, 500d); amt -= 500d; }
            else if (amt >= 250)
                { calculate(purse, 250d); amt -= 250d; }
            else if (amt >= 100)
                { calculate(purse, 100d); amt -= 100d; }
            else if (amt >= 50)
                { calculate(purse, 50d); amt -= 50d; }
            else if (amt >= 25)
                { calculate(purse, 25d); amt -= 25d; }
            else if (amt >= 10)
                { calculate(purse, 10d); amt -= 10d; }
            else if (amt >= 5)
                { calculate(purse, 5d); amt -= 5d; }
            else if (amt >= 2)
                { calculate(purse, 2d); amt -= 2d; }
            else if (amt >= 1)
                { calculate(purse, 1d); amt -= 1d; }

            else if (amt >= 0.50)
                { calculate(purse, 0.50); amt -= 0.50; }
            else if (amt >= 0.25)
                { calculate(purse, 0.25); amt -= 0.25; }
            else if (amt >= 0.10)
                { calculate(purse, 0.10); amt -= 0.10; }
            else if (amt >= 0.05)
                { calculate(purse, 0.05); amt -= 0.05; }
            else if (amt >= 0.01)
                { calculate(purse, 0.01); amt -= 0.01; }
            else
                print("Warn: Less than 1 cent dropped:", amt);

            print(amt);

        } while (amt > 0);

        return purse;
    }
}
