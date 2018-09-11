package my.jpa.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Created by Ivan Sliusar on 31.08.2018.
 * Red Line Soft corp.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
public class ServletIntegrationTest {
    /**
     * Autowired MockMvc.
     */
    @Autowired
    public MockMvc mvc;

    /**
     * Testing CRUD operation by User controller.
     *
     * @throws Exception
     */
    @Test
    @WithMockUser(username = "user@gmail.com", roles = {"User"})
    public void testUserController() throws Exception {

        // Test Create
        this.mvc.perform(
                post(UserServlet.USER_URI)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content("{\"user\":{\"id\":\"1\",\"login\":\"TestUser\",\"email\":\"test@gmail.com\",\"password\":\"vfitymrf\",\"createDate\":\"null\"},\"command\":\"CREATE\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(
                        content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                )
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.login").value("TestUser"))
                .andReturn();
        // Get all
        this.mvc.perform(get(UserServlet.USER_URI).accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(
                        content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                )
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].login").value("TestUser"))
                .andDo(print())
                .andReturn();
        //Test UPDATE
        this.mvc.perform(
                post(UserServlet.USER_URI)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content("{\"user\":{\"id\":1,\"login\":\"UpdateUser\"},\"command\":\"UPDATE\"}")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(
                        content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)

                )
                .andExpect(jsonPath("$.login").value("UpdateUser"))
                .andReturn();

        //Test DELETE
        this.mvc.perform(
                post(UserServlet.USER_URI)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content("{\"user\":{\"id\":1},\"command\":\"DELETE\"}")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        this.mvc.perform(get(UserServlet.USER_URI).accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(
                        content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                )
                .andExpect(jsonPath("$.id").doesNotExist())
                .andDo(print())
                .andReturn();
    }

}
