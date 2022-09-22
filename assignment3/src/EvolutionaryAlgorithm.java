import com.opencsv.CSVWriter;

import javax.lang.model.type.ArrayType;
import javax.swing.event.ChangeEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class EvolutionaryAlgorithm
{

    final static String FILE_NAME = "assignment3\\Resources\\data.csv";
    private final double[] MIN_REQUIREMENTS =
    //{Calories, Protein, Calcium, Iron, Vit A, VB1, VB2, Niacin, Absorbic Acid}
    {1095, 25550, 292, 4380, 1825, 657, 985.5, 6570, 27375} ;
    
    private final int POPULATION_SIZE = 200;
    private final int ITERATIONS = 30000;
    private final int TOURNAMENT_SIZE = 5;

    private ArrayList<Chromosome> population;

    public EvolutionaryAlgorithm() throws IOException {
        population = new ArrayList<>();
         for(int i = 0; i < POPULATION_SIZE; i++)
        {
            population.add(new Chromosome());
        }
        runAlgorithm();
    }

    /**
     * Runs the algorithm
     */
    private void runAlgorithm() throws IOException {
        int t = 1;
        while (t <= ITERATIONS)
        {
            System.out.println(t);
            removeUnfit();
            int popSize = population.size();
            while (population.size() < POPULATION_SIZE - 1)
            {
                if (popSize != 0)
                    population.add(createChild(popSize));
                else
                    population.add(new Chromosome());
            }
            t++;
        }

        Chromosome cur;
        Chromosome curMin = population.get(0);
        for (int i = 1; i < population.size(); i++) {
            cur = population.get(i);
            if (getFitVal(cur) < getFitVal(curMin))
                curMin = cur;
        }

        System.out.println("Best Solution");
        double[] vals = curMin.getNutritionalVal();
        for (int i = 0; i < vals.length; i++)
        {
            System.out.print(Main.NUTRITIONAL_NAMES[i] + ": " + vals[i] +"\t");
            double diff = (MIN_REQUIREMENTS[i] - vals[i]);
            if (diff > 0)
            {
                System.out.println("("+diff+")");
            }
            else
            {
                System.out.println();
            }
        }
        System.out.println("Cost: " + curMin.getCost());

        vals = curMin.getFoodItems();
        File file = new File(FILE_NAME);
        FileWriter fw = new FileWriter(file,false);
        CSVWriter writer = new CSVWriter(fw);
        String[] header = { "Product Name", "Amount (g)"};
        writer.writeNext(header);
        for (int i = 0; i < vals.length; i++)
        {
            String[] values = {Main.ITEM_CONTENTS.get(i).getName(), Double.toString(vals[i])};
            writer.writeNext(values);
        }
        writer.close();
    }

    /**
     * Uses "tournaments" of a given size that only keeps the winner of the tournament to remove unfit chromosomes
     */
    private void removeUnfit()
    {
        ArrayList<Chromosome> list = new ArrayList<>();
        for(int j = 0; j < (POPULATION_SIZE/TOURNAMENT_SIZE); j++)
        {
            for(int i = 0; i < TOURNAMENT_SIZE; i++)
            {
                 list.add(population.remove(0));
            }
            population.add(doTournament(list));
            list.clear();
        }
        
    }

    private Chromosome doTournament(ArrayList<Chromosome> list)
    {
        double curCost;
        double minCost = (double) Integer.MAX_VALUE;
        Chromosome cur;
        Chromosome curMin = null;

        while (list.size() > 0)
        {
            cur = list.remove(0);
            curCost = getFitVal(cur);
            if (minCost >= curCost) {
                curMin = cur;
                minCost = curCost;
            }
        }
        return Objects.requireNonNullElseGet(curMin, Chromosome::new);
    }

    /**
     * Getting the fit value of a chromosome
     * @param cur The chromosome to get the value for
     * @return The fit value (the cost if it meets the criteria and the cost + the difference from the criteria if it does not)
     */
    private double getFitVal(Chromosome cur)
    {
        double[] nutritionalVal = cur.getNutritionalVal();
        double diff = 0;
        for (int i = 0; i < MIN_REQUIREMENTS.length; i++)
        {
            if (nutritionalVal[i] < MIN_REQUIREMENTS[i])
                diff = (MIN_REQUIREMENTS[i] - nutritionalVal[i]);
        }
        return cur.getCost() + (diff*5);
    }

    /**
     * Creates a new child based on two parents
     * @param size The size of the population before any children nodes are added
     * @return The new child node that is created
     */
    private Chromosome createChild(int size)
    {
        Chromosome parentA, parentB;

        Random r = new Random();
        int p = r.nextInt(size-TOURNAMENT_SIZE);
        int q = r.nextInt(size-TOURNAMENT_SIZE);

        while (q >= p && q<= p+TOURNAMENT_SIZE)
            q = r.nextInt(size-TOURNAMENT_SIZE);

        ArrayList<Chromosome> listA = new ArrayList<>(population.subList(p,p+TOURNAMENT_SIZE));
        ArrayList<Chromosome> listB = new ArrayList<>(population.subList(q,q+TOURNAMENT_SIZE));

        parentA = doTournament(listA);
        parentB = doTournament(listB);

        return new Chromosome(parentA, parentB);
    }




    

}
