public class Node {
    public Node(){
        id = general_id++;
    }

    private static int general_id = 0;
    private int id;

    public int getId(){
        return id;
    }
}
