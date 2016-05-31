package serialization;

import entity_layer.Catalog;

import java.io.Serializable;

/**
 * Created by Aphex on 31.05.2016.
 */
public interface Serializator <E> extends Serializable{
    void setEntity(E entity);
    E getEntity();
    void serialize(String filepath);
    Serializator deserialize(String filepath);
}
