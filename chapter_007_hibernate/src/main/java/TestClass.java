import models.parts.Transmission;
import org.hibernate.Session;
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

        Transmission transmission = new Transmission();
        transmission.setName("2-Speed Direct Shift");
        session.save(transmission);

        Transmission transmission2 = new Transmission();
        transmission2.setName("3-Speed Direct Shift");
        session.save(transmission2);


        session.getTransaction().commit();
        session.close();

    }
}
