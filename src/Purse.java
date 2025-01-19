import java.util.HashMap;
import java.util.Map;

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


    public void add(Denomination denomination, int amount)
    {
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
        String str = "Contents: ";
        for ( Map.Entry<Denomination, Integer> entry : cash.entrySet() )
        {
            str += entry.getKey() + " -> " + entry.getValue();
        }
        return str;
    }
}
