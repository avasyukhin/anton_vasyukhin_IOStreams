package serialization;


import entity_layer.EntityCatalog;
import entity_layer.EntityPerformer;
import entity_layer.EntityAlbum;
import entity_layer.EntityTrack;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aphex on 31.05.2016.
 */
public class TextSerializer implements Serializer<EntityCatalog> {
    private final String PERFORMER_MARK = "PERFORMER";
    private final String ALBUM_NAME_MARK = "##ALBUM_NAME";
    private final String ALBUM_GENRE_MARK = "##ALBUM_GENRE";
    private final String TRACK_NAME_MARK = "####TRACK_NAME";
    private final String TRACK_LENGTH_MARK = "####TRACK_LENGTH";
    private final String END_CATALOG = "END_CATALOG";
    private final String END_PERFORMER = "END_PERFORMER";
    private final String END_ALBUM = "END_ALBUM";


    

    public void serialize(EntityCatalog entity,String filepath) throws IOException{
        PrintWriter out = new PrintWriter(
                    new BufferedWriter(
                        new FileWriter(filepath)));
        writeCatalog(out,entity);
        out.flush();
        out.close();
    }

    public EntityCatalog deserialize(String filepath) throws IOException {
        EntityCatalog EntityCatalog =new EntityCatalog();
        BufferedReader in = new BufferedReader(
                        new FileReader(filepath));
        EntityCatalog catalog = readCatalog(in);
        in.close();
        return catalog;
    }

    public void writeCatalog(PrintWriter out, EntityCatalog entity) throws IOException {
        for (EntityPerformer EntityPerformer : entity.getEntityPerformers()) {
            out.println(PERFORMER_MARK);
            out.println(EntityPerformer.getName());
            for (EntityAlbum EntityAlbum : EntityPerformer.getEntityAlbums()) {
                out.println(ALBUM_NAME_MARK);
                out.println('\t'+ EntityAlbum.getName());
                out.println(ALBUM_GENRE_MARK);
                out.println('\t' + EntityAlbum.getGenre());
                for (entity_layer.EntityTrack EntityTrack : EntityAlbum.getEntityTracks()) {
                    out.println(TRACK_NAME_MARK);
                    out.println("\t\t"+ EntityTrack.getName());
                    out.println(TRACK_LENGTH_MARK);
                    out.println("\t\t" + EntityTrack.getLength());
                }
                out.println(END_ALBUM);
            }
            out.println(END_PERFORMER);
        }
        out.println(END_CATALOG);
    }

    private void checkFormat(String inString, String markerString) {
        if (!inString.equals(markerString)) {
            try {
                throw new NotSerializableException("File corrupted in marker string " + markerString);
            } catch (NotSerializableException e) {
                e.printStackTrace();
            }
        }
    }

    private EntityCatalog readCatalog(BufferedReader in) throws IOException {
        List<EntityPerformer> EntityPerformers = new ArrayList<EntityPerformer>();
        while (true) {
            String inputPerformerMark = in.readLine();
            if (inputPerformerMark.equals(END_CATALOG)) {
                break;
            }
            checkFormat(inputPerformerMark, PERFORMER_MARK);
            String performerName = in.readLine().trim();
            List<EntityAlbum> EntityAlbums = new ArrayList<EntityAlbum>();
            while (true) {
                String inputAlbumMark = in.readLine();
                if (inputAlbumMark.equals(END_PERFORMER)) {
                    break;
                }
                checkFormat(inputAlbumMark, ALBUM_NAME_MARK);
                String albumName = in.readLine().trim();
                checkFormat(in.readLine(), ALBUM_GENRE_MARK);
                String albumGenre = in.readLine().trim();
                List<EntityTrack> EntityTracks = new ArrayList<EntityTrack>();
                while (true) {
                    String inputTrackMark = in.readLine();
                    if (inputTrackMark.equals(END_ALBUM)) {
                        break;
                    }
                    checkFormat(inputTrackMark, TRACK_NAME_MARK);
                    String trackName = in.readLine().trim();
                    checkFormat(in.readLine(), TRACK_LENGTH_MARK);
                    int trackLength = Integer.parseInt(in.readLine().trim());
                    EntityTracks.add(new EntityTrack(trackLength, trackName));
                }
                EntityAlbums.add(new EntityAlbum(albumName, albumGenre, EntityTracks));
            }
            EntityPerformers.add(new EntityPerformer(performerName, EntityAlbums));
        }
        return new EntityCatalog(EntityPerformers);
    }
}
