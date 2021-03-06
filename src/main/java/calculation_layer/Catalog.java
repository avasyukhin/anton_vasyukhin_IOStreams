package calculation_layer;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


/**
 * Created by Aphex on 28.05.2016.
 */
public class Catalog{
    private List<Performer> performers;

    public Catalog(List<Performer> performers) {
        this.performers = performers;
    }


    public List<Performer> getPerformers() {
        return performers;
    }

    public void setPerformers(List<Performer> performers) {
        this.performers = performers;
    }
    @Override
    public String toString(){
        String string="";
        for (Performer performer: performers){
            string+=performer.toString()+'\n';
        }
        return string;
    }
}
