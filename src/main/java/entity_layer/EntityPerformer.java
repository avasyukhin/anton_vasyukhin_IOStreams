package entity_layer;

import calculation_layer.Performer;

import javax.xml.bind.annotation.*;
import java.io.*;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by Aphex on 28.05.2016.
 */
@XmlRootElement(name = "performer")
@XmlAccessorType (XmlAccessType.FIELD)
public class EntityPerformer implements Serializable {
    @XmlAttribute(name = "performer_name")
    private String name;
    @XmlElement(name = "album")
    private List<EntityAlbum> entityAlbums;

    public EntityPerformer() {
    }

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
            this.entityAlbums = EntityAlbums;
        } else throw new NoSuchElementException("EntityPerformer must have at least one album");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EntityAlbum> getEntityAlbums() {
        return entityAlbums;
    }

    public void setEntityAlbums(List<EntityAlbum> entityAlbums) {
        this.entityAlbums =entityAlbums;
//        try {
//            setNoEmptyAlbums(entityAlbums);
//        } catch (NoSuchElementException e) {
//            e.printStackTrace();
//        }
    }
    public boolean equals(Object other){
        boolean result = false;
        if (other instanceof EntityPerformer) {
            EntityPerformer performer = (EntityPerformer) other;
            result=((name.equals(performer.getName()))
                    &&(entityAlbums.containsAll(performer.entityAlbums))
                    &&(performer.entityAlbums.containsAll(entityAlbums)));
        }
        return result;
    }

}
