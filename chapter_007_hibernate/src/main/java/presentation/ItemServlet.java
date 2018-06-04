package presentation;

import com.google.gson.Gson;
import models.Item;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.json.JSONObject;
import utils.UtilHibernate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.function.Function;


/**
 * Created by Ivan Sliusar on 24.04.2018.
 * Red Line Soft corp.
 */
public class ItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        // Handle ajax (JSON or XML) response.
        StringBuilder jb = new StringBuilder();
        String line = "";
        try {
            BufferedReader reader = req.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        JSONObject inputJSON = new JSONObject(jb.toString());

        int command = inputJSON.getInt("command");
        JSONObject response = new JSONObject();

        switch (command) {
            case 0: //get all items
                String s = new Gson().toJson(getAllItmems());
                resp.getWriter().write(s);
                break;
            case 1: //delete items by id

                response.put("answer",
                        deleteById(
                                inputJSON.getInt("itemId")
                        ));
                resp.getWriter().write(response.toString());
                break;
            case 2: // create or update
                Item item = new Item();
                if (inputJSON.has("id")&& !inputJSON.getString("id").isEmpty())
                    item.setId(inputJSON.getInt("id"));
                item.setDecs(inputJSON.getString("desc"));
                item.setCreated(new Timestamp(inputJSON.getLong("dateCreate")));
                item.setDone(Boolean.valueOf(inputJSON.getString("done")));
                response.put("answer",createAndUpdate(item));
                resp.getWriter().write(response.toString());
                break;
        }
    }

    private Collection<Item> getAllItmems() {
       return this.tx(session -> session.createQuery("from Item ").list());
    }

    private boolean deleteById(int id){
        return this.tx(session -> {
            final Query query = session.createQuery("delete from Item where id=:id");
            query.setInteger("id", id);
            query.executeUpdate();
            return true;
        });
    }

    private boolean createAndUpdate(Item item){
     return this.tx(session -> {
        session.saveOrUpdate(item);
         return true;
     });
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = UtilHibernate.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            return command.apply(session);
        } catch (final Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            tx.commit();
            session.close();
        }
        return null;
    }
}
