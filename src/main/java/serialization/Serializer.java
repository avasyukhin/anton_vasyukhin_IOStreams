package serialization;

import java.io.Serializable;

/**
 * Created by Aphex on 31.05.2016.
 */
public interface Serializer<E> extends Serializable{
    void setEntity(E entity);
    E getEntity();
    void serialize(String filepath);
    Serializer deserialize(String filepath);
}
