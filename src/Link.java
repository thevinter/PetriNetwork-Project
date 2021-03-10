public class Link {
    private Node to;
    private Node from;
    private int weight;

    public Link(Node to, Node from){
        this.to = to;
        this.from = from;
        this.weight = 0;
    }

    public Link(Node to, Node from, int weight){
        this.to = to;
        this.from = from;
        this.weight = weight;
    }
}
