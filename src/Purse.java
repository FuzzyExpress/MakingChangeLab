import java.util.*;

/*
Purse (class)
cash: Map<Denomination, Integer> // represents the money in the purse
add(type: Denomination, num: int): void // adds a number of a particular denomination
remove(type: Denomination, num: int): double //diminishes the money in the purse and returns that amount.
getValue(): double // returns the amount of money in the Purse
toString(): String // returns a string representation of the Purse and its contents
*/
// Comparator that sort elements according to marks in
// Ascending order
class DenominationCompare implements Comparator<Denomination> {
    public int compare(Denomination s1, Denomination s2)
    {
        if (s1.amt() == s2.amt())
            return 0;
        return s1.amt() > s2.amt() ? -1 : 1;
    }
}

public class Purse {
    //public Map<Denomination, Integer> cash = new HashMap<Denomination, Integer>();
    public TreeMap<Denomination, Integer> cash = new TreeMap<Denomination, Integer>(new DenominationCompare());

    public void purse()
    {}

    public void purse(double amt)
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

        int counter = 0;
        for (Map.Entry<Denomination, Integer> entry : cash.entrySet())
        {
            str += entry.getKey().displayName() + ": " + entry.getValue() + ( counter == cash.size() - 1 ? " " : ", ");
            counter++;
        }

        return str + ">";
    }
}
