import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.ArrayList;

public class NetworkDeserializer extends StdDeserializer<NetworkParser.NetSpace>{

    private ArrayList<Network> networks;


    public NetworkDeserializer(Class<?> net){
        super(net);
    }


    @Override
    public NetworkParser.NetSpace deserialize(JsonParser parser, DeserializationContext deserializer) throws IOException {

        NetworkParser.NetSpace netSpace = new NetworkParser.NetSpace();
        Network net = new Network(null);

        while (!parser.isClosed()) {
            JsonToken token = parser.nextToken();


            if (JsonToken.FIELD_NAME.equals(token)) {
                String fieldName = parser.getCurrentName();
                //System.out.println(fieldName);
                token = parser.nextToken();

                //start new NET getting name
                if (fieldName.equals("name")) {
                    String netName = parser.getValueAsString();
                    net.setName(parser.getValueAsString());
                }
                //list of locations
                if (fieldName.equals("locations")) {
                    ArrayList<Location> locs = locationDeserializer(parser, deserializer);
                    for (Location l : locs) {
                        net.addNode(l);
                    }
                }
                //list of transitions
                if (fieldName.equals("transitions")) {
                    ArrayList<Transition> trans = transitionDeserializer(parser, deserializer, net);
                    for (Transition t : trans) {
                        net.addNode(t);

                    }
                    //if the pointer come here it means NET has its attributes so it can be added to the list
                    netSpace.addNet(net);
                    net = new Network(null);
                }

            }

        }
        return netSpace;
    }




    public ArrayList<Location> locationDeserializer(JsonParser parser, DeserializationContext deserializer) throws IOException {

        ArrayList<Location> locs = new ArrayList<>();


        while (!parser.isClosed()) {
            JsonToken token = parser.nextToken();


            if (JsonToken.FIELD_NAME.equals(token)) {
                String fieldName = parser.getCurrentName();
                //System.out.println(fieldName);
                token = parser.nextToken();

                if (fieldName.equals("id")) {
                    Location loc = new Location();
                    int id = parser.getValueAsInt();
                    loc.setId(id);
                    locs.add(loc);
                }

                //TODO token field v2

            }else if(JsonToken.END_ARRAY.equals(token))break;
        }



        return locs;
    }

    public ArrayList<Transition> transitionDeserializer(JsonParser parser, DeserializationContext deserializer,Network net) throws IOException, JsonProcessingException {

        ArrayList<Transition> trans = new ArrayList<>();
        Transition tran = new Transition();


            while (!parser.isClosed()) {
                JsonToken token = parser.nextToken();


                if (JsonToken.FIELD_NAME.equals(token)) {
                    String fieldName = parser.getCurrentName();
                    //System.out.println(fieldName);
                    token = parser.nextToken();

                    if (fieldName.equals("id")) {
                        //TODO priority field v4

                        int id = parser.getValueAsInt();
                        tran.setId(id);
                    }
                        //looking for ingoing nodes
                        if (fieldName.equals("in")) {
                            while (!parser.isClosed()) {
                                token = parser.nextToken();


                                if (JsonToken.FIELD_NAME.equals(token)) {
                                    fieldName = parser.getCurrentName();
                                    //System.out.println(fieldName);
                                    token = parser.nextToken();

                                    if (fieldName.equals("node")) {
                                        int id = parser.getValueAsInt();
                                        tran.addOrigin(net.findNode(id));
                                    }

                                }else if(JsonToken.END_ARRAY.equals(token))break;
                            }
                        }

                        //looking for outgoings nodes
                        if (fieldName.equals("out")) {
                            while (!parser.isClosed()) {
                                token = parser.nextToken();


                                if (JsonToken.FIELD_NAME.equals(token)) {
                                    fieldName = parser.getCurrentName();
                                    //System.out.println(fieldName);
                                    token = parser.nextToken();

                                    if (fieldName.equals("node")) {
                                        int id = parser.getValueAsInt();
                                        tran.addDestination(net.findNode(id));
                                    }
                                }else if(JsonToken.END_ARRAY.equals(token)) {
                                    trans.add(tran);
                                    tran = new Transition();
                                    token = parser.nextToken();
                                    break;}

                            }
                        }


                }else if(JsonToken.END_ARRAY.equals(token))break;
            }
        return trans;
    }

}
