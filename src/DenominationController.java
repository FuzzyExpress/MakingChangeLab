import java.util.HashMap;
import java.util.Map;

public class DenominationController
{
    public static Map<Double, Denomination> Types()
    {
        int[] types = {1, 5, 10, 25, 50, 1, 2, 5, 10, 25, 50, 100, 250, 500, 1000};

        Map<Double, Denomination> denominations = new HashMap<>();

        for (int i = 0; i < 5+9+1; i++)
        {
            String name = (i < 5 ? "Coin" : "Paper") + "_" + types[i];
            double amt  = (i < 5 ? 0.01 : 1) * types[i];
            Form form   = (i < 5 ? Form.coin : Form.bill);
            denominations.put( amt, new Denomination(name, amt, form, "images/" + types[i] + ".png") );
            //denominations.add( new Denomination(name, amt, form, "images/" + types[i] + ".png") );
            // print(denominations.get(i));
        }
        return denominations;
    }
}