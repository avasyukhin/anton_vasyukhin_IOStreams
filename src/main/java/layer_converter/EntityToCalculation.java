package layer_converter;







import calculation_layer.Album;
import calculation_layer.Catalog;
import calculation_layer.Performer;
import calculation_layer.Track;
import entity_layer.EntityCatalog;
import entity_layer.EntityPerformer;
import entity_layer.EntityAlbum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aphex on 31.05.2016.
 */
public class EntityToCalculation implements LayerConverter<EntityCatalog, Catalog>{
    public Catalog convert (EntityCatalog inputLayer){
        List<Performer> performers = new ArrayList<Performer>();
        for (EntityPerformer EntityPerformer : inputLayer.getEntityPerformers()){
            List<Album> albums = new ArrayList<Album>();
            for (EntityAlbum EntityAlbum : EntityPerformer.getEntityAlbums()){
                List<Track> tracks = new ArrayList<Track>();
                for (entity_layer.EntityTrack EntityTrack : EntityAlbum.getEntityTracks()){
                    tracks.add(new Track(EntityTrack.getLength(), EntityTrack.getName()));
                }
                albums.add(new Album(EntityAlbum.getName(), EntityAlbum.getGenre(),tracks));
            }
            performers.add(new Performer(EntityPerformer.getName(),albums));
        }
        return new Catalog(performers);
    }
}
