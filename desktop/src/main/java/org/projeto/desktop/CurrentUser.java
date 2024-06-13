package org.projeto.desktop;

import lombok.Getter;
import lombok.Setter;
import org.projeto.data.entities.User;
import org.projeto.desktop.factory.Page;

@Getter
public class CurrentUser {
    public static int id;
    public static String first_name;
    public static String last_name;
    public static String name;
    public static String email;
    public static String password;
    public static String address;
    public static String door;
    public static String city;
    public static String phone;
    public static String type;
    @Setter
    public static Page currentPage;

    public static void setUser(User user) {
        id = user.getId();
        name = user.getName();
        email = user.getEmail();
        password = user.getPassword();
        address = user.getAddress();
        door = String.valueOf(user.getDoor());
        type = user.getUserType().getType().toLowerCase();
        currentPage = null;

        // Split the name into first and last names
        String[] nameParts = name.split(" ", 2); // Split into two parts at the first space
        if (nameParts.length == 2) {
            first_name = nameParts[0];
            last_name = nameParts[1];
        } else {
            // Handle the case where there's no space in the name
            first_name = name;
            last_name = "";
        }
    }

    public static void logout(){
        id = -1;
        name = null;
        type = null;
        currentPage = null;
    }
}
