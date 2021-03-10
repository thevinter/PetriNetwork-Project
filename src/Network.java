import java.util.ArrayList;

public class Network {
    ArrayList<Node> nodes;

    public boolean checkValidity(){
        //TODO: Checks Validity of the network
        return true;
    }

    public void addLocation(Location l, ArrayList<Transition> from, ArrayList<Transition> to){
        l.addLinks(from, to);
        nodes.add(l);
    }

    public void addLocation(ArrayList<Integer> from, ArrayList<Integer> to){
        //TODO: Add the possibility of add locations based on integer ids
        //Create a new Location, search all of the ids in the nodes, add them to the "from" list
        //Search the "to" transitions and if not present ask if the user wants to create one
    }

    public void addTransition(Transition t, ArrayList<Location> from, ArrayList<Location> to){
        t.addLinks(from, to);
        nodes.add(t);
    }

    public void addTransition(ArrayList<Integer> from, ArrayList<Integer> to){
        //TODO: Add the possibility of add transitions based on integer ids
        //Create a new Location, search all of the ids in the nodes, add them to the "from" list
        //Search the "to" transitions and if not present ask if the user wants to create one
    }
}
