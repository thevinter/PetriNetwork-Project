import java.util.ArrayList;

public class Transition extends  Node{

    public Transition(ArrayList<Link> links) {
        super(links);
    }

    public Transition() {
        super();
    }

    public void addFrom(Location loc){
        super.addOrigin(loc);
    }

    public void addTo(Location loc){
        super.addDestination(loc);
    }
}
