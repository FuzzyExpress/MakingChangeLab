import java.util.Map;
import java.util.TreeMap;

public class DenominationController
{
    // Here I create a list of ints, and then transform those into Denominations.
    private static final int[] types = {1, 5, 10, 25, 50, 1, 2, 5, 10, 25, 50, 100, 250, 500, 1000};
    public static Map<Double, Denomination> Types()
    {   // Loop to construct dictionary of denominations
        Map<Double, Denomination> denominations = new TreeMap<>(Double::compareTo); // CompareTo makes it auto sort.
        // TreeMap<Denomination, Integer> cash = new TreeMap<Denomination, Integer>(new DenominationCompare());

        for (int i = 0; i < 5+9+1; i++)
        {
            String name = (i < 5 ? "Coin" : "Paper") + "_" + types[i];
            String displayName = (i < 5 ? "¢" : "$") + types[i];
            double amt  = (i < 5 ? 0.01 : 1) * types[i];
            Form form   = (i < 5 ? Form.coin : Form.bill);
            // /home/evans/Documents/Java/MakingChangeLab/src/images/!/
            denominations.put( amt, new Denomination(name, displayName, amt, form, "src/images/!/" + name + ".png") );
        }
        return denominations;
    }

    public static String getImage(Denomination d, String type)
    {
        // this split and replace allows for having 2 images with only one item
        String[] strs = d.img().split("!");
        return strs[0] + type + strs[1];
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