package org.projeto.desktop;

import lombok.Getter;
import lombok.Setter;
import org.projeto.data.entities.User;
import org.projeto.desktop.factory.Page;

@Getter
public class CurrentUser {
    public static int id;
    public static String name;
    public static String type;
    @Setter
    public static Page currentPage;

    public static void setUser(User user){
        id = user.getId();
        name = user.getName();
        type = user.getUserType().getType().toLowerCase();
        currentPage = null;
    }

    public static void logout(){
        id = -1;
        name = null;
        type = null;
        currentPage = null;
    }
}
