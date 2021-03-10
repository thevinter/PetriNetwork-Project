import java.util.ArrayList;

public class PriorityPetriTransition extends Transition{
    int priority;

    public PriorityPetriTransition(int priority, ArrayList<Link> links){
        super(links);
        this.priority = priority;
    }

    public boolean checkValidity(){
        return super.checkValidity() && priority >= 0; //TODO: Check if it's correct
    }
}
