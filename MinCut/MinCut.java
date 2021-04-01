import java.io.*;
import java.util.*;

public class MinCut{

    public static UndirectedGraph constructUndirectedGraph (String filename) throws Exception{

        UndirectedGraph graph = null;

        Scanner sc = null;

        try {
            sc = new Scanner(new BufferedReader(new FileReader(filename)));
            graph = new UndirectedGraph();

            //Number of vertices
            int nVtx = 0;
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            while (reader.readLine() != null) nVtx++;
            reader.close();

            //Add vertices
            for (int vtxID = 1; vtxID <= nVtx; ++vtxID){
                graph.addVtx(vtxID);
            }
            
            //Add edges
            while (sc.hasNextLine()){
                for (int i = 1; i <= nVtx; i++){
                    String[] line = sc.nextLine().trim().split(" ");
                    for (int j = 1; j < line.length; j++){
                        // System.out.println("i = " + i + " Integer.parseInt = " + Integer.parseInt(line[j]));
                        graph.addEdge(i,Integer.parseInt(line[j]));
                    }
                } 
            }

    
        } catch (FileNotFoundException ex){
            System.out.println("Cannot find the file");
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
        return graph;
    }

    public static void main (String[] args) throws Exception{
        //Calculate number of trials
        Scanner sc = null;
        int nTrial = 0, minimumCut = 0;
        try {
            sc = new Scanner (new BufferedReader(new FileReader("MinCut.txt")));

            //Number of vertices
            int nVtx = 0; 
            BufferedReader reader = new BufferedReader(new FileReader("MinCut.txt"));
            while (reader.readLine() != null) nVtx++;
            reader.close();

            nTrial = (int) Math.ceil(nVtx * nVtx * Math.log(nVtx));

            int nEdge = 0;
            while (sc.hasNext()){
                sc.nextLine();
                ++nEdge;
            }
            minimumCut = nEdge;
        } catch (FileNotFoundException ex) {
            System.out.println("Cannot find the file");
        } finally {
            if (sc != null){
                sc.close();
            }
        }

        System.out.println("Number of edges = " + minimumCut);

        for (int i = 0; i < nTrial; i++){
            //Construct the graph
            UndirectedGraph graph = constructUndirectedGraph("MinCut.txt");

            //Compute mincut
            int currMinimumCut = graph.computeMinCut();
            if (currMinimumCut < minimumCut){
                minimumCut = currMinimumCut;
            }

            System.out.println("End of trial " + i);
            System.out.println("Current minimum Cut: " + minimumCut);
        }

        System.out.println("Minimum cut: " + minimumCut);
    }

}