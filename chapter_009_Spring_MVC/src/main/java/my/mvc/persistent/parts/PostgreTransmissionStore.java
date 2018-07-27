package my.mvc.persistent.parts;

import my.mvc.models.parts.Transmission;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import my.mvc.persistent.Store;
import my.mvc.utils.UtilHibernate;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Created by Ivan Sliusar on 01.06.2018.
 * Red Line Soft corp.
 */
public class PostgreTransmissionStore implements Store<Transmission> {
    final static PostgreTransmissionStore INSTANCE = new PostgreTransmissionStore();
    public static Store getInstance(){
       return INSTANCE;
    }
    private PostgreTransmissionStore() {
}

    @Override
    public void add(Transmission transmission) {
        this.tx(session -> session.save(transmission));
    }

    @Override
    public void update(Transmission transmission) {
        this.tx(session -> {
            session.update(transmission);
            return true;
        });

    }

    @Override
    public boolean delete(Transmission transmission) {
        return this.tx(session -> {
            final Query query = session.createQuery("delete from Transmission where id=:id");
            query.setInteger("id", transmission.getId());
            query.executeUpdate();
            return true;
        });
    }

    @Override
    public List<Transmission> findAll() {
        return (List<Transmission>) this.tx(
                session -> session.createQuery("from Transmission ").list()
        );
    }

    @Override
    public Transmission findById(int id) {
        return this.tx(session -> session.get(Transmission.class,id));
    }

    @Override
    public List<Transmission> findAllByParam(Map<String, Object> param) {
        return null;
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


