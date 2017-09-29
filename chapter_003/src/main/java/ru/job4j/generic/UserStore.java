package ru.job4j.generic;

/**
 * Created by Ivan Sliusar on 18.09.2017.
 * Red Line Soft corp.
 */
public class UserStore extends AbstractStore<User> {
    /**
     * Construct.
     *
     * @param sizeStorage int
     */
    public UserStore(int sizeStorage) {
        super(sizeStorage);
    }
}
