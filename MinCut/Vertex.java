import java.util.*;

public class Vertex{

    private final Map <Integer, Integer> freqOfNeighbors;
    private final List<UndirectedEdge> edges;
    private int vtxID;

    //Constructor
    Vertex(int vtxID){
        this.vtxID = vtxID;
        freqOfNeighbors = new HashMap<>();
        edges = new ArrayList<>();
    }

    //Accessor

    int vtxID(){
        return vtxID;
    }

    //Returns the first edge with the given neighbor
    UndirectedEdge getEdgeWithNeighbor(Vertex neighbor){
        //Check if input neighbor is null
        if (neighbor == null){
            throw new IllegalArgumentException("The input neighbor is null");
        }

        for (UndirectedEdge edge: edges){
            if ((edge.end1() == this && edge.end2() == neighbor) || (edge.end1() == neighbor && edge.end2() == this)){
                return edge;
            }
        }
        //Edge not found with given neighbor
        return null;
    }

    //Accessor for edge
    List<UndirectedEdge> edges(){
        return edges;
    }

    //Adding a new edge
    void addEdge(UndirectedEdge newEdge){
        //Check if newEdge is null
        if (newEdge == null){
            throw new IllegalArgumentException("Input edge is null");
        }

        //Check if newEdge involes this vertex
        if (newEdge.end1() != this && newEdge.end1() != this){
            throw new IllegalArgumentException("Input edge does not involve this vertex");
        }

        edges.add(newEdge);

        //Find neighbor associated with the input edge
        Vertex neighbor = null;
        if (newEdge.end1() == this){
            neighbor = newEdge.end2();
        } else {
            neighbor = newEdge.end1();
        }

        //Update the freq of the neighbor

        Integer freq = freqOfNeighbors.getOrDefault(neighbor.vtxID, 0);
        ++freq;
        freqOfNeighbors.put(neighbor.vtxID,freq);
    }

    //Remove an edge
    void removeEdge(UndirectedEdge edgeToRemove){
        //Check if edgeToRemove is null
        if(edgeToRemove == null){
            throw new IllegalArgumentException("Input edge is null");
        }
        //Check if edgeToRemove involves this vertex
        if (edgeToRemove.end1() != this && edgeToRemove.end2() != this){
            throw new IllegalArgumentException("Input edge does not involve this vertex");
        }

        edges.remove(edgeToRemove);

        //Find the neighbor associated with the input edge
        Vertex neighbor = null;
        if (edgeToRemove.end1() == this){
            neighbor = edgeToRemove.end2();
        } else {
            neighbor = edgeToRemove.end1();
        }

        //Update the freq of neighbor
        Integer freq = freqOfNeighbors.get(neighbor.vtxID);
        if (freq == 1){
            freqOfNeighbors.remove(neighbor.vtxID);
        } else {
            --freq;
            freqOfNeighbors.put(neighbor.vtxID, freq);
        }
    }

        @Override
        public String toString() {
            return String.format("Vertex #%d, Its neighbors and frequencies: %s", vtxID, freqOfNeighbors);
        }

}