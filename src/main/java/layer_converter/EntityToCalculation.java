package layer_converter;







import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aphex on 31.05.2016.
 */
public class EntityToCalculation implements LayerConverter<entity_layer.Catalog, calculation_layer.Catalog>{
    public calculation_layer.Catalog convert (entity_layer.Catalog inputLayer){
        List<calculation_layer.Performer> performers = new ArrayList<calculation_layer.Performer>();
        for (entity_layer.Performer performer: inputLayer.getPerformers()){
            List<calculation_layer.Album> albums = new ArrayList<calculation_layer.Album>();
            for (entity_layer.Album album: performer.getAlbums()){
                List<calculation_layer.Track> tracks = new ArrayList<calculation_layer.Track>();
                for (entity_layer.Track track: album.getTracks()){
                    tracks.add(new calculation_layer.Track(track.getLength(),track.getName()));
                }
                albums.add(new calculation_layer.Album(album.getName(),album.getGenre(),tracks));
            }
            performers.add(new calculation_layer.Performer(performer.getName(),albums));
        }
        return new calculation_layer.Catalog(performers);
    }
}
