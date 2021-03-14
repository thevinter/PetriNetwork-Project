import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    private static final String NETWORK_MENU_TEXT = "Make a choice:\n1) View available networks\n2) Create a network\n3) Choose a network\n4) Modify the selected" +
                                                    " network\n5)Print options\n\n0) Exit";
    private static final String NODES_MENU_TEXT = "Make a choice:\n1) Add a location\n2) Add a transition\n3) Print network\n4) Print options\n\n\n0) Back";
    private static final String WRONG_CHOICE_ERROR = "The number you've entered is not valid. Please choose again";
    private static final String EMPTY_NETWORKS = "There are no available networks. Create one first";
    private static final String NAME_NETWORK = "Select a name for the network";
    private static final String CHOOSE_NETWORK_ERROR = "You must select a network to modify first";
    private static final String CHOOSE_TRANSITION = "Choose a Transition you want this Location to originate from";
    private static final String CHOOSE_LOCATION = "Choose a Location you want this Transition to originate from";


    ArrayList<Network> networks;
    Network currentNetwork;
    Scanner sc;
    public Menu(){
        currentNetwork = null;
        networks=new ArrayList<>();
        sc = new Scanner(System.in);
    }

    void StartMenu() {
        boolean isRunning = true;
        int choice = -1;
        System.out.println(NETWORK_MENU_TEXT);
        while (isRunning){
            choice = sc.nextInt();
            switch (choice){
                case(1):
                    printNetworks();
                    break;
                case(2):
                    createNetwork();
                    break;
                case(3):
                    chooseNetwork();
                    break;
                case(4):
                    if(currentNetwork == null) System.out.println(CHOOSE_NETWORK_ERROR);
                    else networkMenu();
                    break;
                case(5):
                    System.out.println(NETWORK_MENU_TEXT);
                    break;
                default:
                    System.out.println(WRONG_CHOICE_ERROR);
                    break;
            }
        }
    }

    void networkMenu(){
        boolean isRunning = true;
        int choice = -1;
        System.out.println(NODES_MENU_TEXT);
        while(isRunning){
            choice = sc.nextInt();
            switch (choice){
                case(1):
                    addLocation();
                    break;
                case(2):
                    addTransition();
                    break;
                case(3):
                    printNetwork();
                    break;
                case(4):
                    System.out.println(NODES_MENU_TEXT);
                    break;
                default:
                    System.out.println(WRONG_CHOICE_ERROR);
                    break;
            }
        }
    }

    public void addLocation(){
        Location l = new Location();
        ArrayList<Transition> transitions = currentNetwork.getTransitions();
        int i = 0;
        System.out.println(CHOOSE_TRANSITION);
        for(Transition t : transitions){
            System.out.println(i+") Transition no. "+t.getId());
            i++;
        }
        System.out.println("0) Add standalone node");
        int c = sc.nextInt();
        while (c < 0 || c >= transitions.size()) {
            System.out.println(WRONG_CHOICE_ERROR);
            c = sc.nextInt();
        }
        if(c!=0) l.addFrom(transitions.get(c-1));
        currentNetwork.addNode(l);
    }

    public void addTransition(){
        Transition t = new Transition();
        ArrayList<Location> locations = currentNetwork.getLocations();
        int i = 0;
        System.out.println(CHOOSE_LOCATION);
        for(Location l : locations){
            System.out.println(i+") Location no. "+l.getId());
            i++;
        }
        System.out.println("0) Add standalone node");
        int c = sc.nextInt();
        while (c < 0 || c >= locations.size()) {
            System.out.println(WRONG_CHOICE_ERROR);
            c = sc.nextInt();
        }
        if(c!=0) t.addFrom(locations.get(c-1));
        currentNetwork.addNode(t);
    }

    public void printNetwork(){
        ArrayList<Node> nodes = currentNetwork.getNodes();
        for(Node n : nodes){
            n.printLinks();
        }
    }

    public void printNetworks(){
        int i = 0;
        for(Network n : networks){
            System.out.println(i+") " + n.getName());
            i++;
        }
        if(i==0) System.out.println(EMPTY_NETWORKS);
    }

    public void createNetwork(){
        String name;
        System.out.println(NAME_NETWORK);
        name = sc.nextLine();
        Network n = new Network(name);
        networks.add(n);
    }

    public void chooseNetwork(){
        if(networks.size() > 0) {
            printNetworks();
            int i = sc.nextInt();
            while (i < 0 || i >= networks.size()) {
                System.out.println(WRONG_CHOICE_ERROR);
                i = sc.nextInt();
            }
            currentNetwork = networks.get(0);
        }
        else{
            System.out.println(EMPTY_NETWORKS);
        }
    }
}
