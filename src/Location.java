import java.util.ArrayList;

public class Location extends  Node{
    ArrayList<Transition> departure;
    ArrayList<Transition> destination;

    public Location(){
        departure = destination = new ArrayList<>(); //might not work as intended, in case just separate the two declarations
    }

    public Location(ArrayList<Transition> from, ArrayList<Transition> to){
        this.departure = new ArrayList<>(from);
        this.destination = new ArrayList<>(to);
    }

    public boolean checkValidity(){
        return departure.size() > 0 && destination.size() > 0;
    }

    public void addLinks(ArrayList<Transition> to, ArrayList<Transition> from){
        this.departure = new ArrayList<>(from);
        this.destination = new ArrayList<>(to);
    }

    public void addFrom(Transition t){
        departure.add(t);
    }

    public void addTo(Transition t){
        destination.add(t);
    }
}
