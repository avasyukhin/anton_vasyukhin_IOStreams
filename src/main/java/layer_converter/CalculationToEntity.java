package layer_converter;



import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aphex on 31.05.2016.
 */
public class CalculationToEntity implements LayerConverter<calculation_layer.Catalog, entity_layer.Catalog>{
    public entity_layer.Catalog convert (calculation_layer.Catalog inputLayer){
        List<entity_layer.Performer> performers = new ArrayList<entity_layer.Performer>();
        for (calculation_layer.Performer performer: inputLayer.getPerformers()){
            List<entity_layer.Album> albums = new ArrayList<entity_layer.Album>();
            for (calculation_layer.Album album: performer.getAlbums()){
                List<entity_layer.Track> tracks = new ArrayList<entity_layer.Track>();
                for (calculation_layer.Track track: album.getTracks()){
                    tracks.add(new entity_layer.Track(track.getLength(),track.getName()));
                }
                albums.add(new entity_layer.Album(album.getName(),album.getGenre(),tracks));
            }
            performers.add(new entity_layer.Performer(performer.getName(),albums));
        }
        return new entity_layer.Catalog(performers);
    }
}
