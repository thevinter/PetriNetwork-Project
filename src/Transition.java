import java.util.ArrayList;

public class Transition extends  Node{

    public Transition(ArrayList<Link> links) {
        super(links);
    }

    public Transition() {
        super();
    }

    public void addFrom(Location loc){
        Link l = new Link(this, loc);
        links.add(l);
        loc.links.add(l);
    }

    public void addTo(Location loc){
        Link l = new Link(loc, this);
        links.add(l);
        loc.links.add(l);
    }
}
