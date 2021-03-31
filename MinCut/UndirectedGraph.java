import java.util.*;

public class UndirectedGraph {

    private final List<Vertex> vtxList;
    private final List<UndirectedEdge> edgeList;

    //Constructor
    public UndirectedGraph() {
        vtxList = new ArrayList<>();
        edgeList = new ArrayList<>();
    }

    //Find a vertex
    private Vertex findVtx(int vtxID){
        for (Vertex vtx: vtxList){
            if (vtx.vtxID() == vtxID){
                return vtx;
            }
        }
        return null;
    }

    //Adds a new vertex to vtxList without edges
    
    public void addVtx(int newVtxID){
        //Check if input vertex already exists
        if(findVtx(newVtxID) != null){
            throw new IllegalArgumentException("Input vertex already exists");
        }

        Vertex newVtx = new Vertex(newVtxID);
        vtxList.add(newVtx);
    }

    //Remove vertex and corresponding edges using remove edges function
    public void removeVtx(int vtxID){
        //Check if vertex exists
        Vertex vtxToRemove = findVtx(vtxID);
        if (vtxToRemove == null) {
            throw new IllegalArgumentException("Input vertex does not exist");
        }
        //Must remove all edges associated to the vertex
        List<UndirectedEdge> edgesToRemove = vtxToRemove.edges();
        while (edgesToRemove.size() > 0){
            removeEdge(edgesToRemove.get(0).end1().vtxID(),edgesToRemove.get(0).end2().vtxID());
        }

        //Finally remove the vertex itself
        vtxList.remove(vtxToRemove);
    }



    //Remove an Edge
    public void removeEdge(int end1ID, int end2ID){
        //Check that the input vertices exist
        Vertex end1 = findVtx(end1ID), end2 = findVtx(end2ID);
        if (end1 == null || end2 == null){
            throw new IllegalArgumentException("Input vertices do not exist");
        }
        //Check whether the edge to remove exists
        UndirectedEdge edgeToRemove = end1.getEdgeWithNeighbor(end2);
        if(edgeToRemove == null){
            throw new IllegalArgumentException("Edge does not exist");
        }
        //Remove both ends
        end1.removeEdge(edgeToRemove);
        end2.removeEdge(edgeToRemove);
        //Remove edge from list
        edgeList.remove(edgeToRemove);
    }


    //Add an Edge
    public void addEdge(int end1ID, int end2ID){
        //check if both end vertices exist
        Vertex end1 = findVtx(end1ID), end2 = findVtx(end2ID);
        if (end1 == null || end2 == null){
            throw new IllegalArgumentException("Endpoint vertices do not exist");
        }
        //Check if vertices are the same (self loop)
        if (end1ID == end2ID){
            throw new IllegalArgumentException("Endpoint vertices are the same");
        }
        UndirectedEdge newEdge = new UndirectedEdge(end1, end2);
        end1.addEdge(newEdge);
        end2.addEdge(newEdge);
        edgeList.add(newEdge);
    }

    //Remove all edges between a vertex pair from the graph
    public void removeEdgesBetweenPair(int end1ID, int end2ID){
        try {
            while(true){
                removeEdge(end1ID,end2ID);
            }
        } catch (IllegalArgumentException ex){}
    }

    public void showGraph() {
        System.out.println("The vertices are: ");
        for (Vertex vtx : vtxList){
            System.out.println(vtx);
        }
        System.out.println("It's edges are: ");
        for (UndirectedEdge edge: edgeList){
            System.out.println(edge);
        }
    }

    //Method to get next available vertex ID which is 1 greater than the current largest vertex
    private int getNextVtxID() {
        ArrayList<Integer> vtxIDs = new ArrayList<>();
        for (Vertex vtx : vtxList){
            vtxIDs.add(vtx.vtxID());
        }
        return Collections.max(vtxIDs) + 1;
    }

    //Method reconnects edges associated with given endpoint to given merged vertex and remove the endpoint
    private void reconstructEdges(Vertex end, Vertex mergedVtx){
        for (UndirectedEdge edgeFromEnd: end.edges()){
            //Find the neighbor
            Vertex neighbor = null;
            if (edgeFromEnd.end1() == end){
                neighbor = edgeFromEnd.end2();
                neighbor.removeEdge(edgeFromEnd);
                edgeFromEnd.setEnd1(mergedVtx);
            } else {
                neighbor = edgeFromEnd.end1();
                neighbor.removeEdge(edgeFromEnd);
                edgeFromEnd.setEnd2(mergedVtx);
            }
            //Add the new edge to both the neighbor and the merged vertex
            neighbor.addEdge(edgeFromEnd);
            mergedVtx.addEdge(edgeFromEnd);
        }
        //Remove the endpoint
        vtxList.remove(end);
    }


    //Compute the minimum cut of the graph with fewest number of crossing edges
    public int computeMinCut(){
        //Base case
        if (vtxList.size() <= 1) {
            return 0;
        }

        Random random = new Random();

        while (vtxList.size() > 2){
            //1. Pick a random edge
            int randomIDx = random.nextInt(edgeList.size());
            UndirectedEdge edgeToContract = edgeList.get(randomIDx);
            Vertex end1 = edgeToContract.end1(), end2 = edgeToContract.end2();

            //2. Contract two endpoints to a single vertex
            // First remove all edges between pair
            removeEdgesBetweenPair(end1.vtxID(), end2.vtxID());
            //Create a merged vertex
            int mergedVtxID = getNextVtxID();
            addVtx(mergedVtxID);
            //Reconstruct edges associated with with 2 endpoints to merged vertex and remove two endpoints
            Vertex mergedVtx = findVtx(mergedVtxID);
            reconstructEdges(end1, mergedVtx);
            reconstructEdges(end2, mergedVtx);
        }
        return edgeList.size();
    }

}