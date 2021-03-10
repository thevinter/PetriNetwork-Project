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

    public void addTransition(Transition t, ArrayList<Location> from, ArrayList<Location> to){
        t.addLinks(from, to);
        nodes.add(t);
    }

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

    public Transition createTransition(){
        System.out.println("The following transition doesn't exist. Do you want to create one?");
        //TODO: Implement scanner and choice
        return new Transition();
    }

    public Location createLocation(){
        System.out.println("The following location doesn't exist. Do you want to create one?");
        //TODO: Implement scanner and choice
        return new Location();
    }

    public Node findNode(int id){
        for(Node n: nodes){
            if(n.getId() == id) return n;
        }
        return null;
    }
}
