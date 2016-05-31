package serialization;

import java.io.IOException;
import java.io.Serializable;

/**
 * Created by Aphex on 31.05.2016.
 */
public interface Serializer<E>{
    void serialize(E entity,String filepath) throws IOException;
    E deserialize(String filepath) throws IOException;
}
