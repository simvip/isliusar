package ru.job4j.array;

/**
 *Class Rotate.
 *
 *@author i.sliusar
 *@version $Id$
 *@since 0.1
 *13.06.2017
 */
public class TestTask {
    /**
     * back.
     *
     * @param origin - String find.
     * @param sub - String What find.
     * @return array -int[].
     */
    public boolean contains(String origin, String sub) {
        char[] originMass = origin.toCharArray();
        char[] subMass = sub.toCharArray();
        boolean contains = false;

        for (int i = 0; i < originMass.length; i++) {
            if (subMass[0] == originMass[i]) {
                for (int j = 1; j < subMass.length; j++) {
                    if ((i + j) <= originMass.length) {
                        if (subMass[j] == originMass[i + j]) {
                            contains = true;
                        } else {
                            contains = false;
                            break;
                        }
                    } else {
                        contains = false;
                        break;
                    }
                }
                if (contains) {
                    break;
                }
            }
        }
       return contains;
    }
}
