package com.fluffypets.mvc.controller;

import com.fluffypets.mvc.servlets.Action;
import com.fluffypets.mvc.servlets.ViewModel;
import org.apache.commons.fileupload.FileItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.List;
import java.util.UUID;

public class UploadPhotoController implements Controller {
    private static final Logger logger = LogManager.getLogger(UploadPhotoController.class.getName());
    private static final String UPLOAD_DIRECTORY = "/home/matsishin/FluffyPetsImages";

    @Override
    public synchronized  ViewModel process(Action action, ViewModel vm) {
        String uniqueName = null;

        List<FileItem> multiparts = action.getItemsForUpload();
        for (FileItem item : multiparts) {
            if (!item.isFormField()) {
                String name = new File(item.getName()).getName();
                uniqueName = UUID.randomUUID().toString() + name;
                try {
                    item.write(new File(UPLOAD_DIRECTORY + File.separator + uniqueName));
                } catch (Exception e) {
                    logger.error("erreor of image upload of "+name);
                }
            }
        }
        vm.setAttribute("uploadedFile", "/file/" + uniqueName);
        logger.info(uniqueName + "was uploaded");
        return vm;
    }
}