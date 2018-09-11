package my.jpa.controllers;

import my.jpa.models.User;
import my.jpa.service.UserRequestWrapper;
import my.jpa.service.ValidateUser;
import my.jpa.utils.ThrowInPresentation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

/**
 * Created by Ivan Sliusar on 23.05.2018.
 * Red Line Soft corp.
 */
@RestController
@RequestMapping(UserServlet.USER_URI)
public class UserServlet {
    @Autowired
    private ValidateUser logic;

    public static final String USER_URI = "/users";
    private static final Logger logger = Logger.getLogger(UserServlet.class);


    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<User> findAllUsers() throws ServletException, IOException {
        return logic.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HttpEntity userCRUD(@RequestBody UserRequestWrapper userRequestWrapper) throws ServletException, IOException {

        switch (userRequestWrapper.getCommand()) {
            case CREATE:
                logic.add(userRequestWrapper.getUser());
                break;
            case UPDATE:
                logic.update(userRequestWrapper.getUser());
                break;
            case DELETE:
                logic.delete(userRequestWrapper.getUser());
                break;
            default:
                logger.error(new ThrowInPresentation("Don`t recognize command"));
        }
        return new ResponseEntity<>(userRequestWrapper.getUser(), HttpStatus.OK);
    }

}