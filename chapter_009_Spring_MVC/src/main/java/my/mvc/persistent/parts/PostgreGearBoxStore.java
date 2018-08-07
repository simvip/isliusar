package my.mvc.persistent.parts;

import my.mvc.models.parts.GearBox;
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
public class PostgreGearBoxStore implements Store<GearBox> {
    final static PostgreGearBoxStore INSTANCE = new PostgreGearBoxStore();
    public static Store getInstance(){
       return INSTANCE;
    }
    private PostgreGearBoxStore() {}

    @Override
    public void add(GearBox gearBox) {
        this.tx(session -> session.save(gearBox));
    }

    @Override
    public void update(GearBox gearBox) {
        this.tx(session -> {
            session.update(gearBox);
            return true;
        });
    }

    @Override
    public boolean delete(GearBox gearBox) {
        return this.tx(session -> {
            final Query query = session.createQuery("delete from GearBox where id=:id");
            query.setInteger("id", gearBox.getId());
            query.executeUpdate();
            return true;
        });
    }

    @Override
    public List<GearBox> findAll() {
        return (List<GearBox>) this.tx(
                session -> session.createQuery("from GearBox ").list()
        );
    }

    @Override
    public GearBox findById(int id) {
        return this.tx(session -> session.get(GearBox.class,id));
    }

    @Override
    public List<GearBox> findAllByParam(Map<String, Object> param) {
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


