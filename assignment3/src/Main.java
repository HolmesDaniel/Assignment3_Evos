public class main {
    final string FILE_NAME = ""; 
    
    public static void main(String[] arg)
    {
        double[][] itemContents = readIn();
        EvolutionaryAlgorithm algorithm = new EvolutionaryAlgorithm(itemContents);
    }

    private double[][] readIn()
    {
        double[][] contents = new double[77][10];
        for(int i; i = 0; i < contents.length; i++)
            for(int j; j = 0; j < contents[i].length; j++)
            {
                contents[i][j] = 0;
            }
        
        return contents;
    }
}
