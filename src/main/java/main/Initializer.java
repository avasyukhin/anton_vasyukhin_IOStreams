package main;

import calculation_layer.Album;
import calculation_layer.Performer;
import calculation_layer.Track;
import calculation_layer.Catalog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aphex on 31.05.2016.
 */
public class Initializer {
    public static Catalog initialize(){
        List<Track> tracks = new ArrayList<Track>();
        tracks.add(new Track(120, "testtrack"));
        Album testAlbum = new Album("testalbum", "testgenre", tracks);
        List<Album> albums = new ArrayList<Album>();
        albums.add(testAlbum);
        Performer performer = new Performer("testperfomer", albums);
        List<Performer> performers = new ArrayList<Performer>();
        performers.add(performer);
        return new calculation_layer.Catalog(performers);
    }
}
