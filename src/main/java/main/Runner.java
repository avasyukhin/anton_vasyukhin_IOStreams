package main;

import catalog.Album;
import catalog.Catalog;
import catalog.Performer;
import catalog.Track;

import java.io.*;
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
        Catalog catalog = new Catalog(performers);
        try {
            catalog.writeExternal(new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("test.xml"))) );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
