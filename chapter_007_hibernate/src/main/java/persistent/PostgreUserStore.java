package persistent;

import models.User;
import org.apache.log4j.Logger;
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
public class PostgreUserStore {
    private static final Logger LOGGER = Logger.getLogger(PostgreUserStore.class);
    final static PostgreUserStore INSTANCE = new PostgreUserStore();
    public static PostgreUserStore getInstance(){
       return INSTANCE;
    }
    private PostgreUserStore() {
}


    public void add(User user) {
        this.tx(session -> session.save(user));
    }


    public void update(User user) {
        this.tx(session -> {
            session.update(user);
            return true;
        });

    }


    public boolean delete(User user) {
        return this.tx(session -> {
            final Query query = session.createQuery("delete from User where id=:id");
            query.setInteger("id", user.getId());
            query.executeUpdate();
            return true;
        });
    }


    public List<User> findAll() {
        return (List<User>) this.tx(
                session -> session.createQuery("from User").list()
        );
    }


    public User findById(int id) {
        return this.tx(session -> session.get(User.class,id));
    }

    public User findByEmail(String email) {
        return (User) this.tx(
                session -> {
                    final Query query = session.createQuery("from User where email=:email");
                    query.setString("email",email);
                    return query.getSingleResult();
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


