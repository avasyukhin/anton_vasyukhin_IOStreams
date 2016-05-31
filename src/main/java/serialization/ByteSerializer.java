package serialization;

import entity_layer.EntityCatalog;

import java.io.*;

/**
 * Created by Aphex on 31.05.2016.
 */
public class ByteSerializer implements Serializer<EntityCatalog> {




    public void serialize(EntityCatalog entity,String filepath) throws IOException {
        ObjectOutputStream out =new ObjectOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(filepath)));
        out.writeObject(entity);
        out.flush();
        out.close();
    }
    public EntityCatalog deserialize(String filepath) throws IOException {
        ObjectInputStream in =new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream(filepath)));
        EntityCatalog entity=null;
        try {
            entity = (EntityCatalog)in.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            in.close();
            return entity;
        }
    }
}
