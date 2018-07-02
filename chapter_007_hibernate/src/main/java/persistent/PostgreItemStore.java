package persistent;

import models.Item;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.UtilHibernate;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Created by Ivan Sliusar on 12.06.2018.
 * Red Line Soft corp.
 */
public class PostgreItemStore implements Store<Item> {
    private static final Logger LOGGER = Logger.getLogger(PostgreItemStore.class);
    final static PostgreItemStore INSTANCE = new PostgreItemStore();

    public static Store getInstance() {
        return INSTANCE;
    }

    private PostgreItemStore() {
    }

    @Override
    public void add(Item entity) {
        this.tx(session -> session.save(entity));
    }

    @Override
    public void update(Item entity) {
        this.tx(session -> {
            session.update(entity);
            return true;
        });

    }

    @Override
    public boolean delete(Item entity) {
        return this.tx(session -> {
            final Query query = session.createQuery("delete from Item where id=:id");
            query.setInteger("id", entity.getId());
            query.executeUpdate();
            return true;
        });
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Item> findAll() {
        return (List<Item>) this.tx(
                session -> session.createQuery("from Item ").list()
        );
    }


    @Override
    public Item findById(int id) {
        return this.tx(session -> session.get(Item.class, id));
    }


    @Override
    @SuppressWarnings("unchecked")
    public List<Item> findAllByParam(Map<String, Object> parameters) {

        return (List<Item>) this.tx(session -> {

                    StringBuilder hql = new StringBuilder("from Item where 1=1");
                    // add dynamic conditions
                    if (parameters.containsKey("sDate")) {
                        hql.append(" AND created between :sDate and :eDate");
                    }
                    if (parameters.containsKey("carId")) {
                        hql.append(" AND car.id =:carId");
                    }
                    if (parameters.containsKey("withPhoto")) {
                        if ((boolean) parameters.get("withPhoto"))
                            hql.append(" AND coverPath != '' AND coverPath IS NOT NULL");
                    }

                    final Query query = session.createQuery(hql.toString());

                    // set parameters from input map
                    for (String p : query.getNamedParameters()) {
                        query.setParameter(p, parameters.get(p));
                    }
                    return query.list();
                }
        );
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = UtilHibernate.openSession();
        final Transaction transaction = session.beginTransaction();
        try {
            return command.apply(session);
        } catch (final Exception e) {
            LOGGER.error(e);
            throw e;
        } finally {
            transaction.commit();
            session.close();
        }
    }

}
