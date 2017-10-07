package com.fluffypets.mvc.controller.post;

import com.fluffypets.dao.StorageDAO;
import com.fluffypets.dao.impl.DaoFactory;
import com.fluffypets.entities.GoodRecord;
import com.fluffypets.mvc.controller.Controller;
import com.fluffypets.mvc.servlets.Action;
import com.fluffypets.mvc.servlets.ViewModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StorageEditController implements Controller {
    private static final Logger logger = LogManager.getLogger(StorageEditController.class.getName());

    @Override
    public ViewModel process(Action request, ViewModel vm) {
        String command = request.getAttribute("command");
        String status = request.getAttribute("status");
        Integer id = new Integer(request.getAttribute("recordId"));
        Integer available = new Integer(request.getAttribute("available"));
        Integer reserved = new Integer(request.getAttribute("reserved"));
        StorageDAO storageDAO= DaoFactory.getStorageDao();
        GoodRecord goodRecord=storageDAO.findById(id);
        goodRecord.setAvailableHere(available);
        goodRecord.setReservedHere(reserved);
        goodRecord.setStatus(status);

        if (command.equals("update")) {
            storageDAO.update(goodRecord);
            vm.setAttribute("records", storageDAO.getAllGoodRecords());
        }

        vm.setView("adminStorage");
        logger.info("changed status for orderId");
        return vm;
    }}