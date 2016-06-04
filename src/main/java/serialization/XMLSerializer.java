package serialization;

import entity_layer.EntityCatalog;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Aphex on 03.06.2016.
 */
public class XMLSerializer implements Serializer<EntityCatalog>{
    public void serialize(EntityCatalog entity,String filepath) throws IOException {
        try {
            JAXBContext context = JAXBContext.newInstance(EntityCatalog.class);
            Marshaller m = context.createMarshaller();
            m.marshal(entity, new FileOutputStream(filepath));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
    public EntityCatalog deserialize(String filepath) throws IOException {
        EntityCatalog entity = null;
        try {
            File file= new File(filepath);
            JAXBContext context = JAXBContext.newInstance(EntityCatalog.class);
            Unmarshaller jaxbUnmarshaller= context.createUnmarshaller();
            entity = (EntityCatalog) jaxbUnmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return entity;
    }
}
