import java.util.ArrayList;

public class Network {
    ArrayList<Node> nodes;

    public boolean checkValidity(){
        //TODO: Checks Validity of the network
        return true;
    }

    /**
     * This allows to add a Location the network if the user already has a Location object and a list of incoming and outgoing Transitions
     * @param l The Location object
     * @param from The incoming Transition nodes
     * @param to The outgoing Transition nodes
     */
    public void addLocation(Location l, ArrayList<Transition> from, ArrayList<Transition> to){
        l.addLinks(from, to);
        nodes.add(l);
    }

    /**
     * This allows to add a Location to the Network without having the Transition and using integer IDs for the connections
     * @param from The list of incoming Transition IDs
     * @param to The list of outgoing Transition IDs
     */
    public void addLocation(ArrayList<Integer> from, ArrayList<Integer> to){
        Location l = new Location();
        for(Integer i : from){
            Node n = findNode(i);
            if(n == null){
                Transition t = createTransition(); //Ths might be confusing, if the id doesnt exist we create one.
                t.addTo(l); //We set the outgoing link from the Transition to the Location (since this id was supposed to be an incoming Transition)
                l.addFrom(t); //We add an incoming link from the Location to the Transition (since this id was supposed to be an incoming Transition)
                                //https://i.imgur.com/qX7fM0y.png for explanation
            }
            else if(n instanceof Location){
                System.out.println("A location can't be connected to a location");
                //TODO: Throw an error
            }
            else{
                l.addFrom((Transition) n);
            }
        }
        for(Integer i : to){
            Node n = findNode(i);
            if(n == null){
                Transition t = createTransition();
                t.addFrom(l);
                l.addTo(t);
            }
            else if(n instanceof Location){
                System.out.println("A location can't be connected to a location");
                //TODO: Throw an error
            }
            else{
                l.addTo((Transition) n);
            }
        }
    }

    /**
     * This adds a transition to the Network if we have everything we need
     * @param t The transition we want to add
     * @param from The incoming Locations
     * @param to The outgoing locations
     */
    public void addTransition(Transition t, ArrayList<Location> from, ArrayList<Location> to){
        t.addLinks(from, to);
        nodes.add(t);
    }

    /**
     * This allows to add a Transition to the Network without having the Transition and using integer IDs for the connections
     * @param from The list of IDs of the incoming nodes
     * @param to  The list of IDs of the outgoing nodes
     */
    public void addTransition(ArrayList<Integer> from, ArrayList<Integer> to){
        Transition t = new Transition();
        for(Integer i : from){
            Node n = findNode(i);
            if(n == null){
                Location l = createLocation();
                l.addTo(t);
                t.addFrom(l);
            }
            else if(n instanceof Transition){
                System.out.println("A transition can't be connected to a transition");
                //TODO: Throw an error
            }
            else{
                t.addFrom((Location) n);
            }
        }
        for(Integer i : to){
            Node n = findNode(i);
            if(n == null){
                Location l = createLocation();
                l.addFrom(t);
                t.addTo(l);
            }
            else if(n instanceof Transition){
                System.out.println("A transition can't be connected to a transition");
                //TODO: Throw an error
            }
            else{
                t.addTo((Location) n);
            }
        }
    }

    /**
     * This creates a new empty transition if the user wants to
     * @return A new transition
     */
    public Transition createTransition(){
        System.out.println("The following transition doesn't exist. Do you want to create one?");
        //TODO: Implement scanner and choice
        return new Transition();
    }

    /**
     * This creates a new empty Location if the user wants to
     * @return A new location
     */
    public Location createLocation(){
        System.out.println("The following location doesn't exist. Do you want to create one?");
        //TODO: Implement scanner and choice
        return new Location();
    }

    /**
     * Finds a node in the Network given an id
     * @param id The id of the node we want to search
     * @return The node requested, null if the node is not present
     */
    public Node findNode(int id){
        for(Node n: nodes){
            if(n.getId() == id) return n;
        }
        return null;
    }
}
