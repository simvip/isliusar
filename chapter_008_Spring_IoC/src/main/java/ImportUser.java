import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import springtest.db.UserStorage;
import springtest.models.User;

/**
 * Created by Ivan Sliusar on 06.07.2018.
 * Red Line Soft corp.
 */
public class ImportUser {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        UserStorage store = context.getBean(UserStorage.class);

        User user = new User();
        user.setName("Piter");
        store.add(user);

        System.out.println(""+store.findById(1));


    }
}
