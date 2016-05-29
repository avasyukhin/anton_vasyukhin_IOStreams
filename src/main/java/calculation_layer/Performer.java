package calculation_layer;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.List;

/**
 * Created by Aphex on 28.05.2016.
 */
public class Performer {
    String name;
    List<Album> albums;

    public Performer(String name, List<Album> albums) {
        this.name = name;
        this.albums = albums;
    }


}
