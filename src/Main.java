import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        NetworkParser parser = new NetworkParser();
        Menu m = new Menu(parser.getNets());
        m.startMenu();
    }
}
