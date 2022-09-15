public class Chromosome 
{
    final int SIZE = 77;
    final int FOOD_CONST = 5;
    double[] foodItems;

    public Chromosome()
    {
        foodItems = new double[SIZE];
        setRandom();
    }

    private void setRandom()
    {
        Random r = new Random();
        for(int i; i = 0; i < foodItems.length; i++)
        {
            foodItems[i] = r.nextDouble() * FOOD_CONST;
        }
    }

    public double getFoodItem(int i)
    {
        return foodItems[i];
    }

    public double getCost()
    {
        return 0.0;
    }

}
