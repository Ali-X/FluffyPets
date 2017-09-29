package com.fluffypets.mvc.model;

public class User {
  private Integer id;
  private String userName;
  private String password;
  private String token;
  private String email;
  private String RoleString;

    public User(Integer id, String userName, String password, String email, String roleString) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
        RoleString = roleString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (!getUserName().equals(user.getUserName())) return false;
        if (!getPassword().equals(user.getPassword())) return false;
        return getEmail().equals(user.getEmail());
    }

    @Override
    public int hashCode() {
        int result = getUserName().hashCode();
        result = 31 * result + getPassword().hashCode();
        result = 31 * result + getEmail().hashCode();
        return result;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRoleString(String roleString) {
        RoleString = roleString;
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

    public String getEmail() {
        return email;
    }

    public String getRoleString() {
        return RoleString;
    }

    public static User from(String userName, String password, String token) {
        return null;
    }
}
