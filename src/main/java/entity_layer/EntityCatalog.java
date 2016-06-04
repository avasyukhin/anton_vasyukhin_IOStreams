package entity_layer;



import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.*;
import java.util.List;


/**
 * Created by Aphex on 28.05.2016.
 */
@XmlRootElement( name="catalog" )
@XmlAccessorType(XmlAccessType.FIELD)
public class EntityCatalog implements Serializable {
    @XmlElement (name = "performer")
    private List<EntityPerformer> entityPerformers;

    public EntityCatalog() {
    }

    public EntityCatalog(List<EntityPerformer> EntityPerformers) {
        this.entityPerformers = EntityPerformers;
    }


    public List<EntityPerformer> getEntityPerformers() {
        return entityPerformers;
    }

    public void setEntityPerformers(List<EntityPerformer> entityPerformers) {
        this.entityPerformers = entityPerformers;
    }

}
