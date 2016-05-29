package catalog;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.List;


/**
 * Created by Aphex on 28.05.2016.
 */
public class Catalog implements Externalizable {
    List<Performer> performers;

    public Catalog(List<Performer> performers) {
        this.performers = performers;
    }

    public void writeExternal(ObjectOutput out){
        String xml =" <?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"+
                "<catalog>\n";
        try {
            out.writeUTF(xml);
            for (Performer performer : performers) {
                performer.writeExternal(out);
            }
            out.writeUTF("</catalog>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readExternal(ObjectInput in) {

    }
}
