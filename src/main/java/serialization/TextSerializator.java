package serialization;


import entity_layer.Album;
import entity_layer.Catalog;
import entity_layer.Performer;
import entity_layer.Track;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aphex on 31.05.2016.
 */
public class TextSerializator implements Serializator<Catalog> {
    private final String PERFORMER_MARK = "PERFORMER\n";
    private final String ALBUM_NAME_MARK = "##ALBUM_NAME\n";
    private final String ALBUM_GENRE_MARK = "##ALBUM_GENRE\n";
    private final String TRACK_NAME_MARK = "####TRACK_NAME\n";
    private final String TRACK_LENGTH_MARK = "####TRACK_LENGTH\n";
    private final String END_CATALOG = "END_CATALOG";
    private final String END_PERFORMER = "END_PERFORMER\n";
    private final String END_ALBUM = "END_ALBUM\n";
    private Catalog entity;

    public TextSerializator() {
    }

    public TextSerializator(Catalog entity) {
        this.entity = entity;
    }

    public Catalog getEntity() {
        return entity;
    }

    public void setEntity(Catalog entity) {
        this.entity = entity;
    }

    public void serialize(String filepath){
        try{
            ObjectOutputStream out =new ObjectOutputStream(
                    new BufferedOutputStream(
                            new FileOutputStream(filepath)));
            out.writeObject(this);
            out.flush();
            out.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public Serializator deserialize(String filepath){
        Serializator serializator=new TextSerializator();
        try{
            ObjectInputStream in =new ObjectInputStream(
                    new BufferedInputStream(
                            new FileInputStream(filepath)));
            serializator = (TextSerializator)in.readObject();
            in.close();
        }catch (IOException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }finally {
            return serializator;
        }
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        for (Performer performer : entity.getPerformers()) {
            out.writeUTF(PERFORMER_MARK);
            out.writeUTF(performer.getName() + '\n');
            for (Album album : performer.getAlbums()) {
                out.writeUTF(ALBUM_NAME_MARK);
                out.writeUTF(album.getName() + '\n');
                out.writeUTF(ALBUM_GENRE_MARK);
                out.writeUTF(album.getGenre() + '\n');
                for (Track track: album.getTracks()){
                    out.writeUTF(TRACK_NAME_MARK);
                    out.writeUTF(track.getName() + '\n');
                    out.writeUTF(TRACK_LENGTH_MARK);
                    out.writeInt(track.getLength());
                }
                out.writeUTF(END_ALBUM);
            }
            out.writeUTF(END_PERFORMER);
        }
        out.writeUTF(END_CATALOG);
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

    private void readObject(ObjectInputStream in) throws IOException {
        List<Performer> performers = new ArrayList<Performer>();
        while (true) {
            String inputPerformerMark = in.readUTF();
            if (inputPerformerMark.equals(END_CATALOG)) {
                break;
            }
            checkFormat(inputPerformerMark, PERFORMER_MARK);
            String performerName = in.readUTF().trim();
            List<Album> albums = new ArrayList<Album>();
            while (true) {
                String inputAlbumMark = in.readUTF();
                if (inputAlbumMark.equals(END_PERFORMER)) {
                    break;
                }
                checkFormat(inputAlbumMark, ALBUM_NAME_MARK);
                String albumName = in.readUTF().trim();
                checkFormat(in.readUTF(), ALBUM_GENRE_MARK);
                String albumGenre = in.readUTF().trim();
                List<Track> tracks = new ArrayList<Track>();
                while (true) {
                    String inputTrackMark = in.readUTF();
                    if (inputTrackMark.equals(END_ALBUM)) {
                        break;
                    }
                    checkFormat(inputTrackMark, TRACK_NAME_MARK);
                    String trackName = in.readUTF().trim();
                    checkFormat(in.readUTF(), TRACK_LENGTH_MARK);
                    int trackLength = in.readInt();
                    tracks.add(new Track(trackLength,trackName));
                }
                albums.add(new Album(albumName,albumGenre,tracks));
            }
            performers.add(new Performer(performerName,albums));
        }
        entity=new Catalog(performers);
    }
}
