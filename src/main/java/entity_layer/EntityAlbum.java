package entity_layer;

import javax.xml.bind.annotation.*;
import java.io.*;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by Aphex on 28.05.2016.
 */
@XmlRootElement(name = "album")
@XmlAccessorType (XmlAccessType.FIELD)
public class EntityAlbum implements Serializable {
    @XmlAttribute(name = "album_name")
    private String name;
    @XmlAttribute(name = "genre")
    private String genre;
    @XmlElement(name = "track")
    private List<EntityTrack> entityTracks;

    public EntityAlbum() {
    }

    public EntityAlbum(String name, String genre, List<EntityTrack> EntityTracks) {
        this.name = name;
        this.genre = genre;
        try {
            setNoEmptyTracks(EntityTracks);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }

    }

    private final void setNoEmptyTracks(List<EntityTrack> EntityTracks) {
        if (!EntityTracks.isEmpty()) {
            this.entityTracks = EntityTracks;
        } else throw new NoSuchElementException("EntityAlbum must have at least one track");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<EntityTrack> getEntityTracks() {
        return entityTracks;
    }

    public void setEntityTracks(List<EntityTrack> entityTracks) {
        this.entityTracks =entityTracks;
//        try {
//            setNoEmptyTracks(entityTracks);
//        } catch (NoSuchElementException e) {
//            e.printStackTrace();
//        }
    }
    @Override
    public boolean equals(Object other){
        boolean result = false;
        if (other instanceof EntityAlbum) {
            EntityAlbum album = (EntityAlbum) other;
            result=((genre.equals(album.getGenre()))&&(name.equals(album.getName()))
                    &&(entityTracks.containsAll(album.entityTracks))
                    &&(album.entityTracks.containsAll(entityTracks)));
        }
        return result;
    }


}
