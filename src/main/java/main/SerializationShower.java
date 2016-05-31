package main;


import calculation_layer.Catalog;
import entity_layer.EntityCatalog;
import layer_converter.CalculationToEntity;
import layer_converter.EntityToCalculation;
import layer_converter.LayerConverter;
import serialization.Serializer;

import java.io.IOException;

/**
 * Created by Aphex on 31.05.2016.
 */
public class SerializationShower {
    public static Catalog showSerialization(Catalog catalog,Serializer serializer,String filepath) throws IOException {
        LayerConverter converter = new CalculationToEntity();
        EntityCatalog entity = (EntityCatalog) converter.convert(catalog);
        serializer.serialize(entity,filepath);
        entity = (EntityCatalog) serializer.deserialize(filepath);
        converter = new EntityToCalculation();
        return (Catalog) converter.convert(entity);
    }

}
