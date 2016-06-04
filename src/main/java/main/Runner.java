package main;

import calculation_layer.Catalog;
import entity_layer.EntityCatalog;
import layer_converter.CalculationToEntity;
import layer_converter.EntityToCalculation;
import layer_converter.LayerConverter;
import serialization.ByteSerializer;
import serialization.TextSerializer;
import serialization.XMLSerializer;

import java.io.IOException;

/**
 * Created by Aphex on 28.05.2016.
 */
public class Runner {
    public static void execute() {
        Catalog catalog =Initializer.initialize();
        System.out.print(catalog);
        try {
            catalog = SerializationShower.showSerialization(catalog,new TextSerializer(),"object_text.ser");
            System.out.print("No changes in catalog after text serialization:\n"+
                    catalog);
            catalog = SerializationShower.showSerialization(catalog,new ByteSerializer(),"object_byte.ser");
            System.out.print("No changes in catalog after byte serialization:\n"+
                    catalog);
            catalog = SerializationShower.showSerialization(catalog,new XMLSerializer(),"object.xml");
            System.out.print("No changes in catalog after xml serialization:\n"+
                    catalog);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
