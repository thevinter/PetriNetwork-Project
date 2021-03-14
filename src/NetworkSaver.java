import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class NetworkSaver {

    private String activeDocument = "files/default";
    private ArrayList<Network> networks;
    private Network activeNet;
    private ArrayList<Location> locations;
    private ArrayList<Transition> transitions;


    public void activeNetwork(Network n) {
        this.activeNet = n;
        this.locations = n.getLocations();
        this.transitions = n.getTransitions();

    }
    public void createDocument() {

        try {
            JsonGenerator jsonGenerator = new JsonFactory().createGenerator(new FileOutputStream(activeDocument+".json"));

            jsonGenerator.setPrettyPrinter(new DefaultPrettyPrinter());

            jsonGenerator.writeStartObject(); //start Net Space
            jsonGenerator.writeArrayFieldStart("Networks");
            for(Network n: networks) { //start Nets array
                jsonGenerator.writeObjectFieldStart(n.getName()); //start Net

                jsonGenerator.writeArrayFieldStart("locations"); //start location array
                for(Location l: locations) {
                    jsonGenerator.writeObjectFieldStart(String.valueOf(l.getId()));//start location
                    //TODO v2 tokens field
                    jsonGenerator.writeEndObject();//end location
                }
                jsonGenerator.writeEndArray();//end location array

                jsonGenerator.writeArrayFieldStart("transitions"); //start transitions array
                for(Transition t: transitions) {
                    jsonGenerator.writeObjectFieldStart(String.valueOf(t.getId()));//start transition
                    //TODO v4 priority field
                    jsonGenerator.writeArrayFieldStart("in");//start ingoing nodes
                    for(Link l: t.links) {
                        if(l.getDestination().getId() == t.getId()) {
                            jsonGenerator.writeNumberField("node",l.getFrom().getId());
                            //TODO v2 weight field
                        }
                    }
                    jsonGenerator.writeEndArray();//end ingoing nodes

                    jsonGenerator.writeArrayFieldStart("out");//start outgoing nodes
                    for(Link l: t.links) {
                        if(l.getFrom().getId() == t.getId()) {
                            jsonGenerator.writeNumberField("node",l.getDestination().getId());
                            //TODO v2 weight field
                        }
                    }
                    jsonGenerator.writeEndArray();//end outgoing nodes

                    jsonGenerator.writeEndObject();//end transition
                }
                jsonGenerator.writeEndArray();//end transition array

                jsonGenerator.writeEndObject();// end Net

            } //end Nets array
            jsonGenerator.writeEndObject(); //end Net Space

            jsonGenerator.flush();
            jsonGenerator.close();

        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
