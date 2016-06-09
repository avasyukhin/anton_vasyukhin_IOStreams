package dao;

import entity_layer.EntityAlbum;
import entity_layer.EntityCatalog;
import entity_layer.EntityPerformer;
import entity_layer.EntityTrack;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.util.List;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;


/**
 * Created by Aphex on 03.06.2016.
 */
public class XMLDAO implements EntityPerformerDAO {
    private String filepath;
    private EntityCatalog entity = null;
    private List<EntityPerformer> performers = null;
    private JAXBContext context = null;
    private static final Logger log = Logger.getLogger(XMLDAO.class);

    public XMLDAO(String filepath) {
        log.setLevel(Level.ALL);
        this.filepath = filepath;
        try {
            File file = new File(filepath);
            context = JAXBContext.newInstance(EntityCatalog.class);
            Unmarshaller jaxbUnmarshaller = context.createUnmarshaller();
            entity = (EntityCatalog) jaxbUnmarshaller.unmarshal(file);
            performers = entity.getEntityPerformers();
            log.info("DAO inititalized on file " + filepath);
        } catch (JAXBException e) {
            log.error("DAO init error",e);
        }
    }

    public EntityPerformer get(String name) throws NoSuchFieldException {
        for (EntityPerformer performer : performers) {
            if (performer.getName().equals(name)) {
                log.info("From DAO got performer " + name);
                return performer;
            }
        }
        throw new NoSuchFieldException("Not found performer with name " + name);
    }

    public List<EntityPerformer> getAll() {
        log.info("From DAO got all performer ");
        return performers;
    }

    public void add(EntityPerformer performer) {
        performers.add(performer);
        log.info("Added performer " + performer.getName());
        XMLupdate();
    }

    public void remove(EntityPerformer performer) {
        performers.remove(performer);
        log.info("Removed performer " + performer.getName());
        XMLupdate();
    }

    public void update(EntityPerformer performer) throws NoSuchFieldException {
        String name = performer.getName();
        for (EntityPerformer performerToUpdate : performers) {
            if (performer.getName().equals(name)) {
                performers.set(performers.indexOf(performerToUpdate),performer);
                log.info("Removed performer " + performer.getName());
                XMLupdate();
                return;
            }
        }
        throw new NoSuchFieldException("Not found performer with name " + name);
    }

    public int getTotalLength(String name) throws NoSuchFieldException {
        List<EntityAlbum> albums = get(name).getEntityAlbums();
        int length = 0;
        for (EntityAlbum album : albums) {
            length += album
                    .getEntityTracks()
                    .stream()
                    .mapToInt(EntityTrack::getLength)
                    .reduce((s1, s2) -> s1 + s2)
                    .getAsInt();
        }
        return length;
    }



    public void XMLupdate() {
        try {
            File file = new File(filepath);
            context = JAXBContext.newInstance(EntityCatalog.class);
            Unmarshaller jaxbUnmarshaller = context.createUnmarshaller();
            entity = (EntityCatalog) jaxbUnmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            log.error("DAO init error",e);
        }

        if (entity.getEntityPerformers().equals(performers)) {
            log.warn("Data is up to date");
        } else {
            entity.setEntityPerformers(performers);
            try {
                Marshaller m = context.createMarshaller();
                m.marshal(entity, new FileOutputStream(filepath));
                log.info("Data updated");
            } catch (JAXBException e) {
                log.error("XML update error", e);
            } catch (FileNotFoundException e) {
                log.error("XML access error", e);
            }
        }
    }
}
