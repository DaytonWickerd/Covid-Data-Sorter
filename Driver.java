package Programs.Program1;

import java.io.*;

public class Driver {
    public static void main(String[] args) throws Exception {
        MinPQ<Data> pq = new MinPQ<>(4);
        BST<Long, Data> bst = new BST<>();
        String compare = new String("");

        boolean First = true;

        BufferedReader br = new BufferedReader(new FileReader("owid-covid-data.csv"));

        // skip first line
        String line = br.readLine();

        // now read the file
        while ((line = br.readLine()) != null) {
            String[] lineData = line.split(",");
            Data d = new Data(lineData);

            // starts the program
            if (First) {
                pq.insert(d);
                compare = d.getCountry();
                First = false;
            }
            // checks country b/c the min pq deals with 1 country at a time
            else if (compare.equals(d.getCountry())) {
                pq.insert(d);

                if (pq.size() > 3) {
                    pq.delMin();
                }
            }

            else { // new country
                while (!pq.isEmpty()) {
                    Data min = pq.delMin();
                    bst.put(min.getNewCases(), min);
                }
                pq.insert(d);
                compare = d.getCountry();
            }
        }

        br.close();
        while (!pq.isEmpty()) {
            Data min = pq.delMin();
            bst.put(min.getNewCases(), min);
        }
        FileWriter writer = new FileWriter("outputp1.txt");
        for (Long key : bst.keys()) {
            Data data = bst.get(key);
            writer.write("New Cases: " + data.getNewCases() + " at " + data.getCountry() + "/"
                    + data.getContinent() + " on " + data.getDate() + " Total: " + data.getTotalCases() + " Pop: "
                    + data.getPopulation() + "\n");
        }
        writer.close();
    }
}
