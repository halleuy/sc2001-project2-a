package Lab2a;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GenerateStatsCSV {

    // Utility function to generate random graphs with edges of weight between 1 and 100
    public static int[][] generateRandomGraph(int V) {
        Random rand = new Random();
        int[][] graph = new int[V][V];
        int edges = 0;
        int E = 3 * V;


        while (edges < E) {
            int u = rand.nextInt(V);
            int v = rand.nextInt(V);

            if (u != v && graph[u][v] == 0) {
                graph[u][v] = rand.nextInt(100) + 1;
                edges++;
            }
        }

        return graph;
    }

    public static void main(String[] args) {
        try {
            FileWriter csvWriter = new FileWriter("results.csv");
            csvWriter.append("vertices,edges,time(ms),comparisons");
            long[] comparisons = new long[1];

            for (int V = 100; V <= 200; V++) {
                int[][] graph = generateRandomGraph(V);
                long startTime = System.nanoTime();
                comparisons[0] = DijkstraAdjacencyMatrix.dijkstra(graph, 0);
                long endTime = System.nanoTime();

                double timeTaken = (endTime - startTime) / 1e6;
                int E = 3 * V;

                csvWriter.append(String.valueOf(V)).append(",").append(String.valueOf(E)).append(",").append(String.valueOf(timeTaken)).append(",").append(String.valueOf(comparisons[0])).append("\n");

                System.out.println("Completed test for " + V + " vertices, " + E + " edges.");
            }

            csvWriter.flush();
            csvWriter.close();
            System.out.println("Result written to results.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
