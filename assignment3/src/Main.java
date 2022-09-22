import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.util.ArrayList;

public class Main
{
    final static String WEIGHT = "Weight";
    final static String CALORIES = "Calories";
    final static String PROTEIN = "Protein";
    final static String CALCIUM = "Calcium";
    final static String IRON = "Iron";
    final static String VITAMIN_A = "Vitamin A";
    final static String THIAMINE = "Thiamine";
    final static String RIBOFLAVIN = "Riboflavin";
    final static String NIACIN = "Niacin";
    final static String ABSORBIC_ACID = "Absorbic Acid";

    final static String FILE_NAME = "assignment3\\Resources\\ItemContents.csv";

    public static String[] NUTRITIONAL_NAMES =
    {CALORIES, PROTEIN, CALCIUM, IRON, VITAMIN_A, THIAMINE, RIBOFLAVIN, NIACIN, ABSORBIC_ACID};

    public static ArrayList<ItemContents> ITEM_CONTENTS;

    public static void main(String[] arg)
    {
        try
        {
            readIn();
            EvolutionaryAlgorithm algorithm = new EvolutionaryAlgorithm();
        }
        catch (IOException | CsvValidationException e)
        {
            e.printStackTrace();
        }
    }

    public static double getContent(int i, String Name)
    {
       return ITEM_CONTENTS.get(i).getContent(Name);
    }

    private static void readIn() throws IOException, CsvValidationException {

        File file = new File(FILE_NAME);
        FileReader fr = new FileReader(file);
        BufferedReader reader = new BufferedReader(fr);

        ITEM_CONTENTS = new ArrayList<>();
        reader.readLine();
        String line = reader.readLine();
        String[] data;
        double[] contents;
        while (line != null)
        {
            data = line.split(",");
            System.out.println(line);
            contents = new double[10];
            for (int i = 1; i < data.length; i++)
            {
                contents[i-1] = Double.parseDouble(data[i]);
            }

            ItemContents newItem = new ItemContents(contents, data[0]);

            ITEM_CONTENTS.add(newItem);
            line = reader.readLine();
        }
    }
}
