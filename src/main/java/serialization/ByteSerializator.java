package serialization;

import entity_layer.Catalog;

import java.io.*;

/**
 * Created by Aphex on 31.05.2016.
 */
public class ByteSerializator implements Serializator<Catalog> {
    private Catalog entity;

    public ByteSerializator() {
    }

    public ByteSerializator(Catalog entity) {
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
        Serializator serializator=new ByteSerializator();
        try{
            ObjectInputStream in =new ObjectInputStream(
                    new BufferedInputStream(
                            new FileInputStream(filepath)));
            serializator = (ByteSerializator)in.readObject();
            in.close();
        }catch (IOException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }finally {
            return serializator;
        }
    }
}
