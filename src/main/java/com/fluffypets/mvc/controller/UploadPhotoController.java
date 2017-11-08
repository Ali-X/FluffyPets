package com.fluffypets.mvc.controller;

import com.fluffypets.exeptions.AccessException;
import com.fluffypets.factory.ContextFactory;
import com.fluffypets.mvc.servlets.Action;
import com.fluffypets.mvc.servlets.ViewModel;
import org.apache.commons.fileupload.FileItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

public class UploadPhotoController implements Controller {
    private static final Logger logger = LogManager.getLogger(UploadPhotoController.class.getName());
    private String UPLOAD_DIRECTORY;

    public UploadPhotoController() {
        Properties property = new Properties();

        try {
            property.load(getClass().getResourceAsStream("/config.properties"));
            UPLOAD_DIRECTORY = property.getProperty("image.folder");

        } catch (IOException e) {
            throw new AccessException(e.getMessage());
        }
    }

    @Override
    public synchronized ViewModel process(Action action, ViewModel vm) {
        String uniqueName = null;

        List<FileItem> multiparts = action.getItemsForUpload();
        for (FileItem item : multiparts) {
            if (!item.isFormField()) {
                String name = new File(item.getName()).getName();
                uniqueName = UUID.randomUUID().toString() + name;
                try {
                    item.write(new File(UPLOAD_DIRECTORY + File.separator + uniqueName));
                } catch (Exception e) {
                    logger.error("erreor of image upload of " + name);
                }
            }
        }
        vm.setAttribute("uploadedFile", "/file/" + uniqueName);
        logger.info(uniqueName + "was uploaded");
        return vm;
    }
}