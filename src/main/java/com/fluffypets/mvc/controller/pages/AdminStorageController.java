package com.fluffypets.mvc.controller.pages;

import com.fluffypets.dao.StorageDAO;
import com.fluffypets.dao.impl.DaoFactory;
import com.fluffypets.entities.GoodRecord;
import com.fluffypets.entities.enumes.ProductStatus;
import com.fluffypets.mvc.controller.Controller;
import com.fluffypets.mvc.servlets.Action;
import com.fluffypets.mvc.servlets.ViewModel;

import java.util.List;

public class AdminStorageController implements Controller {

    @Override
    public ViewModel process(Action action, ViewModel vm) {
        StorageDAO storageDAO=DaoFactory.getStorageDao();
        List<GoodRecord> goodRecords = storageDAO.getAllGoodRecords();
        vm.setAttribute("records", goodRecords);
        vm.setAttribute("statuses", ProductStatus.values());
        vm.setView("adminStorage");
        return vm;
    }
}