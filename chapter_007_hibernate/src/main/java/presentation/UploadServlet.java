package presentation;

import logic.ValidateFile;
import models.FileImage;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by Ivan Sliusar on 11.06.2018.
 * Red Line Soft corp.
 */
public class UploadServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(UploadServlet.class);
    /**
     * Instance validate layer.
     */
    private static final ValidateFile LOGIC = ValidateFile.getInstance();
    /**
     * Name of the directory where uploaded files will be saved, relative to
     * the web application directory.
     */
    private static final String SAVE_DIR = "uploadFiles";

    /**
     * Max buffer size in byte.
     */
    private static final Integer MAX_BUFFER = 1024 * 1024;

    /**
     * Max file size in byte
     */
    private static final Integer MAX_FILE_SIZE = 1024 * 1024 * 10;

    /**
     * Random for auto-generate file name/
     */
    private Random random = new Random();

    /**
     * handles file upload
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // checking whether it is query multipart/fom-data
        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        if (!isMultipart) {
            logger.warn(HttpServletResponse.SC_BAD_REQUEST);
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(MAX_BUFFER);

        File tempDir = (File) getServletContext().getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(tempDir);

        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(MAX_FILE_SIZE);

        List<FileItem> listFiles = new ArrayList<>();
        Integer itemId = null;
        try {
            List items = upload.parseRequest(req);
            Iterator iter = items.iterator();

            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();

                if (item.isFormField()) {
                    String nameFild = item.getFieldName();
                    if (nameFild.equals("itemId")) {
                        itemId = Integer.valueOf(item.getString());
                    }
                } else {
                    listFiles.add(item);
                }
            }
        } catch (Exception ex) {
            logger.error(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,ex);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        for (FileItem item :
                listFiles) {
            try {
                processUploadedFile(itemId, item);
            } catch (Exception ex) {
                logger.error("Problem with",ex);
            }
        }
    }

    private void processUploadedFile(Integer intemId, FileItem item) throws Exception {
        File uploadetFile = null;
        // gets absolute path of the web application
        String appPath = getServletContext().getRealPath("");
        // constructs path of the directory to save uploaded file
        String savePath = appPath + File.separator + SAVE_DIR + File.separator;

        do {
            String path = savePath + random.nextInt() + item.getName();
            uploadetFile = new File(path);
        } while (uploadetFile.exists());

        //create file
        uploadetFile.createNewFile();
        //save data in to file
        item.write(uploadetFile);
        //save info about file in DB
        FileImage image = new FileImage();
        image.setName(uploadetFile.getName());
        image.setPath(savePath);
        image.setItemId(intemId);
        LOGIC.add(image);
    }
}