package persistent;

import models.Car;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.UtilHibernate;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Created by Ivan Sliusar on 01.06.2018.
 * Red Line Soft corp.
 */
public class PostgreCarStore implements Store<Car> {
    final static PostgreCarStore INSTANCE = new PostgreCarStore();
    public static Store getInstance(){
       return INSTANCE;
    }
    private PostgreCarStore() {
}

    @Override
    public void add(Car car) {
        this.tx(session -> session.save(car));
    }

    @Override
    public void update(Car car) {
        this.tx(session -> {
            session.update(car);
            return true;
        });

    }

    @Override
    public boolean delete(Car car) {
        return this.tx(session -> {
            final Query query = session.createQuery("delete from Car where id=:id");
            query.setInteger("id", car.getId());
            query.executeUpdate();
            return true;
        });
    }

    @Override
    public List<Car> findAll() {
        return (List<Car>) this.tx(
                session -> session.createQuery("from Car ").list()
        );
    }

    @Override
    public Car findById(int id) {
        return this.tx(session -> session.get(Car.class,id));
    }

    @Override
    public List<Car> findAllByParam(Map<String, Object> param) {
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


