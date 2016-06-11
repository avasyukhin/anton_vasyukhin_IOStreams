package entity_layer;

import calculation_layer.Track;

import javax.xml.bind.annotation.*;
import java.io.*;

/**
 * Created by Aphex on 28.05.2016.
 */
@XmlRootElement(name = "track")
@XmlAccessorType(XmlAccessType.FIELD)
public class EntityTrack implements Serializable {
    @XmlAttribute(name = "track_name")
    private String name;
    @XmlAttribute(name = "length")
    private int length;

    public EntityTrack() {
    }

    public EntityTrack(String name, int length) {
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
    public boolean equals(Object other){
        boolean result = false;
        if (other instanceof EntityTrack) {
            EntityTrack track = (EntityTrack) other;
            result = ((length==track.getLength())&&(name.equals(track.getName())));
        }
        return result;
    }

}
