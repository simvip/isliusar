package persistent;

import models.Item;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.UtilHibernate;

import java.util.List;
import java.util.function.Function;

/**
 * Created by Ivan Sliusar on 12.06.2018.
 * Red Line Soft corp.
 */
public class PostgreItemStore implements Store<Item> {
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
    public List<Item> findAll() {
        return (List<Item>) this.tx(
                session -> session.createQuery("from Item ").list()
        );
    }


    @Override
    public Item findById(int id) {
        return this.tx(session -> session.get(Item.class, id));
    }


    private <T> T tx(final Function<Session, T> command) {
        final Session session = UtilHibernate.openSession();
        final Transaction transaction = session.beginTransaction();
        try {
            return command.apply(session);
        } catch (final Exception e) {
            throw e;
        } finally {
            transaction.commit();
            session.close();
        }
    }

}
