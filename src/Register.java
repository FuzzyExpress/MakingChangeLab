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
        // gosh darn rounding errors ruining everything
        // Blow up the Doubles and replace them is Ints!!
        int cents = (int) (amt * 100);
        Purse purse = new Purse();
        do {
            //amt = Math.round(amt * 100.0) / 100.0;
            do {
                int l = DenominationController.Values().length;
                // Applied is needed so that the loop only uses the larges value.
                boolean applied = false;
                for (int i = l-1; i >= 0 ; i--) {
                    int num = (int) ( DenominationController.Values()[i] * 100 );
                    if (cents >= num && !applied)
                    {
                        applied = true;
                        calculate(purse, (double) num /100);
                        cents -= num;
                        // print(amt, num, i);
                    }
                }
                // Less than 1 cent is needed to prevent
                // rounding errors from jamming the loop
            } while (cents > 1);

        } while (cents > 0);
        return purse;
    }
}
