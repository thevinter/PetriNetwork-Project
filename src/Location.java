import java.util.ArrayList;

public class Location extends  Node{

    public void addFrom(Transition t){
        Link l = new Link(this, t);
        links.add(l);
        t.links.add(l);
    }

    public void addTo(Transition t){
        Link l = new Link(t, this);
        links.add(l);
        t.links.add(l);
    }
}
