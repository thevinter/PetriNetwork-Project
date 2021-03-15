import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NetworkSaver {



    private String activeDocument = "default";
    private List<Network> networks;
    private Network activeNet;
    private List<Location>  locations = new ArrayList<>();
    private List<Transition> transitions = new ArrayList<>();

    public NetworkSaver(ArrayList<Network> nets){
        networks = nets;
    }

    //TODO v5 Constructor for change name of activeDocument

    public void activeNet(Network n) {
        activeNet = n;
        locations = n.getLocations();
        transitions = n.getTransitions();

    }

    public  void createDocument() {

        try {
            JsonGenerator jsonGenerator = new JsonFactory().createGenerator(new FileOutputStream(activeDocument+".json"));

            jsonGenerator.setPrettyPrinter(new DefaultPrettyPrinter());

            jsonGenerator.writeStartObject(); //start Net Space
            jsonGenerator.writeArrayFieldStart("Networks");
            for(Network n: networks) { //start Nets array
                activeNet(n);
                jsonGenerator.writeStartObject(); //start Net
                jsonGenerator.writeStringField("name",n.getName()); //name net field

                jsonGenerator.writeArrayFieldStart("locations"); //start location array
                for(Location l: locations) {
                    jsonGenerator.writeStartObject();//start location
                    jsonGenerator.writeNumberField("id",l.getId());//id location field
                    //TODO v2 tokens field
                    jsonGenerator.writeEndObject();//end location
                }
                jsonGenerator.writeEndArray();//end location array

                jsonGenerator.writeArrayFieldStart("transitions"); //start transitions array
                for(Transition t: transitions) {
                    jsonGenerator.writeStartObject();//start transition
                    jsonGenerator.writeNumberField("id",t.getId()); //id transition field
                    //TODO v4 priority field
                    jsonGenerator.writeArrayFieldStart("in");//start ingoing nodes array
                    for(Link l: t.links) {
                        if(l.getDestination().getId() == t.getId()) {
                            jsonGenerator.writeStartObject();//start ingoing node
                            jsonGenerator.writeNumberField("node",l.getFrom().getId());//id ingoing node field
                            //TODO v2 weight field
                            jsonGenerator.writeEndObject();//end ingoing node
                        }
                    }
                    jsonGenerator.writeEndArray();//end ingoing nodes array

                    jsonGenerator.writeArrayFieldStart("out");//start outgoing nodes array
                    for(Link l: t.links) {
                        if(l.getFrom().getId() == t.getId()) {
                            jsonGenerator.writeStartObject();//start outgoing node
                            jsonGenerator.writeNumberField("node",l.getDestination().getId());//id outgoing node field
                            //TODO v2 weight field
                            jsonGenerator.writeEndObject();//end outgoing node array
                        }
                    }
                    jsonGenerator.writeEndArray();//end outgoing nodes

                    jsonGenerator.writeEndObject();//end transition
                }
                jsonGenerator.writeEndArray();//end transition array

                jsonGenerator.writeEndObject();// end Net

            }
            jsonGenerator.writeEndArray(); //end Nets array
            jsonGenerator.writeEndObject(); //end Net Space

            jsonGenerator.flush();
            jsonGenerator.close();

        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
