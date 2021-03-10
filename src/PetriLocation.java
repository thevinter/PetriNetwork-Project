import java.util.ArrayList;

public class PetriLocation extends Location{
    int n_tokens;

    public PetriLocation(ArrayList<Transition> from, ArrayList<Transition> to){
        super(from, to);
        n_tokens = 0;
    }

    public PetriLocation(ArrayList<Transition> from, ArrayList<Transition> to, int n_tokens){
        super(from, to);
        this.n_tokens = n_tokens;
    }

    public boolean checkValidity(){
        return super.checkValidity() && n_tokens>=0; //TODO: Verify if correct
    }
}
