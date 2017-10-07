package com.fluffypets.dao.impl;

import com.fluffypets.dao.AbstractDAO;
import com.fluffypets.dao.StorageDAO;
import com.fluffypets.entities.GoodRecord;
import com.fluffypets.exeptions.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StorageDAOImpl extends AbstractDAO<GoodRecord> implements StorageDAO {
    private static final Logger logger = LogManager.getLogger(StorageDAOImpl.class.getName());

    StorageDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    public List<GoodRecord> getAllGoodRecords() {
        PreparedStatement preparedStatement = null;
        List<GoodRecord> resSetCont;
        try {
            connection.setAutoCommit(false);
            String preparedQuery = "SELECT * FROM Pets.storage";
            preparedStatement = connection.prepareStatement(preparedQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            resSetCont = parseResultSet(resultSet);
            connection.commit();
            logger.info("get GoodRecords");
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                logger.error(e.getLocalizedMessage());
            }
            throw new DAOException("There are problems with getting GoodRecords from DB " + e.getLocalizedMessage());
        } finally {
            closeStatement(preparedStatement, logger);
        }
        return resSetCont;
    }

    @Override
    public GoodRecord create(GoodRecord goodRecord) {
        PreparedStatement preparedStatement = null;
        try {
            connection.setAutoCommit(false);
            String preparedQuery = "INSERT INTO Pets.storage (productId, avaliableHere, reservedHere, status) VALUES(?,?,?,?)";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setLong(1, goodRecord.getProductId());
            preparedStatement.setLong(2, goodRecord.getAvailableHere());
            preparedStatement.setInt(3, goodRecord.getReservedHere());
            preparedStatement.setString(4, goodRecord.getStatus());
            preparedStatement.execute();
            connection.commit();
            logger.info("create GoodRecord");
            return get(goodRecord);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                logger.error(e.getLocalizedMessage());
            }
            throw new DAOException("There are problems with new GoodRecord insertion to DB " + e.getLocalizedMessage());
        } finally {
            closeStatement(preparedStatement, logger);
        }
    }

    @Override
    public GoodRecord delete(GoodRecord goodRecord) {
        PreparedStatement preparedStatement = null;
        try {
            connection.setAutoCommit(false);
            String preparedQuery = "DELETE FROM Pets.storage  WHERE id=?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setLong(1, goodRecord.getId());
            preparedStatement.execute();
            connection.commit();
            logger.info("delete GoodRecord");
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                logger.error(e.getLocalizedMessage());
            }
            throw new DAOException("There are problems with GoodRecord deleting from DB " + e.getLocalizedMessage());
        } finally {
            closeStatement(preparedStatement, logger);
        }
        return goodRecord;
    }

    @Override
    public GoodRecord update(GoodRecord goodRecord) {
        PreparedStatement preparedStatement = null;
        try {
            connection.setAutoCommit(false);
            String preparedQuery = "UPDATE Pets.storage SET  productId= ?," +
                    "avaliableHere = ?, reservedHere = ?, status = ? WHERE id =?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setLong(1, goodRecord.getProductId());
            preparedStatement.setLong(2, goodRecord.getAvailableHere());
            preparedStatement.setInt(3, goodRecord.getReservedHere());
            preparedStatement.setString(4, goodRecord.getStatus());
            preparedStatement.setLong(5, goodRecord.getId());
            preparedStatement.execute();
            connection.commit();
            logger.info("update GoodRecord");
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                logger.error(e.getLocalizedMessage());
            }
            throw new DAOException("There are problems with GoodRecord update in DB " + e.getLocalizedMessage());
        } finally {
            closeStatement(preparedStatement, logger);
        }
        return goodRecord;
    }

    @Override
    public GoodRecord get(GoodRecord goodRecord) {
        PreparedStatement preparedStatement = null;
        try {
            connection.setAutoCommit(false);
            String preparedQuery = "SELECT * FROM Pets.storage WHERE productId = ? AND avaliableHere = ? " +
                    "AND reservedHere = ?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setLong(1, goodRecord.getProductId());
            preparedStatement.setLong(2, goodRecord.getAvailableHere());
            preparedStatement.setLong(3, goodRecord.getReservedHere());
            ResultSet resultSet = preparedStatement.executeQuery();
            List<GoodRecord> resSetCont = parseResultSet(resultSet);
            if (resSetCont.size() == 0) {
                goodRecord = null;
            } else {
                goodRecord = resSetCont.get(0);
            }
            connection.commit();
            logger.info("get GoodRecord");
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                logger.error(e.getLocalizedMessage());
            }
            throw new DAOException("There are problems with getting GoodRecord from DB " + e.getLocalizedMessage());
        } finally {
            closeStatement(preparedStatement, logger);
        }
        return goodRecord;
    }

    @Override
    public GoodRecord findById(Integer id) {
        PreparedStatement preparedStatement = null;
        GoodRecord goodRecord;
        try {
            connection.setAutoCommit(false);
            String preparedQuery = "SELECT * FROM Pets.storage WHERE id = ?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<GoodRecord> resSetCont = parseResultSet(resultSet);
            if (resSetCont.size() == 0) {
                goodRecord = null;
            } else {
                goodRecord = resSetCont.get(0);
            }
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                logger.error(e.getLocalizedMessage());
            }
            throw new DAOException("There are problems searching GoodRecord by id " + e.getLocalizedMessage());
        } finally {
            closeStatement(preparedStatement, logger);
        }
        return goodRecord;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DAOException(e.getLocalizedMessage());
        }

    }

    @Override
    public List<GoodRecord> parseResultSet(ResultSet resultSet) {
        List<GoodRecord> goodRecords = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                Integer productId = resultSet.getInt("productId");
                Integer availableHere = resultSet.getInt("avaliableHere");
                Integer reservedHere = resultSet.getInt("reservedHere");
                String status = resultSet.getString("status");
                goodRecords.add(new GoodRecord(id, productId, availableHere, reservedHere, status));
            }
        } catch (SQLException e) {
            throw new DAOException(e.getLocalizedMessage());
        }
        return goodRecords;
    }
}
