import java.util.ArrayList;

public class Node {
    public Node(){
        id = general_id++;
        links = new ArrayList<>();
    }

    public Node(ArrayList<Link> links){
        id = general_id++;
        this.links = new ArrayList<>(links);
    }

    public ArrayList<Link> links;
    private static int general_id = 0;
    private int id;

    public void addLink(Link l){
        links.add(l);
    }
    public void addLinks(ArrayList<Link> links){
        this.links.addAll(links);
    }

    public boolean checkValidity(){
        return links.size() > 0;
    }

    public void addOrigin(Node from,int s){
        Link l = new Link(this, from);
        l.setStrength(s);
        links.add(l);
    }

    public void addOrigin(Node from){
        Link l = new Link(this, from);
        links.add(l);
    }

    public void addDestination(Node to,int s){
        Link l = new Link(to, this);
        l.setStrength(s);
        links.add(l);
    }

    public void addDestination(Node to){
        Link l = new Link(to, this);
        links.add(l);
    }

    public int getId(){
        return id;
    }
    
    public ArrayList<Node> getDestinations(){
    	ArrayList<Node> n = new ArrayList<>();
    	for(Link l: links)n.add(l.getDestination());
    	return n;
    }

    public void printLinks(){
        for(Link l : links){
            System.out.println("Node no. " + this.id + " -- " +l.getStrength() + "-->" + l.getDestination());
        }
    }

    public void setId(int id){this.id = id;}
}
