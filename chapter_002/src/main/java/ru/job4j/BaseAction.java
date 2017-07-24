package ru.job4j;


/**
 * Created by Admin on 09.07.2017.
 */
public abstract class BaseAction implements UserAction {
    /**
     * key.
     */
    private String key;
    /**
     * name.
     */
    private String name;

    /**
     * construct.
     *
     * @param key  String
     * @param name string
     */
    public BaseAction(String key, String name) {
        this.key = key;
        this.name = name;
    }

    /**
     * gen key.
     *
     * @return
     */
    @Override
    public int key() {
        return Integer.parseInt(this.key);
    }

    /**
     * execute.
     *
     * @param input   Input
     * @param tracker Tracker
     */
    @Override
    public abstract void execute(Input input, Tracker tracker);

    /**
     * info.
     *
     * @return String
     */
    @Override
    public String info() {
        return String.format("%s. %s", this.key, this.name);
    }
}
