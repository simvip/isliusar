package my.jpa.controllers;

import my.jpa.models.Car;
import my.jpa.models.FileImage;
import my.jpa.models.Item;
import my.jpa.models.parts.BaseBlock;
import my.jpa.models.parts.Engine;
import my.jpa.models.parts.GearBox;
import my.jpa.models.parts.Transmission;
import my.jpa.service.ItemRequestWrapper;
import my.jpa.service.ValidateFile;
import my.jpa.service.ValidateItem;
import my.jpa.utils.Crud;
import my.jpa.utils.RequestParameter;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Created by Ivan Sliusar on 31.08.2018.
 * Red Line Soft corp.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ItemServlet.class)
public class ItemServletTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    public ValidateItem itemService;
    @MockBean
    public ValidateFile fileService;

    @Test
    @WithMockUser(username = "user@gmail.com", roles = {"User"})
    public void whenFindAllThenGetAllItems() throws Exception {
        given(
                this.itemService.findAll()
        ).willReturn(
                new ArrayList<Item>(
                        Lists.newArrayList(
                                Item.builder()
                                        .id(1)
                                        .desc("My car")
                                        .build()
                        )
                )
        );
        this.mvc.perform(post(ItemServlet.ITEM_URI)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"command\":\"FIND_ALL\"}")
        )
                .andExpect(status().isOk())
                .andExpect(
                        content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                )
                .andExpect(jsonPath("$.size()").value(1))
                .andDo(print())
                .andReturn();

    }

    @Test
    @WithMockUser(username = "user@gmail.com", roles = {"User"})
    public void whenGetAllItemsInDiapasonThenReceivingFilterData() throws Exception {
        assertThat(this.itemService).isNotNull();
        given(
                this.itemService.findAllByFilter(ItemRequestWrapper.builder()
                        .command(Crud.FIND_ALL)
                        .parameters(
                                Lists.newArrayList(
                                        RequestParameter.builder()
                                                .key("sDate")
                                                .value("1532984400000")
                                                .build(),
                                        RequestParameter.builder()
                                                .key("eDate")
                                                .value("1536353999000")
                                                .build()
                                )
                        )
                        .build()
                )
        ).willReturn(
                new ArrayList<Item>(
                        Lists.newArrayList(
                                Item.builder()
                                        .id(1)
                                        .desc("My car")
                                        .build()
                        )
                )
        );
        // get all items in diapason from sDate to eDate
        this.mvc.perform(post(ItemServlet.ITEM_URI)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"command\":\"FIND_ALL\",\"parameters\":[{\"key\":\"sDate\",\"value\":\"1532984400000\"},{\"key\":\"eDate\",\"value\":\"1536353999000\"}]}")
        )
                .andExpect(status().isOk())
                .andExpect(
                        content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                )
                .andExpect(jsonPath("$.size()").value(1))
                .andDo(print())
                .andReturn();

    }

    @Test
    @WithMockUser(username = "user@gmail.com", roles = {"User"})
    public void whenAddItemsThenGetTrue() throws Exception {
        assertThat(this.itemService).isNotNull();
        given(
                this.itemService.add(Item.builder()
                        .id(2)
                        .desc("My car")
                        .build())
        ).willReturn(true);
        this.mvc.perform(post(ItemServlet.ITEM_URI)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"command\":\"CREATE_OR_UPDATE\",\"item\":{\"id\":\"2\",\"user\":{\"id\":\"3\"},\"car\":{\"id\":1},\"desc\":\"My car 333\",\"done\":\"true\",\"created\":\"2018-09-06T15:05:05.748Z\",\"coverPath\":\"/image/1_4Sep2018062859GMT_1536042539006.jpg\"}}")
        )
                .andExpect(status().isOk())
                .andExpect(
                        content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                )
                .andDo(print())
                .andReturn();

    }

    @Test
    @WithMockUser(username = "user@gmail.com", roles = {"User"})
    public void whenDeleteThenGetTrue() throws Exception {
        assertThat(this.itemService).isNotNull();
        given(
                this.itemService.delete(1)
        ).willReturn(true);
        this.mvc.perform(post(ItemServlet.ITEM_URI)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"command\":\"DELETE\",\"item\":{\"id\":\"1\"}}")
        )
                .andExpect(status().isOk())
                .andExpect(
                        content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                )
                .andDo(print())
                .andReturn();

    }

    @Test
    @WithMockUser(username = "user@gmail.com", roles = {"User"})
    public void whenGetByIdThenGetItemWithSameId() throws Exception {
        assertThat(this.itemService).isNotNull();
        given(
                this.itemService.findByID(1)
        ).willReturn(Item.builder()
                .id(1)
                .desc("MyCar")
                .build());
        this.mvc.perform(post(ItemServlet.ITEM_URI)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"command\":\"GET_BY_ID\",\"item\":{\"id\":\"1\"}}")
        )
                .andExpect(status().isOk())
                .andExpect(
                        content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                )
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.desc").value("MyCar"))
                .andDo(print())
                .andReturn();

    }

    @Test
    @WithMockUser(username = "user@gmail.com", roles = {"User"})
    public void whenGetDropdownListsThenWeGetAllInSeparateList() throws Exception {
        assertThat(this.itemService).isNotNull();
        Map<String, List<BaseBlock>> listMap = new HashMap<>();
        listMap.put("car",
                Lists.newArrayList(
                        Car.builder()
                                .id(1)
                                .name("myCar")
                                .build()
                ));
        listMap.put("engine",
                Lists.newArrayList(
                        Engine.builder()
                                .id(2)
                                .name("testEngine")
                                .build())
        );
        listMap.put("gearbox",
                Lists.newArrayList(
                        GearBox.builder()
                                .id(3)
                                .name("testGearBox")
                                .build())
        );
        listMap.put("transmission",
                Lists.newArrayList(
                        Transmission.builder()
                                .id(4)
                                .name("testTransmission")
                                .build())
        );
        given(
                this.itemService.getDopdownList()
        ).willReturn(
                listMap
        );
        this.mvc.perform(post(ItemServlet.ITEM_URI)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"command\":\"GET_DROPDOWN_LIST\"}")
        )
                .andExpect(status().isOk())
                .andExpect(
                        content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                )
                .andExpect(jsonPath("$.car[0].id").value(1))
                .andExpect(jsonPath("$.car[0].name").value("myCar"))
                .andExpect(jsonPath("$.engine[0].id").value(2))
                .andExpect(jsonPath("$.gearbox[0].id").value(3))
                .andExpect(jsonPath("$.transmission[0].id").value(4))
                .andDo(print())
                .andReturn();

    }

    @Test
    @WithMockUser(username = "user@gmail.com", roles = {"User"})
    public void whenGetAllImageByItemIdThenReceiveListOfImage() throws Exception {
        assertThat(this.fileService).isNotNull();
        given(
                this.fileService.findAll(1)
        ).willReturn(
                new ArrayList<FileImage>(
                        Lists.newArrayList(
                                FileImage.builder()
                                        .id(1)
                                        .itemId(1)
                                        .path("pathImage")
                                        .build()
                        )
                )
        );
        this.mvc.perform(post(ItemServlet.ITEM_URI)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"command\":\"GET_ALL_FILES\",\"item\":{\"id\":\"1\"}}")
        )
                .andExpect(status().isOk())
                .andExpect(
                        content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                )
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].path").value("pathImage"))
                .andDo(print())
                .andReturn();

    }
}
