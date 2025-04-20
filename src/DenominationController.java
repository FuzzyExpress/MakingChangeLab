import java.util.Map;
import java.util.TreeMap;

public class DenominationController
{
    // Singleton instance
    private static DenominationController instance;

    // Here I create a list of ints, and then transform those into Denominations.
    private static final int[] types = {1, 5, 10, 25, 50, 1, 2, 5, 10, 25, 50, 100, 250, 500, 1000};
    private Map<Double, Denomination> denominations;
    private double[] values;

    // Private constructor for Singleton pattern
    private DenominationController() {
        initializeDenominations();
        initializeValues();
    }

    // Get singleton instance
    public static synchronized DenominationController getInstance() {
        if (instance == null) {
            instance = new DenominationController();
        }
        return instance;
    }

    // Initialize denominations map
    private void initializeDenominations() {
        denominations = new TreeMap<>(Double::compareTo); // CompareTo makes it auto sort.
        
        for (int i = 0; i < 5+9+1; i++)
        {
            String name = (i < 5 ? "Coin" : "Paper") + "_" + types[i];
            String displayName = (i < 5 ? "¢" : "$") + types[i];
            double amt  = (i < 5 ? 0.01 : 1) * types[i];
            Form form   = (i < 5 ? Form.coin : Form.bill);
            denominations.put(amt, new Denomination(name, displayName, amt, form, "src/images/!/" + name + ".png"));
        }
    }

    // Initialize values array
    private void initializeValues() {
        values = new double[types.length];
        for (int i = 0; i < values.length; i++)
        {
            values[i] = (i < 5 ? 0.01 : 1) * types[i];
        }
    }

    public Map<Double, Denomination> getTypes() {
        return denominations;
    }

    public static String getImage(Denomination d, String type)
    {
        // this split and replace allows for having 2 images with only one item
        String[] strs = d.img().split("!");
        return strs[0] + type + strs[1];
    }

    public double[] getValues() {
        return values;
    }
}