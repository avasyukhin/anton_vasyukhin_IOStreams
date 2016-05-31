package entity_layer;

import java.io.*;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by Aphex on 28.05.2016.
 */
public class EntityPerformer implements Serializable {
    private String name;
    private List<EntityAlbum> EntityAlbums;

    public EntityPerformer(String name, List<EntityAlbum> EntityAlbums) {
        this.name = name;
        try {
            setNoEmptyAlbums(EntityAlbums);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    private final void setNoEmptyAlbums(List<EntityAlbum> EntityAlbums) {
        if (!EntityAlbums.isEmpty()) {
            this.EntityAlbums = EntityAlbums;
        } else throw new NoSuchElementException("EntityPerformer must have at least one album");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EntityAlbum> getEntityAlbums() {
        return EntityAlbums;
    }

    public void setEntityAlbums(List<EntityAlbum> EntityAlbums) {
        try {
            setNoEmptyAlbums(EntityAlbums);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

}
