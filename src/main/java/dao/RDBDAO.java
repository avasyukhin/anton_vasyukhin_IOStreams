package dao;

import dao_factory.AbstractFactory;
import dao_factory.XMLFactory;
import entity_layer.EntityAlbum;
import entity_layer.EntityPerformer;
import entity_layer.EntityTrack;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Aphex on 10.06.2016.
 */
public class RDBDAO implements EntityPerformerDAO{
    private Connection conn;
    private String host,port,dbname;
    private static final Logger log = Logger.getLogger(RDBDAO.class);
    private PreparedStatement getPerformerStatement,getPerformerAlbumsStatement,getAlbumStatement,getTrackStatement,
            addPerformerStatement, addAlbumStatement,addTrackStatement,
            removeStatement,removeAllStatement,getAllStatement,getAlbumByNameStatement,
            updatePerformerStatement,updateAlbumStatement,updateTrackStatement,
            getTotalLengthStatement;


    public RDBDAO(String host, String port, String dbname, String user, String password){
        this.host=host;
        this.port=port;
        this.dbname=dbname;
        String url ="jdbc:postgresql://"+host+":"+port+"/"+dbname;
        Properties props = new Properties();
        props.setProperty("user",user);
        props.setProperty("password",password);
        props.setProperty("ssl","false");
        try {
            Class.forName("org.postgresql.Driver");
            conn= DriverManager.getConnection(url,props);
            log.info("DAO connected to " + url);
            getPerformerAlbumsStatement=conn.prepareStatement(
                    "SELECT \n" +
                            "  albums.id,alb_name,genre\n" +
                            "FROM \n" +
                            "  albums\n" +
                            "INNER JOIN \n" +
                            "  performers\n" +
                            "ON \n" +
                            "  perf_id=performers.id\n" +
                            "WHERE \n" +
                            "  perf_name =?;");
            getPerformerStatement=conn.prepareStatement(
                    "SELECT \n" +
                            "  id\n" +
                            "FROM \n" +
                            "  performers\n" +
                            "WHERE \n" +
                            "  perf_name =?;");
            getAlbumStatement=conn.prepareStatement(
                    "SELECT \n" +
                            "  id,alb_name,genre\n" +
                            "FROM \n" +
                            "  albums\n" +
                            "WHERE \n" +
                            "  perf_id =?;");
            getAlbumByNameStatement=conn.prepareStatement(
                    "SELECT \n" +
                            "  id\n" +
                            "FROM \n" +
                            "  albums\n" +
                            "WHERE \n" +
                            "  perf_id =? AND alb_name =?;");
            getTrackStatement=conn.prepareStatement(
                    "SELECT \n" +
                            "  track_name,track_len\n" +
                            "FROM \n" +
                            "  tracks\n" +
                            "WHERE \n" +
                            "  alb_id =?;");
            getAllStatement=conn.prepareStatement(
                    "SELECT \n" +
                            "id, perf_name\n" +
                            "FROM \n" +
                            "  performers\n;");
            removeStatement=conn.prepareStatement(
                    "DELETE \n"+
                            "FROM performers\n" +
                            "WHERE perf_name = ?;"
            );
            removeAllStatement=conn.prepareStatement(
                    "DELETE \n"+
                            "FROM performers\n;"
            );
            addPerformerStatement=conn.prepareStatement(
                    "INSERT \n" +
                            "INTO performers (perf_name)"+
                            "VALUES(?);" );
            addAlbumStatement=conn.prepareStatement(
                    "INSERT \n" +
                            "INTO albums (alb_name,genre,perf_id)"+
                            "VALUES(?,?,?);" );
            addTrackStatement=conn.prepareStatement(
                    "INSERT \n" +
                            "INTO tracks (track_name,track_len,alb_id)"+
                            "VALUES(?,?,?);" );
            getTotalLengthStatement=conn.prepareStatement(
                    "SELECT \n" +
                        "SUM(track_len)\n" +
                        "FROM \n" +
                        "  public.albums, \n" +
                        "  public.performers, \n" +
                        "  public.tracks\n" +
                        "WHERE \n" +
                        "  performers.id = albums.perf_id AND\n" +
                        "  tracks.alb_id = albums.id AND\n" +
                        "  performers.perf_name =?;");
        } catch (SQLException e) {
            log.error("SQL error",e);
        } catch (ClassNotFoundException e) {
            log.error("Driver not found", e);
        }
    }


