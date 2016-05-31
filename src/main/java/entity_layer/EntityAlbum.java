package entity_layer;

import java.io.*;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by Aphex on 28.05.2016.
 */
public class EntityAlbum implements Serializable {
    private String name;
    private String genre;
    private List<EntityTrack> EntityTracks;


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
            this.EntityTracks = EntityTracks;
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
        return EntityTracks;
    }

    public void setEntityTracks(List<EntityTrack> EntityTracks) {
        try {
            setNoEmptyTracks(EntityTracks);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }


}
