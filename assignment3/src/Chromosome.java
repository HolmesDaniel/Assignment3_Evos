import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Chromosome
{
    private final int RANGE_MIN = 2500;
    private final int RANGE_MAX = 15000;
    private double[] foodItems;

    /**
     * Creates a new random chromosome
     */
    public Chromosome()
    {
        foodItems = new double[77];
        Random r = new Random();
        for(int i = 0; i < foodItems.length; i++)
        {
            foodItems[i] = ThreadLocalRandom.current().nextDouble(RANGE_MIN, RANGE_MAX);
        }
    }

    /**
     * Creates a new chromosome from 2 parent chromosomes
     */
    public Chromosome(Chromosome parentA, Chromosome parentB)
    {
        Random r = new Random();
        int coin;
        double value;

        foodItems = new double[77];

        for (int i = 0; i < foodItems.length; i++)
        {
            coin = r.nextInt(2);

            if (coin == 0)
                value = parentA.getFoodItem(i);
            else
                value = parentB.getFoodItem(i);

            double randomNo = r.nextGaussian();;

            while(value + randomNo < 0)
            {
                randomNo = r.nextGaussian();;
            }
            value += randomNo;
            foodItems[i] = value;
        }
    }

     /**
     * Gets a specific food item
     * @param i The index of the food item
     * @return The number of grams of the food item
     */
    private double getFoodItem(int i)
    {
        return foodItems[i];
    }

    public double[] getFoodItems(){return foodItems;}

    /**
     * @return The cost of the solution
     */
    public double getCost()
    {
        double cost = 0;
        for (int i = 0; i < foodItems.length; i++)
        {
            cost += foodItems[i] / Main.getContent(i, Main.WEIGHT);
        }
        return cost;
    }

    /**
     * @return The nutritional value of the solution
     */
    public double[] getNutritionalVal()
    {
        double[] values = new double[9];
        Arrays.fill(values, 0.0);
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < foodItems.length; j++) {
                values[i] += (foodItems[j] / Main.getContent(j, Main.WEIGHT)) * Main.getContent(j, Main.NUTRITIONAL_NAMES[i]);
            }
        }

        return values;
    }

}
