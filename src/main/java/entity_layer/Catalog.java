package entity_layer;



import java.io.*;
import java.util.List;


/**
 * Created by Aphex on 28.05.2016.
 */
public class Catalog implements Serializable {
    private List<Performer> performers;

    public Catalog(List<Performer> performers) {
        this.performers = performers;
    }


    public List<Performer> getPerformers() {
        return performers;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        throw new NotSerializableException("You must use Serializator class");
    }

    private void readObject(ObjectInputStream in) throws IOException {
        throw new NotSerializableException("You must use Serializator class");
    }
}
