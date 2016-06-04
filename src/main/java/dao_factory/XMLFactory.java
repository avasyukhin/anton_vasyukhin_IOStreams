package dao_factory;

import dao.AbstractDAO;
import dao.XMLDAO;

/**
 * Created by Aphex on 04.06.2016.
 */
public class XMLFactory implements AbstractFactory{
    private String filepath;

    public XMLFactory(String filepath) {
        this.filepath = filepath;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }


    public AbstractDAO createDAO(){
        return new XMLDAO(filepath);
    }
}
