import static Print.Print.print;

// Strategy interface for different change-making algorithms
interface ChangeStrategy {
    Purse makeChange(double amount);
}

// Greedy algorithm implementation - uses largest denominations first
class GreedyChangeStrategy implements ChangeStrategy {
    @Override
    public Purse makeChange(double amount) {
        // gosh darn rounding errors ruining everything
        // Blow up the Doubles and replace them is Ints!!
        int cents = (int) (amount * 100);
        Purse purse = new Purse();
        
        do {
            int l = DenominationController.getInstance().getValues().length;
            // Applied is needed so that the loop only uses the larges value.
            boolean applied = false;
            for (int i = l-1; i >= 0; i--) {
                int num = (int) (DenominationController.getInstance().getValues()[i] * 100);
                if (cents >= num && !applied) {
                    applied = true;
                    double denomValue = (double) num / 100;
                    purse.add(DenominationController.getInstance().getTypes().get(denomValue), 1);
                    cents -= num;
                }
            }
            // Less than 1 cent is needed to prevent
            // rounding errors from jamming the loop
        } while (cents > 1);
        
        return purse;
    }
}

// Dynamic programming implementation - optimizes for fewest coins
class DynamicProgrammingChangeStrategy implements ChangeStrategy {
    @Override
    public Purse makeChange(double amount) {
        // Convert amount to cents to avoid floating-point errors
        int cents = (int) (amount * 100);
        double[] denomValues = DenominationController.getInstance().getValues();
        
        // Convert denomination values to cents
        int[] denomCents = new int[denomValues.length];
        for (int i = 0; i < denomValues.length; i++) {
            denomCents[i] = (int) (denomValues[i] * 100);
        }
        
        // DP table to store minimum coins needed
        int[] minCoins = new int[cents + 1];
        int[] coinUsed = new int[cents + 1];
        
        // Initialize with max value
        for (int i = 1; i <= cents; i++) {
            minCoins[i] = Integer.MAX_VALUE - 1;
        }
        
        // Fill the DP table
        for (int i = 0; i < denomCents.length; i++) {
            for (int j = denomCents[i]; j <= cents; j++) {
                if (minCoins[j - denomCents[i]] + 1 < minCoins[j]) {
                    minCoins[j] = minCoins[j - denomCents[i]] + 1;
                    coinUsed[j] = i;
                }
            }
        }
        
        // Reconstruct the solution
        Purse purse = new Purse();
        int remaining = cents;
        
        while (remaining > 0) {
            int denomIndex = coinUsed[remaining];
            double denomValue = denomValues[denomIndex];
            purse.add(DenominationController.getInstance().getTypes().get(denomValue), 1);
            remaining -= denomCents[denomIndex];
        }
        
        return purse;
    }
}

public class Register {
    private static ChangeStrategy changeStrategy = new GreedyChangeStrategy();
    
    // Set the change-making strategy
    public static void setChangeStrategy(ChangeStrategy strategy) {
        changeStrategy = strategy;
    }
    
    // Make change using the current strategy
    public static Purse makeChange(double amt) {
        return changeStrategy.makeChange(amt);
    }
}
