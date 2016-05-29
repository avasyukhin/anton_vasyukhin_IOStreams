package calculation_layer;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by Aphex on 28.05.2016.
 */
public class Performer {
    private String name;
    private List<Album> albums;

    public Performer(String name, List<Album> albums) {
        this.name = name;
        try {
            setNoEmptyAlbums(albums);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    private final void setNoEmptyAlbums(List<Album> albums) {
        if (!albums.isEmpty()) {
            this.albums = albums;
        } else throw new NoSuchElementException("Performer must have at least one album");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        try {
            setNoEmptyAlbums(albums);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }
    @Override
    public String toString(){
        String string=name+"\n";
        for (Album album: albums){
            string+=albums.toString()+'\n';
        }
        return string;
    }
}
