import java.util.ArrayList;

public class Transition extends  Node{

    public Transition(ArrayList<Location> from, ArrayList<Location> to){
        this.departure = new ArrayList<>(from);
        this.destination = new ArrayList<>(to);
    }
    public Transition() {
        //cost_enter = cost_exit = 0;
        departure = destination = new ArrayList<>(); //might not work as intended, in case just separate the two declarations
    }

    ArrayList<Location> departure; //the list of Locations that connect TO the Transition
    ArrayList<Location> destination; //the list of Locations that the Transition fires TO

    public boolean checkValidity(){
        return departure.size() > 0 && destination.size() > 0;
    }

    public void addLinks(ArrayList<Location> to, ArrayList<Location> from){
        this.departure = new ArrayList<>(from);
        this.destination = new ArrayList<>(to);
    }

    public void addFrom(Location l){
        departure.add(l);
    }

    public void addTo(Location l){
        destination.add(l);
    }
}
