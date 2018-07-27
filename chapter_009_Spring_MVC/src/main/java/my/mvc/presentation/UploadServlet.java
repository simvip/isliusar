package my.mvc.presentation;

import my.mvc.logic.ValidateFile;
import my.mvc.models.FileImage;
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
    private static final ValidateFile LOGIC = ValidateFile.getInstance();

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
                String appPath = context.getRealPath("");
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

//    /**
//     * Instance validate layer.
//     */
//    private static final ValidateFile LOGIC = ValidateFile.getInstance();
//    /**
//     * Name of the directory where uploaded files will be saved, relative to
//     * the web application directory.
//     */
//    private static final String SAVE_DIR = "uploadFiles";
//
//    /**
//     * Max buffer size in byte.
//     */
//    private static final Integer MAX_BUFFER = 1024 * 1024;
//
//    /**
//     * Max file size in byte
//     */
//    private static final Integer MAX_FILE_SIZE = 1024 * 1024 * 10;
//
//    /**
//     * Random for auto-generate file name/
//     */
//    private Random random = new Random();
//
//    /**
//     * handles file upload
//     */
//    @RequestMapping(value="/upload",method = RequestMethod.POST)
//    protected String doPost(MultipartHttpServletRequest req) throws ServletException, IOException {
//        // checking whether it is query multipart/fom-data
//        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
//        if (!isMultipart) {
//            logger.warn(HttpServletResponse.SC_BAD_REQUEST);
//            return "index";
//        }
//
//        DiskFileItemFactory factory = new DiskFileItemFactory();
//        factory.setSizeThreshold(MAX_BUFFER);
//
//        File tempDir = (File) this.context.getAttribute("javax.servlet.context.tempdir");
//        factory.setRepository(tempDir);
//
//        ServletFileUpload upload = new ServletFileUpload(factory);
//        upload.setFileSizeMax(MAX_FILE_SIZE);
//
//        List<FileItem> listFiles = new ArrayList<>();
//        Integer itemId = null;
//        try {
//            List items = upload.parseRequest(req);
//            Iterator iter = items.iterator();
//
//            while (iter.hasNext()) {
//                FileItem item = (FileItem) iter.next();
//
//                if (item.isFormField()) {
//                    String nameFild = item.getFieldName();
//                    if (nameFild.equals("itemId")) {
//                        itemId = Integer.valueOf(item.getString());
//                    }
//                } else {
//                    listFiles.add(item);
//                }
//            }
//        } catch (Exception ex) {
//            logger.error(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,ex);
//            return "index";
//        }
//
//        for (FileItem item :
//                listFiles) {
//            try {
//                processUploadedFile(itemId, item);
//            } catch (Exception ex) {
//                logger.error("Problem with",ex);
//            }
//        }
//        return "index";
//    }
//
//
//    private void processUploadedFile(Integer intemId, FileItem item) throws Exception {
//        File uploadetFile = null;
//        // gets absolute path of the web application
//        String appPath = this.context.getRealPath("");
//        // constructs path of the directory to save uploaded file
//        String savePath = appPath + File.separator + SAVE_DIR + File.separator;
//
//        do {
//            String path = savePath + random.nextInt() + item.getName();
//            uploadetFile = new File(path);
//        } while (uploadetFile.exists());
//
//        //create file
//        uploadetFile.createNewFile();
//        //save data in to file
//        item.write(uploadetFile);
//        //save info about file in DB
//        FileImage image = new FileImage();
//        image.setName(uploadetFile.getName());
//        image.setPath(savePath);
//        image.setItemId(intemId);
//        LOGIC.add(image);
//    }

}