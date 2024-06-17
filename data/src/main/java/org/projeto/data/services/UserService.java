package org.projeto.data.services;

import org.apache.commons.codec.digest.DigestUtils;
import org.projeto.data.entities.User;
import org.projeto.data.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private static UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        UserService.userRepository = userRepository;
    }

    public static User login(String email, String password) throws Exception {
        User user = UserService.findUserByEmail(email);

        // If user is found and password matches, return the user
        if (user.getPassword().equals(DigestUtils.sha256Hex(password))) {
            return user;
        } else {
            throw new Exception("Invalid password");
        }
    }

    public static void register(User user) {
        if (user.getPassword() == null) {
            throw new IllegalArgumentException("Password cannot be null");
        }

        user.setPassword(DigestUtils.sha256Hex(user.getPassword()));
        userRepository.save(user);
    }

    public static void update(User editUser) throws Exception {

        userRepository.save(editUser);
    }

    public static void delete(User user) {
        userRepository.delete(user);
    }

    public static User findUserByID(Integer ID) {
        return userRepository.findById(ID);
    }

    public static User findUserByEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new Exception("User with email " + email + " not found");
        }
        return user;
    }

    public static List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public static List<String> getUserTypeAllEmails(Integer userTypeID) {
        ArrayList<String> emails = new ArrayList<>();
        for (User client : UserService.getUserByTypeID(userTypeID)) {
            assert false;
            emails.add(client.getEmail());
        }
        return emails;
    }

    public static List<User> getUserByTypeID(Integer id) {
        return userRepository.findByUserTypeId(id);
    }

    public static List<User> getAllClients() {
        return userRepository.findByUserTypeId(1);
    }

    public static User findUserByName(String name) {
        return userRepository.findByName(name);
    }

    public static List<User> getAllWorkers() {
        return userRepository.findByUserTypeId(3);
    }
}
