package dao;

import entity_layer.EntityPerformer;

import java.util.List;

/**
 * Created by Aphex on 03.06.2016.
 */
public interface AbstractDAO {
    EntityPerformer get(String name)throws NoSuchFieldException;
    List<EntityPerformer> getAll();
    void add(EntityPerformer performer);
    void remove(EntityPerformer performer)throws NoSuchFieldException;
    int getTotalLength(String name)throws NoSuchFieldException;
    void update();
}
