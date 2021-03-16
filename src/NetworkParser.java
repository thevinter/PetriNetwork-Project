import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class NetworkParser {

    private final String DEFAULTDOCUMENT = "default";

    public NetworkParser(){

    }
    //TODO v5 constructor for change document to read

    /**
     * Read a json file with our net structure to import them into an arraylist
     * @return list of nets on json file
     * @throws IOException
     */

    public ArrayList<Network> getNets() throws IOException {

        InputStream input = new FileInputStream(DEFAULTDOCUMENT+".json");
        NetSpace netSpace;
        ObjectMapper mapper = new ObjectMapper();

        SimpleModule module =
                new SimpleModule("NetDeserializer", new Version(3, 1, 8, null, null, null));

        module.addDeserializer(NetSpace.class, new NetworkDeserializer(NetSpace.class));

        mapper.registerModule(module);

        netSpace = mapper.readValue(input,NetSpace.class);

        return netSpace.getNetworks();

    }

    /**
     * Utility class for parsing the document
     */
    public static class NetSpace{

        private ArrayList<Network> networks;

        public NetSpace(){
            this.networks = new ArrayList<>();
        }

        public NetSpace(ArrayList<Network> nets){
            this.networks = nets;
        }

        public void addNet(Network n) {
            networks.add(n);
        }

        public ArrayList<Network> getNetworks(){
            return networks;
        }
    }


}
