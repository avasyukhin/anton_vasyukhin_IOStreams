package calculation_layer;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by Aphex on 28.05.2016.
 */
public class Album {
    private String name;
    private String genre;
    private List<Track> tracks;


    public Album(String name, String genre, List<Track> tracks) {
        this.name = name;
        this.genre = genre;
        try {
            setNoEmptyTracks(tracks);
        }catch (NoSuchElementException e){
            e.printStackTrace();
        }

    }

    private final void setNoEmptyTracks(List<Track> tracks) {
        if (!tracks.isEmpty()) {
            this.tracks = tracks;
        }else throw new NoSuchElementException("Album must have at least one track");
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

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        try {
            setNoEmptyTracks(tracks);
        }catch (NoSuchElementException e){
            e.printStackTrace();
        }
    }
}
