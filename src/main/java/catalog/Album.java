package catalog;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.List;

/**
 * Created by Aphex on 28.05.2016.
 */
public class Album implements Externalizable {
    String name;
    String genre;
    List<Track> tracks;

    public Album(String name, String genre, List<Track> tracks) {
        this.name = name;
        this.genre = genre;
        this.tracks = tracks;
    }

    public void writeExternal(ObjectOutput out) {
        String xml = "<album name=\"" + name +
                "\" genre=\"" + genre + "\">\n";
        try {
            out.writeUTF(xml);
            for (Track track : tracks) {
                track.writeExternal(out);
            }
            out.writeUTF("</album>\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readExternal(ObjectInput in) {

    }
}
