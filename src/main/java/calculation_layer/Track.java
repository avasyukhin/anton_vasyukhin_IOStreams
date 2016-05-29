package calculation_layer;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * Created by Aphex on 28.05.2016.
 */
public class Track{
    private String name;
    private int length;


    public Track(int length, String name) {
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

    @Override
    public String toString() {
        return "\t\t" + name +
                " :: " + length +
                "s\n";
    }
}
