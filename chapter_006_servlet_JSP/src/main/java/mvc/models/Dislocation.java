package mvc.models;

/**
 * Created by Ivan Sliusar on 20.02.2018.
 * Red Line Soft corp.
 */
public class Dislocation {
    /**
     * Id dislocation
     */
    int id;

    /**
     * Name dislocation
     */
    String name;

    public Dislocation(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
