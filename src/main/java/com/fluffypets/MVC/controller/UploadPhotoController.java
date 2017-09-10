package com.fluffypets.MVC.controller;

import com.fluffypets.MVC.model.Product;
import com.fluffypets.MVC.servlets.Request;
import com.fluffypets.MVC.servlets.ViewModel;
import com.fluffypets.factory.Factory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class UploadPhotoController implements Controller {
    private static final Logger logger = LogManager.getLogger(UploadPhotoController.class.getName());

    @Override
    public ViewModel process(Request request) {
        ViewModel vm = Factory.getViewModel();

        final String[] productName = {null};
        request.getItemsForUpload().forEach(item -> {
            if (item.isFormField()) {
                productName[0] = item.getString();
            }
            if (!item.isFormField()) {
                String fileName = productName[0] + ".jpg";
                String filePath = request.getAttribute("file-upload") + fileName;
                try {
                    item.write(new File(filePath));
                } catch (Exception e) {
                    throw new RuntimeException("There was problem with uploading file " + e);
                }
            }
        });

        Product product=(Product) vm.getAttribute("product");
//        product.setPictureURL();
        return vm;
    }
}