package com.fluffypets.dao;

import com.fluffypets.entities.GoodRecord;

import java.util.List;

public interface StorageDAO extends GenericDAO<GoodRecord> {
    List<GoodRecord> getAllGoodRecords();
}