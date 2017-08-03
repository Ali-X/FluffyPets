package com.fluffypets.MVC.model;

public class User {
  private Integer id;
  private String userName;
  private String password;
  private String token;

  public User(Integer id, String userName, String password, String token) {
    this.id = id;
    this.userName = userName;
    this.password = password;
    this.token = token;
  }

  public static User from(String userName, String password, String token) {
    return new User(null, userName, password, null);
  }

  public Integer getId() {
    return id;
  }

  public String getUserName() {
    return userName;
  }

  public String getPassword() {
    return password;
  }

  public String getToken() {
    return token;
  }

  public static User from(int id, String username) {
    return new User(id, username, null, null);
  }
}
