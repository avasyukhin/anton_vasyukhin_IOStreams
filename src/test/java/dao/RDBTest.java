package dao;

import dao_factory.AbstractFactory;
import dao_factory.RDBFactory;
import entity_layer.EntityAlbum;
import entity_layer.EntityPerformer;
import entity_layer.EntityTrack;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;

/**
 * Created by Aphex on 11.06.2016.
 */
public class RDBTest {
    /*
    You must redefine this parameters before running
     */
    static final String host="localhost",port="5433",dbname="postgres",user="postgres",password="1qa@WS3ed";

    AbstractFactory sqlFactory;
    RDBDAO dao;
    static List<EntityPerformer> testPerformers, updatedPerformers, extendedPerformers;
    static EntityPerformer testPerformer, updatedTestPerformer, performerToAdd;
    static String testperformerName = "testperfomer";
    static String dataPath = "test_data.xml";



    @BeforeClass
    public static void setTestPerformers(){
        testPerformers=new ArrayList<EntityPerformer>();
        updatedPerformers=new ArrayList<>();
        extendedPerformers=new ArrayList<>();
        EntityTrack testTrack = new EntityTrack("testtrack", 120);
        List<EntityTrack> tracks = new ArrayList<>();
        tracks.add(testTrack);
        EntityAlbum testAlbum = new EntityAlbum("testalbum","testgenre",tracks);
        EntityAlbum otherAlbum = new EntityAlbum("otheralbum","testgenre",tracks);
        List<EntityAlbum> testPerformerAlbums = new ArrayList<>();
        List<EntityAlbum> otherPerformerAlbums = new ArrayList<>();
        testPerformerAlbums.add(testAlbum);testPerformerAlbums.add(otherAlbum);
        otherPerformerAlbums.add(testAlbum);
        testPerformer = new EntityPerformer(testperformerName,testPerformerAlbums);
        updatedTestPerformer = new EntityPerformer(testperformerName,otherPerformerAlbums);
        performerToAdd = (new EntityPerformer("performertoadd",otherPerformerAlbums));
        testPerformers.add(testPerformer);
        testPerformers.add(new EntityPerformer("otherperfomer",otherPerformerAlbums));
        updatedPerformers.add(updatedTestPerformer);
        updatedPerformers.add(new EntityPerformer("otherperfomer", otherPerformerAlbums));
        extendedPerformers.add(testPerformer);
        extendedPerformers.add(new EntityPerformer("otherperfomer",otherPerformerAlbums));
        extendedPerformers.add(performerToAdd);
    }
    @Before
    public void setFactoryAndDao(){
        sqlFactory = new RDBFactory(host,port,dbname,user,password);
        dao = (RDBDAO)sqlFactory.createDAO();
        dao.removeAll();
        dao.copyFromXML(dataPath);
    }
    @After
    public void clear(){
        dao.removeAll();
    }

    @Test
    public void getTest() throws NoSuchFieldException {
        Assert.assertEquals(testPerformer, dao.get(testperformerName));
    }

    @Test
    public void getAllTest() throws NoSuchFieldException {
        Assert.assertEquals(testPerformers,dao.getAll());
    }

    @Test (expected = NoSuchFieldException.class)
    public void noSuchPerformerTest() throws NoSuchFieldException {
        dao.get("");
    }


    @Test
    public void totalLengthTest() throws NoSuchFieldException {
        Assert.assertThat(dao.getTotalLength(testperformerName),is(240));
    }

    @Test
    public void addTest() throws NoSuchFieldException {
        dao.add(performerToAdd);
        Assert.assertEquals(extendedPerformers,dao.getAll());
        dao.remove(performerToAdd);
    }
    @Ignore
    @Test
    public void updateTest() throws NoSuchFieldException {
        dao.update(updatedTestPerformer);
        Assert.assertEquals(updatedPerformers,dao.getAll());
        dao.update(testPerformer);
    }
}
