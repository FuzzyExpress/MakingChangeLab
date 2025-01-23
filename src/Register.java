import static Print.Print.print;

public class Register {
    // takes an amt and returns a Purse containing that amount in the fewest number of bills and coins.
    private static void calculate(Purse purse, double amt)
    {
        purse.add( DenominationController.Types().get(amt), 1 );
        // print("purse.add(" + amt + ")");
    }


    public static Purse makeChange(double amt)
    {
        Purse purse = new Purse();
        do {
            amt = Math.round(amt * 100.0) / 100.0;

            do {
                int l = DenominationController.Values().length;
                // Applied is needed so that the loop only uses the larges value.
                boolean applied = false;
                for (int i = l-1; i >= 0 ; i--) {
                    double num = DenominationController.Values()[i];
                    if (amt >= num && !applied)
                    {
                        applied = true;
                        calculate(purse, num);
                        amt -= num;
                        // print(amt, num, i);
                    }
                }
                // Less than 1 cent is needed to prevent
                // rounding errors from jamming the loop
            } while (amt > 0.01);

        } while (amt > 0);
        return purse;
    }
}
