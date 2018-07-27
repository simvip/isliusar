package my.mvc.persistent.parts;

import my.mvc.models.parts.Engine;
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
public class PostgreEngineStore implements Store<Engine> {
    final static PostgreEngineStore INSTANCE = new PostgreEngineStore();
    public static Store getInstance(){
       return INSTANCE;
    }
    private PostgreEngineStore() {
}

    @Override
    public void add(Engine engine) {
        this.tx(session -> session.save(engine));
    }

    @Override
    public void update(Engine engine) {
        this.tx(session -> {
            session.update(engine);
            return true;
        });

    }

    @Override
    public boolean delete(Engine engine) {
        return this.tx(session -> {
            final Query query = session.createQuery("delete from Engine where id=:id");
            query.setInteger("id", engine.getId());
            query.executeUpdate();
            return true;
        });
    }

    @Override
    public List<Engine> findAll() {
        return (List<Engine>) this.tx(
                session -> session.createQuery("from Engine ").list()
        );
    }

    @Override
    public Engine findById(int id) {
        return this.tx(session -> session.get(Engine.class,id));
    }

    @Override
    public List<Engine> findAllByParam(Map<String, Object> param) {
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


