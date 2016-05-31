package entity_layer;

import java.io.*;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by Aphex on 28.05.2016.
 */
public class Performer implements Serializable {
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
    private void writeObject(ObjectOutputStream out) throws IOException {
        throw new NotSerializableException("You must use Serializator class");
    }

    private void readObject(ObjectInputStream in) throws IOException {
        throw new NotSerializableException("You must use Serializator class");
    }
}