    public EntityPerformer get(String name)throws NoSuchFieldException{
        EntityPerformer performer = new EntityPerformer();
        try {
            getPerformerAlbumsStatement.setString(1,name);
            ResultSet albumsResult = getPerformerAlbumsStatement.executeQuery();
            List<EntityAlbum> albums = new ArrayList<>();
            while (albumsResult.next()){
                getTrackStatement.setInt(1, albumsResult.getInt(1));
                ResultSet tracksResult = getTrackStatement.executeQuery();
                List<EntityTrack> tracks = new ArrayList<>();
                while (tracksResult.next()){
                    tracks.add(new EntityTrack(tracksResult.getString(1),tracksResult.getInt(2)));
                }
                albums.add(new EntityAlbum(albumsResult.getString(2),albumsResult.getString(3),tracks));
            }
            if (albums.isEmpty()){
                throw new NoSuchFieldException("Not found performer with name " + name);
            }
            performer.setName(name);
            performer.setEntityAlbums(albums);
        } catch (SQLException e) {
            log.error("SQL error", e);
        }
        log.info("From RDB DAO got performer " + name);
        return performer;
    }


    public List<EntityPerformer> getAll(){
        List<EntityPerformer> performers = new ArrayList<>();
        try {
            ResultSet performersResult = getAllStatement.executeQuery();
            while (performersResult.next()) {
                String name=performersResult.getString(2);
                getAlbumStatement.setInt(1, performersResult.getInt(1));
                ResultSet albumsResult = getAlbumStatement.executeQuery();
                List<EntityAlbum> albums = new ArrayList<>();
                while (albumsResult.next()) {
                    getTrackStatement.setInt(1, albumsResult.getInt(1));
                    ResultSet tracksResult = getTrackStatement.executeQuery();
                    List<EntityTrack> tracks = new ArrayList<>();
                    while (tracksResult.next()) {
                        tracks.add(new EntityTrack(tracksResult.getString(1), tracksResult.getInt(2)));
                    }
                    albums.add(new EntityAlbum(albumsResult.getString(2), albumsResult.getString(3), tracks));
                }
                performers.add(new EntityPerformer(name,albums));
            }
            log.info("From RDB DAO got all performer ");
        } catch (SQLException e) {
            log.error("SQL error", e);
        }
        return performers;
    }
    public void add(EntityPerformer performer) {
        try {
            addPerformerStatement.setString(1, performer.getName());
            addPerformerStatement.executeUpdate();
            getPerformerStatement.setString(1, performer.getName());
            ResultSet performersResult = getPerformerStatement.executeQuery();
            performersResult.next();
            int perf_id = performersResult.getInt(1);
            for (EntityAlbum album : performer.getEntityAlbums()) {
                addAlbumStatement.setString(1, album.getName());
                addAlbumStatement.setString(2, album.getGenre());
                addAlbumStatement.setInt(3, perf_id);
                addAlbumStatement.executeUpdate();
                getAlbumByNameStatement.setInt(1, perf_id);
                getAlbumByNameStatement.setString(2, album.getName());
                ResultSet albumsResult = getAlbumByNameStatement.executeQuery();
                albumsResult.next();
                int alb_id = albumsResult.getInt(1);
                for (EntityTrack track : album.getEntityTracks()) {
                    addTrackStatement.setString(1, track.getName());
                    addTrackStatement.setInt(2, track.getLength());
                    addTrackStatement.setInt(3, alb_id);
                    addTrackStatement.executeUpdate();
                }
            }
            log.info("Added performer " + performer.getName());
        } catch (SQLException e) {
            log.error("SQL error", e);
        }
    }

    public void remove(EntityPerformer performer)throws NoSuchFieldException{
        try {
            removeStatement.setString(1,performer.getName());
            removeStatement.executeUpdate();
            log.info("Removed performer " + performer.getName());
        } catch (SQLException e) {
            log.error("SQL error", e);
        }
    }
    public void removeAll(){
        try {
            removeAllStatement.executeUpdate();
            log.info("Removed all performers ");
        } catch (SQLException e) {
            log.error("SQL error", e);
        }
    }


    public void update(EntityPerformer performer)throws NoSuchFieldException{}

    public int getTotalLength(String name)throws NoSuchFieldException{
        int length = 0;
        try {
            getTotalLengthStatement.setString(1,name);
            ResultSet lengths = getTotalLengthStatement.executeQuery();
            lengths.next();
            length = lengths.getInt(1);
        } catch (SQLException e) {
            log.error("SQL error", e);
        }
        return length;
    }

    public void copyFromXML(String filepath){
        AbstractFactory factory = new XMLFactory(filepath);
        EntityPerformerDAO xml = factory.createDAO();
        xml.getAll().forEach(this::add);
    }
}
