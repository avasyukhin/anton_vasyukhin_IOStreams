package dao_factory;

import dao.EntityPerformerDAO;

/**
 * Created by Aphex on 03.06.2016.
 */
public interface AbstractFactory {
        public EntityPerformerDAO createDAO();
}
