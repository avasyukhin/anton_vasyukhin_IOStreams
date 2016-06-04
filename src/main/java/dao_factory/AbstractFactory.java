package dao_factory;

import dao.AbstractDAO;

/**
 * Created by Aphex on 03.06.2016.
 */
public interface AbstractFactory {
        public AbstractDAO createDAO();
}
