package persistent;

import models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.UtilHibernate;

import java.util.List;
import java.util.function.Function;

/**
 * Created by Ivan Sliusar on 01.06.2018.
 * Red Line Soft corp.
 */
public class PostgreStore implements Store<User> {
    final static PostgreStore INSTANCE = new PostgreStore();
    public static Store getInstance(){
       return INSTANCE;
    }
    private PostgreStore() {
}

    @Override
    public void add(User user) {
        this.tx(session -> session.save(user));
    }

    @Override
    public void update(User user) {
        this.tx(session -> {
            session.update(user);
            return true;
        });

    }

    @Override
    public boolean delete(User user) {
        return this.tx(session -> {
            final Query query = session.createQuery("delete from User where id=:id");
            query.setInteger("id", user.getId());
            query.executeUpdate();
            return true;
        });
    }

    @Override
    public List<User> findAll() {
        return (List<User>) this.tx(
                session -> session.createQuery("from User").list()
        );
    }

    @Override
    public User findById(int id) {
        return this.tx(session -> session.get(User.class,id));
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


