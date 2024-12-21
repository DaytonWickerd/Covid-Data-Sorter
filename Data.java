package Programs.Program1;

public class Data implements Comparable<Data> {
    private String continent;
    private String country;
    private String date;
    private long total_cases;
    private long new_cases;
    private long population;

    public Data(String[] LineData) {
        continent = new String(LineData[0]);
        country = new String(LineData[1]);
        date = new String(LineData[2]);
        total_cases = Long.parseLong(LineData[3]);
        new_cases = Long.parseLong(LineData[4]);
        population = Long.parseLong(LineData[5]);
    }

    public Data() {
        country = new String("None");
    }

    public int compareTo(Data n) {
        if (this.new_cases < n.new_cases)
            return -1;
        if (this.new_cases > n.new_cases)
            return 1;
        return 0;
    }

    public String getContinent() {
        return continent;
    }

    public String getCountry() {
        return country;
    }

    public String getDate() {
        return date;
    }

    public long getTotalCases() {
        return total_cases;
    }

    public long getNewCases() {
        return new_cases;
    }

    public long getPopulation() {
        return population;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTotal_Cases(int total_cases) {
        this.total_cases = total_cases;
    }

    public void setNewCases(int new_cases) {
        this.new_cases = new_cases;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
}
