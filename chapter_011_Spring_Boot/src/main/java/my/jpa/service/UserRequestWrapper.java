package my.jpa.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import my.jpa.models.User;
import my.jpa.utils.Crud;

/**
 * Created by Ivan Sliusar on 06.09.2018.
 * Red Line Soft corp.
 * Utility transport class.
 * Contains the user and the operation to be performed in relation to him.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserRequestWrapper{
    /**
     * Command for controller. On of CURUD operation.
     */
    public Crud command;
    /**
     * Construct user from RequestBody.
     */
    private User user;

}
