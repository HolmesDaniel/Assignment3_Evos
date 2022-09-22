public class ItemContents
{
    private final double[] contents;
    private final String name;

    public ItemContents(double[] contents, String name)
    {
        this.name = name;
        this.contents = contents;
    }

    public String getName() {
        return name;
    }

    public double getContent(String Name)
    {
        switch (Name) {
            case Main.WEIGHT: return contents[0];
            case Main.CALORIES: return contents[1];
            case Main.PROTEIN: return contents[2];
            case Main.CALCIUM: return contents[3];
            case Main.IRON: return contents[4];
            case Main.VITAMIN_A: return contents[5];
            case Main.THIAMINE: return contents[6];
            case Main.RIBOFLAVIN: return contents[7];
            case Main.NIACIN: return contents[8];
            case Main.ABSORBIC_ACID: return contents[9];
            default: return -1;
        }

    }

    public String printContent()
    {
        String line = "";
        for (int i = 0; i < contents.length-1; i++) {
            line = line + contents[i] + ", ";
        }
        line = line + contents[contents.length-1];
        return line;
    }
}
