package layer_converter;



import calculation_layer.Album;
import calculation_layer.Catalog;
import calculation_layer.Performer;
import calculation_layer.Track;
import entity_layer.EntityCatalog;
import entity_layer.EntityPerformer;
import entity_layer.EntityAlbum;
import entity_layer.EntityTrack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aphex on 31.05.2016.
 */
public class CalculationToEntity implements LayerConverter<Catalog, EntityCatalog>{
    public EntityCatalog convert (Catalog inputLayer){
        List<EntityPerformer> EntityPerformers = new ArrayList<EntityPerformer>();
        for (Performer performer: inputLayer.getPerformers()){
            List<EntityAlbum> EntityAlbums = new ArrayList<EntityAlbum>();
            for (Album album: performer.getAlbums()){
                List<EntityTrack> EntityTracks = new ArrayList<EntityTrack>();
                for (Track track: album.getTracks()){
                    EntityTracks.add(new EntityTrack(track.getLength(),track.getName()));
                }
                EntityAlbums.add(new EntityAlbum(album.getName(),album.getGenre(), EntityTracks));
            }
            EntityPerformers.add(new EntityPerformer(performer.getName(), EntityAlbums));
        }
        return new EntityCatalog(EntityPerformers);
    }
}
