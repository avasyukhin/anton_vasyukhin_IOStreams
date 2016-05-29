package catalog;

import java.io.*;
import java.util.Scanner;

/**
 * Created by Aphex on 28.05.2016.
 */
public class Track implements Externalizable {
    String name;
    int length;

    public Track(int length, String name) {
        this.length = length;
        this.name = name;
    }

    public void writeExternal(ObjectOutput out){
        int[] hoursMinutesArray = new int[3];
        for (int i=0;i<3;i++){
            hoursMinutesArray[i]=length%60;
            length/=60;
        }
        String xml = "<track name=\""+name+
                "\" length=\""+hoursMinutesArray[2]+"h:"+
                hoursMinutesArray[1]+"m:"+
                hoursMinutesArray[0]+"s\"/>\n";
        try {
            out.writeUTF(xml);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readExternal(ObjectInput in){

    }
}
