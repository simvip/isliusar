package my.jpa.controllers;

import my.jpa.models.FileImage;
import my.jpa.service.ValidateFile;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;

/**
 * Created by Ivan Sliusar on 11.06.2018.
 * Red Line Soft corp.
 */
@Controller
@RequestMapping(UploadServlet.UPLOAD_URI)
public class UploadServlet {
    public final static String UPLOAD_URI = "/upload";
    private static final Logger logger = Logger.getLogger(UploadServlet.class);
    @Value("${local.ImageStorage}")
    private String localImageStorage;
    /**
     * Instance validate layer.
     */
    @Autowired
    private ValidateFile logic;

    /**
     * Upload multiple file using Spring Controller
     */
    @RequestMapping(method = RequestMethod.POST)
    public
    @ResponseBody
    String uploadMultipleFileHandler(@RequestParam("itemId") String itemId,
                                     @RequestParam("file") MultipartFile[] files) {
        String message = "File upload";
        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            try {
                byte[] bytes = file.getBytes();

                // save file in directory
                File serverFile = new File(this.localImageStorage
                        + generateUniqueFileName(itemId)
                        + "."
                        + FilenameUtils.getExtension(file.getOriginalFilename()));

                FileUtils.writeByteArrayToFile(serverFile, file.getBytes());
                // save file in db
                FileImage image = new FileImage();
                image.setName(serverFile.getName());
                image.setPath(FilenameUtils.getFullPathNoEndSeparator(serverFile.getPath()));
                image.setItemId(Integer.valueOf(itemId));
                logic.add(image);

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