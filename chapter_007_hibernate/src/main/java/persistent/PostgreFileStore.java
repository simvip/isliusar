package persistent;

import models.FileImage;
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
public class PostgreFileStore{
    private static final Logger LOGGER = Logger.getLogger(PostgreFileStore.class);
    final static PostgreFileStore INSTANCE = new PostgreFileStore();
    public static PostgreFileStore getInstance(){
       return INSTANCE;
    }
    private PostgreFileStore() {
}


    public void add(FileImage fileImage) {
        this.tx(session -> session.save(fileImage));
    }


    public void update(FileImage fileImage) {
        this.tx(session -> {
            session.update(fileImage);
            return true;
        });

    }


    public boolean delete(FileImage fileImage) {
        return this.tx(session -> {
            final Query query = session.createQuery("delete from FileImage where id=:id");
            query.setInteger("id", fileImage.getId());
            query.executeUpdate();
            return true;
        });
    }


    public List<FileImage> findAll(int id) {
        return (List<FileImage>) this.tx(
                session -> {
                    final Query query = session.createQuery("from FileImage where itemId=:id");
                    query.setInteger("id",id);
                    return query.list();
                }
        );
    }


    public FileImage findById(int id) {
        return this.tx(session -> session.get(FileImage.class,id));
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


