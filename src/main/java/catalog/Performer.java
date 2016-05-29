package catalog;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.List;

/**
 * Created by Aphex on 28.05.2016.
 */
public class Performer implements Externalizable {
    String name;
    List<Album> albums;

    public Performer(String name, List<Album> albums) {
        this.name = name;
        this.albums = albums;
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        String xml = "<performer name=\"" + name +
                "\">\n";
        try {
            out.writeUTF(xml);
            for (Album album : albums) {
                album.writeExternal(out);
            }
            out.writeUTF("</performer>\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {

    }
}
