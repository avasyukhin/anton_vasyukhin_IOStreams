package entitiy_layer;



import java.io.*;
import java.util.List;


/**
 * Created by Aphex on 28.05.2016.
 */
public class Catalog implements Serializable {
    private List<calculation_layer.Performer> performers;

    public Catalog(List<calculation_layer.Performer> performers) {
        this.performers = performers;
    }


    public List<calculation_layer.Performer> getPerformers() {
        return performers;
    }
}
