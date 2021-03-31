
class UndirectedEdge{

    private Vertex end1;
    private Vertex end2;

    //Constructor
    UndirectedEdge(Vertex end1, Vertex end2){
        this.end1 = end1;
        this.end2 = end2;
    }

    //Accessors
    Vertex end1(){
        return end1;
    }

    Vertex end2(){
        return end2;
    }

    //Mutators

    void setEnd1(Vertex end1){
        this.end1 = end1;
    }

    void setEnd2(Vertex end2){
        this.end2 = end2;
    }

    @Override
    public String toString(){
        return String.format("Edge conncting vertex #%d and Vertex #%d", end1.vtxID(), end2.vtxID());
    }

}