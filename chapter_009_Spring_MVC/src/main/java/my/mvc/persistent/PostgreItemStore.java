package my.mvc.persistent;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import my.mvc.models.Item;
import my.mvc.models.Item_;
import my.mvc.utils.UtilHibernate;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
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

        return this.tx(session -> {

                    CriteriaBuilder builder = session.getCriteriaBuilder();
                    CriteriaQuery<Item> query = builder.createQuery(Item.class);
                    Root<Item> root = query.from(Item.class);
                    query.select(root);


                    if (parameters.containsKey("sDate")) {

                        query.where(
                                builder.between(
                                        root.get(Item_.created)
                                        , (Timestamp) parameters.get("sDate")
                                        , (Timestamp) parameters.get("eDate")
                                )
                        );


                    }
                    if (parameters.containsKey("carId")) {

                        query.where(
                                builder.equal(
                                        root.get(Item_.car)
                                        , parameters.get("carId")
                                )
                        );
                   }
                    if (parameters.containsKey("withPhoto")) {
                        if ((boolean) parameters.get("withPhoto")) {
                            query.where(builder.notEqual(root.get(Item_.coverPath),""));

                        }
                    }
                    return session.createQuery(query).getResultList();
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
