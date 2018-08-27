package my.jpa.presentation;

import my.jpa.service.ValidateFile;
import my.jpa.models.FileImage;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.util.Date;

/**
 * Created by Ivan Sliusar on 11.06.2018.
 * Red Line Soft corp.
 */
@Controller
public class UploadServlet {
    @Autowired
    ServletContext context;
    /**
     * Instance validate layer.
     */
    @Autowired
    private ValidateFile LOGIC;
    private static final Logger logger = Logger.getLogger(UploadServlet.class);

    /**
     * Name of the directory where uploaded files will be saved, relative to
     * the web application directory.
     */
    private static final String SAVE_DIR = "uploadFiles";

    /**
     * Upload multiple file using Spring Controller
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public
    @ResponseBody
    String uploadMultipleFileHandler(@RequestParam("itemId") String itemId,
                                     @RequestParam("file") MultipartFile[] files) {
        String message = "File upload";
        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            try {
                byte[] bytes = file.getBytes();
                String appPath = context.getRealPath("/WEB-INF/classes/static");
                // save file in directory
                File serverFile = new File(appPath
                        + File.separator
                        + SAVE_DIR
                        + File.separator
                        + generateUniqueFileName(itemId)
                        + "."
                        + FilenameUtils.getExtension(file.getOriginalFilename()));

                FileUtils.writeByteArrayToFile(serverFile, file.getBytes());
                // save file in db
                FileImage image = new FileImage();
                image.setName(serverFile.getName());
                image.setPath(FilenameUtils.getFullPathNoEndSeparator(serverFile.getPath()));
                image.setItemId(Integer.valueOf(itemId));
                LOGIC.add(image);

                logger.info("Server File Location="
                        + serverFile.getAbsolutePath());

            } catch (Exception e) {
                return "You failed to upload " + itemId + " => " + e.getMessage();
            }
        }
        return message;
    }

    private String generateUniqueFileName(String prefix) {
        String filename = "";
        long millis = System.currentTimeMillis();
        String datetime = new Date().toGMTString();
        datetime = datetime.replace(" ", "");
        datetime = datetime.replace(":", "");

        filename = prefix + "_" + datetime + "_" + millis;
        return filename;
    }
}