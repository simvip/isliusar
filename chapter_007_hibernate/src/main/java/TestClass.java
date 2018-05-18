import models.Car;
import models.parts.Engine;
import models.parts.GearBox;
import models.parts.Transmission;
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

        Transmission transmission = new Transmission();
        transmission.setName("6-Speed Direct Shift");
        session.save(transmission);

        Transmission transmission2 = new Transmission();
        transmission2.setName("5-Speed Direct Shift");
        session.save(transmission2);

        Transmission transmission3 = new Transmission();
        transmission3.setName("Automatic Transmission");
        session.save(transmission3);

        GearBox gearBox = new GearBox();
        gearBox.setName("Manual");
        session.save(gearBox);

        GearBox gearBox1 = new GearBox();
        gearBox1.setName("Auto");
        session.save(gearBox1);

        Car car1 = new Car("VW Caddy",new Engine(1),new Transmission(1),new GearBox(1));
        session.save(car1);

        Car car2 = new Car("BMW X3",new Engine(2),new Transmission(2),new GearBox(2));
        session.save(car2);

        session.getTransaction().commit();
        session.close();
        factory.close();
    }
}
