package dao_factory;

import dao.EntityPerformerDAO;
import dao.RDBDAO;

/**
 * Created by Aphex on 11.06.2016.
 */
public class RDBFactory implements AbstractFactory {
    private String host,port,dbname,user,password;

    public RDBFactory(String host, String port, String dbname, String user, String password) {
        this.host = host;
        this.port = port;
        this.dbname = dbname;
        this.user = user;
        this.password = password;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDbname() {
        return dbname;
    }

    public void setDbname(String dbname) {
        this.dbname = dbname;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public EntityPerformerDAO createDAO() {
        return new RDBDAO(host,port,dbname,user,password);
    }
}
