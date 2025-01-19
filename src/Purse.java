import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;

/*
Purse (class)
cash: Map<Denomination, Integer> // represents the money in the purse
add(type: Denomination, num: int): void // adds a number of a particular denomination
remove(type: Denomination, num: int): double //diminishes the money in the purse and returns that amount.
getValue(): double // returns the amount of money in the Purse
toString(): String // returns a string representation of the Purse and its contents
*/
public class Purse {
    public Map<Denomination, Integer> cash = new HashMap<Denomination, Integer>();

    void purse()
    {}

    void purse(double amt)
    {
        cash = Register.makeChange(amt).cash;
    }

    public void add(Denomination denomination, int amount)
    {
        if (cash.containsKey(denomination))
            cash.put(denomination, cash.get(denomination) + amount);
        else
            cash.put(denomination, amount);
    }
    public double remove(Denomination denomination, int amount)
    {
        double amt = this.getValue();
        amt -= amount;

        cash = Register.makeChange(amt).cash;
        return amt;
    }
    public double getValue()
    {
        double total = 0;
        for (double cash : cash.values())
        {
            total += cash;
        }
        return total;
    }

    @Override
    public String toString() {
        String str = "< Purse: ";
        // Convert to list and sort
        List<Map.Entry<Denomination, Integer>> sortedEntries = 
            new ArrayList<>(cash.entrySet());
        
        Collections.sort(sortedEntries, 
            (e1, e2) -> Double.compare(e2.getKey().amt(), e1.getKey().amt()));
        
        for (Map.Entry<Denomination, Integer> entry : sortedEntries) {
            str += entry.getKey().displayName() + ": " + entry.getValue() + ", ";
        }
        return str + ">";
    }
}
