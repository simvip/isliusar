package mvc.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan Sliusar on 19.01.2018.
 * Red Line Soft corp.
 */
public enum Role {
    ADMIN,
    USER,
    UPPERUSER;

    static public List<Role> getAllRole() {
        ArrayList<Role> roles = new ArrayList<>();
        for (Role role :
                Role.values()) {
            roles.add(role);
        }
        return roles;
    }

    public String getName(){
        return this.name();
    }

}
