package nonblockingalgoritm;

import net.jcip.annotations.ThreadSafe;

/**
 * Created by Ivan Sliusar on 16.11.2017.
 * Red Line Soft corp.
 */
@ThreadSafe
public class Model {

    /**
     * ID
     */
    private int id;

    /**
     * Version.
     */
    private int version = 0;

    /**
     * Model name
     */
    private String name;

    /**
     * Construct.
     *
     * @param name String.
     */
    public Model(int id, String name) {
        this.name = name;
        this.id = id;
    }

    /**
     * Override equals.
     *
     * @param o Object
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Model model = (Model) o;

        return id == model.id;

    }

    /**
     * Override HashCode
     *
     * @return int
     */
    @Override
    public int hashCode() {
        return id;
    }

    /**
     * Getter version.
     *
     * @return int
     */
    public int getVersion() {
        return version;
    }


    /**
     * Getter name.
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Setter name.
     *
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Increment version
     */
    public void incrementVersion() {
        this.version++;
    }

    @Override
    public String toString() {
        return "Model{" + "version=" + version + ", name='" + name + '\'' + '}';
    }
}
