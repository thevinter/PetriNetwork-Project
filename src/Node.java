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

    public  void addLink(Node to, Node from){
        //TODO: Throw an exception if neither "To" or "From" are this node;
        Link l = new Link(to, from);
        links.add(l);
    }

    public int getId(){
        return id;
    }
}
