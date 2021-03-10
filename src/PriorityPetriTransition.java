import java.util.ArrayList;

public class PriorityPetriTransition extends PetriTransition{
    int priority;

    public PriorityPetriTransition(int priority, int cost_enter, int cost_exit, ArrayList<Location> from, ArrayList<Location> to){
        super(cost_enter, cost_exit, from, to);
        this.priority = priority;
    }

    public boolean checkValidity(){
        return super.checkValidity() && priority >= 0; //TODO: Check if it's correct
    }
}
