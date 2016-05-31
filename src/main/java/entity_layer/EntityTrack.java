package entity_layer;

import java.io.*;

/**
 * Created by Aphex on 28.05.2016.
 */
public class EntityTrack implements Serializable {
    private String name;
    private int length;


    public EntityTrack(int length, String name) {
        this.length = length;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }


}
