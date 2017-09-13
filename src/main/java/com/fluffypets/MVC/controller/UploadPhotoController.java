package com.fluffypets.MVC.controller;

import com.fluffypets.MVC.servlets.Request;
import com.fluffypets.MVC.servlets.ViewModel;
import com.fluffypets.factory.Factory;
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
    synchronized public ViewModel process(Request request) {
        ViewModel vm = Factory.getViewModel();
        String uniqueName=null;

        List<FileItem> multiparts = request.getItemsForUpload();
        for (FileItem item : multiparts) {
            if (!item.isFormField()) {
                String name = new File(item.getName()).getName();
                uniqueName= UUID.randomUUID().toString()+name;
                try {
                    item.write(new File(UPLOAD_DIRECTORY + File.separator +uniqueName));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        vm.setAttribute("uploadedFile","/file/"+uniqueName);
        logger.info(uniqueName+"was uploaded");
        return vm;
    }
}