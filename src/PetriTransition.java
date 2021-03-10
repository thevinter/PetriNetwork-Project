import java.util.ArrayList;

public class PetriTransition extends Transition{
    int cost_enter; //the number of required tokens to activate the transition
    int cost_exit; //the number of tokens that the transition fires

    public PetriTransition(ArrayList<Location> from, ArrayList<Location> to){
        super(from, to);
    }

    public PetriTransition(int cost_enter, int cost_exit, ArrayList<Location> from, ArrayList<Location> to){
        this.cost_enter = cost_enter;
        this.cost_exit = cost_exit;
        this.departure = new ArrayList<>(from);
        this.destination = new ArrayList<>(to);
    }

    public PetriTransition(int cost_enter, int cost_exit){
        this.cost_enter = cost_enter;
        this.cost_exit = cost_exit;
    }

    public PetriTransition() {
        cost_enter = cost_exit = 0;
        departure = destination = new ArrayList<>(); //might not work as intended, in case just separate the two declarations
    }

    public boolean checkValidity(){
        return super.checkValidity() && cost_enter >= 0 && cost_exit >=0; //TODO: Verify the conditions
    }
}
