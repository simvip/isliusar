import models.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by Ivan Sliusar on 22.04.2018.
 * Red Line Soft corp.
 */
public class TestClass {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure()
                .addResource("models/Item.hbm.xml")
                .buildSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();

        Item item = new Item();
        item.setDecs("Test creation");
        session.saveOrUpdate(item);

        Item item2 = new Item();
        item2.setDecs("1 Test creation");
        session.saveOrUpdate(item2);

        session.getTransaction().commit();
        session.close();
        factory.close();
    }
}
