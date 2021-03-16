import java.util.ArrayList;

public class Network {
    String name;
    ArrayList<Node> nodes;
    ArrayList<Integer> checkedNodes = new ArrayList<>();

    public Network(String name){
        this.name = name;
        nodes = new ArrayList<>();
    }

    public String getName(){
        return name;
    }

    public ArrayList<Node> getNodes(){
        return nodes;
    }

    public ArrayList<Transition> getTransitions(){
        ArrayList<Transition> temp = new ArrayList<>();
        for(Node n : nodes){
            if(n instanceof Transition) temp.add((Transition) n);
        }
        return temp;
    }

    public ArrayList<Location> getLocations(){
        ArrayList<Location> temp = new ArrayList<>();
        for(Node n : nodes){
            if(n instanceof Location) temp.add((Location) n);
        }
        return temp;
    }


    /**
     * 
     * @return
     */
    public boolean checkValidity(){
        checkedNodes.add(nodes.get(0).getId());
        DFS(nodes.get(0));
    	for(Node n : nodes) {
    		if(!checkedNodes.contains(n.getId()))return false;
    	}
        return true;
    }
    /**
     * Depth First Search algorithm Implementation
     * @param n0
     */
    public void DFS(Node n0){
    	for(Node n : n0.getDestinations()){
    		if(!checkedNodes.contains(n.getId()))DFS(n);
    		checkedNodes.add(n.getId());
    	}	
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
                System.out.println("The selected id does not exist");
                //TODO: Throw an error
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
                System.out.println("The selected id does not exist");
                //TODO: Throw an error
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
     * This adds a Node to the Network if we have everything we need
     * @param n The Transition or the Location we want to add
     */
    public void addNode(Node n){
        nodes.add(n);
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
                System.out.println("The selected id does not exist");
                //TODO: Throw an error
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
                System.out.println("The selected id does not exist");
                //TODO: Throw an error
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
        System.out.println("Do you want to create a Transition?");
        //TODO: Implement scanner and choice
        return new Transition();
    }

    /**
     * This creates a new empty Location if the user wants to
     * @return A new location
     */
    public Location createLocation(){
        System.out.println("Do you want to create a Transition?");
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

    public void setName(String name) {this.name = name;}
}
