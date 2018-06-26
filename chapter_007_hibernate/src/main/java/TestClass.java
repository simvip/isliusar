import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.UtilHibernate;


/**
 * Created by Ivan Sliusar on 22.04.2018.
 * Red Line Soft corp.
 */
public class TestClass {
    public static void main(String[] args) {


//        SessionFactory factory = new Configuration()
//                .configure()
//                .addResource("models/Item.hbm.xml")
//                .buildSessionFactory();


        Session session = UtilHibernate.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Item where car.id=:carId");
        query.setParameter("carId",2);
        query.list();
//        User user = new User();
//        user.setId(1);
//        user.setName("User 3");
//        user.setEmail("mymail@gmail.com");
//        session.save(user);

//        Item item = new Item();
//        item.setUser(user);
//        item.setDesc("Test");
//        session.save(item);


        session.getTransaction().commit();
        session.close();

    }
}
