import java.util.ArrayList;

public class Location extends  Node{

    public void addFrom(Transition t){
       super.addOrigin(t);
    }

    public void addTo(Transition t){
        super.addDestination(t);
    }
}
