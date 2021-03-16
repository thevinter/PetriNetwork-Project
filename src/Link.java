

public class Link {
    private Node to;
    private Node from;
    private int strength;

    public Link(Node to, Node from){
        this.to = to;
        this.from = from;
        this.strength = 0;
    }

    public Link(Node to, Node from, int strength){
        this.to = to;
        this.from = from;
        this.strength = strength;
    }
    
    public Node getDestination(){
    	return to;
    }
    public Node getFrom() {return from;}

    public int getStrength() { return strength; }

    public void setStrength(int s) {
        this.strength = s;
    }
//    public void setStrength(){
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Select the weight of the link");
//        int i = sc.nextInt();
//        while(i < 0){
//            i = sc.nextInt();
//            System.out.println("Please enter a positive value");
//        }
//        this.strength = i;
//    }
    
}
