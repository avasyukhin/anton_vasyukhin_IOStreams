package main;

import calculation_layer.Album;
import calculation_layer.Catalog;
import calculation_layer.Performer;
import calculation_layer.Track;
import layer_converter.CalculationToEntity;
import layer_converter.EntityToCalculation;
import layer_converter.LayerConverter;
import serialization.ByteSerializer;
import serialization.Serializer;
import serialization.TextSerializer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aphex on 28.05.2016.
 */
public class Runner {
    public static void execute(){
        List <Track> tracks = new ArrayList<Track>();
        tracks.add(new Track(120,"testtrack"));
        Album testAlbum = new Album("testalbum","testgenre",tracks);
        List <Album> albums =new ArrayList<Album>();
        albums.add(testAlbum);
        Performer performer = new Performer("testperfomer",albums);
        List<Performer> performers = new ArrayList<Performer>();
        performers.add(performer);
        Catalog calculation = new Catalog(performers);
        LayerConverter converter = new CalculationToEntity();
        entity_layer.Catalog entity = (entity_layer.Catalog) converter.convert(calculation);
        Serializer serializer = new TextSerializer(entity);
        serializer.serialize("object_text.ser");
        serializer.deserialize("object_text.ser");
        converter = new EntityToCalculation();
        calculation = (Catalog) converter.convert(serializer.getEntity());
        System.out.println(calculation);
        serializer =new ByteSerializer(entity);
        serializer.serialize("object_byte.ser");
        serializer.deserialize("object_byte.ser");
        System.out.println(converter.convert(serializer.getEntity()));
    }
}
