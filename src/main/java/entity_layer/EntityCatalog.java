package entity_layer;



import java.io.*;
import java.util.List;


/**
 * Created by Aphex on 28.05.2016.
 */
public class EntityCatalog implements Serializable {
    private List<EntityPerformer> EntityPerformers;

    public EntityCatalog() {
    }

    public EntityCatalog(List<EntityPerformer> EntityPerformers) {
        this.EntityPerformers = EntityPerformers;
    }


    public List<EntityPerformer> getEntityPerformers() {
        return EntityPerformers;
    }


}
