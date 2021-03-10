import java.util.ArrayList;

public class PetriLocation extends Location{
    int n_tokens;

    public PetriLocation(ArrayList<Link> links, int n_tokens){
        super(links);
        this.n_tokens = n_tokens;
    }

    public boolean checkValidity(){
        return super.checkValidity() && n_tokens>=0; //TODO: Verify if correct
    }
}
