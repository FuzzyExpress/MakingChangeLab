import java.util.HashMap;
import java.util.Map;

public class DenominationController
{
    private static final int[] types = {1, 5, 10, 25, 50, 1, 2, 5, 10, 25, 50, 100, 250, 500, 1000};
    public static Map<Double, Denomination> Types()
    {   // Loop to construct dictionary of denominations
        Map<Double, Denomination> denominations = new HashMap<>();

        for (int i = 0; i < 5+9+1; i++)
        {
            String name = (i < 5 ? "Coin" : "Paper") + "_" + types[i];
            String displayName = (i < 5 ? "¢" : "$") + types[i];
            double amt  = (i < 5 ? 0.01 : 1) * types[i];
            Form form   = (i < 5 ? Form.coin : Form.bill);
            denominations.put( amt, new Denomination(name, displayName, amt, form, "images/" + types[i] + ".png") );
        }
        return denominations;
    }

    // Make a separate list of the value types to make looping easier in other zones
    public static double[] Values()
    {
        double[] values = new double[ types.length ];
        for (int i = 0; i < values.length; i++)
        {
            values[i] = (i < 5 ? 0.01 : 1) * types[i];
        }
        return values;
    }
}