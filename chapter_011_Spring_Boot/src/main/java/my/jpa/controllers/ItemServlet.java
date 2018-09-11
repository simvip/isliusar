package my.jpa.controllers;

import my.jpa.models.Item;
import my.jpa.service.ItemRequestWrapper;
import my.jpa.service.ValidateFile;
import my.jpa.service.ValidateItem;
import my.jpa.utils.Crud;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;


/**
 * Created by Ivan Sliusar on 24.04.2018.
 * Red Line Soft corp.
 */
@RestController
@RequestMapping(ItemServlet.ITEM_URI)
public class ItemServlet {
    @Autowired
    private ValidateItem logicItem;
    @Autowired
    private ValidateFile logicFile;
    public final static String ITEM_URI = "/items";
    private final static Logger logger = Logger.getLogger(ItemServlet.class);

    private Map<Crud, Function<ItemRequestWrapper, HttpEntity>> dispatch = new HashMap<>();

    public ItemServlet() {
        this.dispatch.put(
                Crud.FIND_ALL,
                findAll()
        );
        this.dispatch.put(
                Crud.DELETE,
                delete()
        );
        this.dispatch.put(
                Crud.CREATE_OR_UPDATE,
                createOrUpdate()
        );
        this.dispatch.put(
                Crud.GET_DROPDOWN_LIST,
                getDorpdownList()
        );
        this.dispatch.put(
                Crud.GET_BY_ID,
                getById()
        );
        this.dispatch.put(
                Crud.GET_ALL_FILES,
                getAllFiles()
        );
    }

    public HttpEntity handleRequest(final ItemRequestWrapper requestWrapper) {
        Crud command = requestWrapper.getCommand();
        if (this.dispatch.containsKey(command)) {
            return this.dispatch.get(
                    command
            ).apply(requestWrapper);
        } else {
            logger.error("Command not find");
            return HttpEntity.EMPTY;
        }
    }

    /**
     * Find all and find all by parameters.
     *
     * @return HttpEntity.
     */
    private Function<ItemRequestWrapper, HttpEntity> findAll() {
        return requestWrapper -> {
            logger.info("Find items");
            List<Item> all = null;
            if (requestWrapper.getParameters() != null) {
                all = logicItem.findAllByFilter(requestWrapper);
            } else {
                all = logicItem.findAll();
            }
            return new ResponseEntity<>(all, HttpStatus.OK);
        };
    }

    /**
     * Delete Item by id.
     *
     * @return HttpEntity
     */
    private Function<ItemRequestWrapper, HttpEntity> delete() {
        return requestWrapper -> {
            logger.info("Delete item by id:" + requestWrapper.getItem().getId());
            return new ResponseEntity<>(logicItem.delete(requestWrapper.getItem().getId()), HttpStatus.OK);
        };
    }

    /**
     * Create or update item.
     *
     * @return HttpEntity
     */
    private Function<ItemRequestWrapper, HttpEntity> createOrUpdate() {
        return requestWrapper -> {
            logger.info("Create or update item with id=" + requestWrapper.getItem().getId());
            logicItem.add(requestWrapper.getItem());
            return new ResponseEntity<>(requestWrapper.getItem(), HttpStatus.OK);
        };
    }

    /**
     * Get all dropdown list.
     *
     * @return HttpEntity
     */
    private Function<ItemRequestWrapper, HttpEntity> getDorpdownList() {
        return requestWrapper -> {
            logger.info("Get dropdown list");
            return new ResponseEntity<>(logicItem.getDopdownList(), HttpStatus.OK);
        };
    }

    /**
     * Get item by id.
     * @return HttpEntity
     */
    private Function<ItemRequestWrapper, HttpEntity> getById() {
        return requestWrapper -> {
            logger.info("Get by id=" + requestWrapper.getItem().getId());
            return new ResponseEntity<>(logicItem.findByID(requestWrapper.getItem().getId()), HttpStatus.OK);
        };
    }

    /**
     * Get all files by current item.
     * @return HttpEntity
     */
    private Function<ItemRequestWrapper, HttpEntity> getAllFiles() {
        return wrappersParameters -> {
            logger.info("Get all items");
            return new ResponseEntity<>(logicFile.findAll(wrappersParameters.getItem().getId()), HttpStatus.OK);
        };
    }

    /**
     * Handler post requests.
     * @param  requestWrapper
     * @return HttpEntity
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public HttpEntity doPost(@RequestBody ItemRequestWrapper requestWrapper) throws ServletException, IOException {
        return handleRequest(requestWrapper);
    }
}




