package com.fluffypets.factory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.flywaydb.core.Flyway;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Enumeration;

public class ContextFactory {
    private static final Logger logger = LogManager.getLogger(ContextFactory.class.getName());

    public static Connection getConnection() {
        return getContextConnection();
    }

    private ContextFactory() {
    }

    public static String getEmail() {
        try {
            Context ctx = new InitialContext();
            Context initCtx = (Context) ctx.lookup("java:/comp/env");
            return (String) initCtx.lookup("userName");
        } catch (NamingException e) {
            logger.error("error in getting email and password from context \n" + e);
        }
        return null;
    }

    public static String getPassword() {
        try {
            Context ctx = new InitialContext();
            Context initCtx = (Context) ctx.lookup("java:/comp/env");
            return (String) initCtx.lookup("password");
        } catch (NamingException e) {
            logger.error("error in getting email and password from context \n" + e);
        }
        return null;
    }

    public static Connection getContextConnection() {
        try {
            Context ctx = new InitialContext();
            Context initCtx = (Context) ctx.lookup("java:/comp/env");
            DataSource ds = (DataSource) initCtx.lookup("jdbc/Pets");
            return ds.getConnection();
        } catch (NamingException | SQLException e) {
            logger.error("get connection from TomCat error\n" + e);
            throw new RuntimeException("get connection from TomCat error");
        }
    }

    public static void migrate() {
        try {
            Context ctx = new InitialContext();
            Context initCtx = (Context) ctx.lookup("java:/comp/env");
            DataSource ds = (DataSource) initCtx.lookup("jdbc/Pets");
            Flyway flyway = new Flyway();
            flyway.setDataSource(ds);
            flyway.setLocations("/flyway");
//            flyway.setBaseDir("src/main/flyway");
            flyway.setEncoding("UTF-8");
//            flyway.validate();
            flyway.migrate();
        } catch (NamingException e) {
            logger.error("get connection from TomCat error\n" + e);
            throw new RuntimeException("get connection from TomCat error");
        }
    }

    public static String getIp() {
        String ip = null;
        try {
            Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();  // gets All networkInterfaces of your device
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface inet = (NetworkInterface) networkInterfaces.nextElement();
                Enumeration address = inet.getInetAddresses();
                while (address.hasMoreElements()) {
                    InetAddress inetAddress = (InetAddress) address.nextElement();
                    if (inetAddress.isSiteLocalAddress()) {
                        ip = inetAddress.getHostAddress(); /// gives ip address of your device
                    }
                }
            }
            return ip;
        } catch (Exception e) {
            logger.error("ip finding error");
        }
        return null;
    }

    public static String md5Custom(String st, Logger logger) {
        MessageDigest messageDigest;
        byte[] digest = new byte[0];

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(st.getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            logger.error("MD5 error " + e.getLocalizedMessage());
        }

        BigInteger bigInt = new BigInteger(1, digest);
        StringBuilder md5Hex = new StringBuilder(bigInt.toString(16));

        while (md5Hex.length() < 32) {
            md5Hex.insert(0, "0");
        }

        return md5Hex.toString();
    }
}
