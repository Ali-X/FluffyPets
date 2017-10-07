package com.fluffypets.mvc.servlets;

import com.fluffypets.entities.Category;
import com.fluffypets.entities.Product;
import com.fluffypets.entities.User;
import com.fluffypets.entities.enumes.Prices;
import com.fluffypets.mvc.page_objects.Cart;
import com.fluffypets.mvc.page_objects.HomePagePref;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class ViewModel implements Serializable {

    private String view;
    private Map<String, Object> attributes = new HashMap<>();
    private Locale currentLocale = new Locale("en", "US");

    public ViewModel() {
        localisation();
        User user = new User(0, "Unknown", "", "", "user");
        Cart cart = new Cart(user);
        this.setAttribute("cart", cart);
    }

    Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttribute(String attrName, Object value) {
        attributes.put(attrName, value);
    }

    public Object getAttribute(String attrName) {
        return attributes.get(attrName);
    }

    public Locale getCurrentLocale() {
        return currentLocale;
    }

    public Boolean isUkrLocale() {
        return this.currentLocale.equals(new Locale("uk", "UA"));
    }

    public void setCurrentLocale(Locale currentLocale) {
        this.currentLocale = currentLocale;
        localisation();
    }

    String getView() {
        return view;
    }

    public void removeAttribute(String attribute) {
        attributes.remove(attribute);
    }

    public void setView(String view) {
        this.view = view;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public static ViewModel of() {
        return new ViewModel();
    }

    public static String stringUTF8(String string) {
        try {
            return new String(string.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void localisation() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("language", this.getCurrentLocale());
        if (this.isUkrLocale()) {
            this.setAttribute("isUa", "true");
        } else {
            this.setAttribute("isUa", "false");
        }
        this.setAttribute("prices", Prices.values());
        this.setAttribute("Add_to_cart", ViewModel.stringUTF8(resourceBundle.getString("Add_to_cart")));
        this.setAttribute("Admin_page", ViewModel.stringUTF8(resourceBundle.getString("Admin_page")));
        this.setAttribute("Confirm_your_order", ViewModel.stringUTF8(resourceBundle.getString("Confirm_your_order")));
        this.setAttribute("Create_product", ViewModel.stringUTF8(resourceBundle.getString("Create_product")));
        this.setAttribute("Logout", ViewModel.stringUTF8(resourceBundle.getString("Logout")));
        this.setAttribute("My_cart", ViewModel.stringUTF8(resourceBundle.getString("My_cart")));
        this.setAttribute("My_profile", ViewModel.stringUTF8(resourceBundle.getString("My_profile")));
        this.setAttribute("Products", ViewModel.stringUTF8(resourceBundle.getString("Products")));
        this.setAttribute("Select_categories", ViewModel.stringUTF8(resourceBundle.getString("Select_categories")));
        this.setAttribute("Select_price_range", ViewModel.stringUTF8(resourceBundle.getString("Select_price_range")));
        this.setAttribute("Signin", ViewModel.stringUTF8(resourceBundle.getString("Signin")));
        this.setAttribute("Signup", ViewModel.stringUTF8(resourceBundle.getString("Signup")));
        this.setAttribute("Welcome", ViewModel.stringUTF8(resourceBundle.getString("Welcome")));
        this.setAttribute("message_L", ViewModel.stringUTF8(resourceBundle.getString("message_L")));
        this.setAttribute("All", ViewModel.stringUTF8(resourceBundle.getString("All")));
        this.setAttribute("Language", ViewModel.stringUTF8(resourceBundle.getString("Language")));
        this.setAttribute("Select", ViewModel.stringUTF8(resourceBundle.getString("Select")));
        this.setAttribute("OrderLabel", ViewModel.stringUTF8(resourceBundle.getString("OrderLabel")));
        this.setAttribute("IncreasePrice", ViewModel.stringUTF8(resourceBundle.getString("IncreasePrice")));
        this.setAttribute("DecreasePrice", ViewModel.stringUTF8(resourceBundle.getString("DecreasePrice")));
    }
}
