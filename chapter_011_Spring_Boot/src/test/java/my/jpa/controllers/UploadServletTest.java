package my.jpa.controllers;

import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Ivan Sliusar on 11.09.2018.
 * Red Line Soft corp.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(UploadServlet.class)
public class UploadServletTest {
    @Autowired
    private MockMvc mvc;

    @Test
    @WithMockUser(username = "user@gmail.com", roles = {"User"})
    public void uploadMultipleFileHandler() throws Exception {
        MockMultipartFile multipartFile = new MockMultipartFile("file", "test.txt",
                "text/plain", "Spring Framework".getBytes());
        this.mvc.perform(fileUpload(UploadServlet.UPLOAD_URI)
                .file(multipartFile)
                .param("itemId", "1")
                .param("file",
                        String.valueOf(new ArrayList<MockMultipartFile>(
                                        Lists.newArrayList(
                                                multipartFile
                                        )
                                )
                        )
                )
        )
                .andDo(print())
                .andExpect(content().string(containsString("File upload")))
                .andExpect(status().isOk());
    }
}