public class EvolutionaryAlgorithm 
{
    private final double[] MIN_REQUIREMENTS = new double[9]
    //{Calories, Protein, Calcium, Iron, Vit A, VB1, VB2, Niacin, Absorbic Acid}
    {1095, 25550, 292, 4380, 1825000, 657, 985.5, 6570, 27375} ;
    
    private final int POPULATION_SIZE = 200;
    private final int ITERATIONS = 1000000;
    private final int TOURNAMENT_SIZE = 10;

    private double[][] itemContents; 
    private ArrayList<Chromosome> population; 

    public EvolutionaryAlgorithm(double[][] itemContents)
    {
        this.itemContents = itemContents;
        population = new ArrayList<>();
         for(int i; i = 0; i < POPULATION_SIZE; i++)
        {
            population.add(new Chromosome());
        }
        runAlgorithm();
    }

    private void runAlgorithm()
    {
        int t = 0;
        while (t <= ITERATIONS)
        {
            removeUnfit();
            while (population.size < POPULATION_SIZE - 1)
            {
                population.add(createChild());
            }
            t++
        }
    }

    private void removeUnfit()
    {

        
        double curCost, minCost;
        Chromosome curMin, cur;
        
        for(int j; j = 0; j < POPULATION_SIZE/TOURNAMENT_SIZE; j++)
        {
            minCost = (double)Integer.MAX_VALUE;
            for(int i; i = 0; i < TOURNAMENT_SIZE; i++)
             {
                cur = population.remove(0);
                curCost = cur.getCost()
                if(minCost > curCost)
                    {
                        curMin = cur;
                        minCost = curCost;
                    }
            }

            population.add(curMin);               
        }
        
    }

    private Chromosome createChild()
    {
        return new Chromosome();
    }




    

}
