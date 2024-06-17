package org.projeto.data.controllers;

import org.projeto.data.entities.User;

import org.apache.commons.codec.digest.DigestUtils;
import org.projeto.data.entities.Zipcode;
import org.projeto.data.services.UserService;
import org.projeto.data.services.ZipcodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    public UserController(UserService userService) {}

    @GetMapping("/getClients")
    public ResponseEntity<List<User>> getClients(){
        List<User> clients = UserService.getAllClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping("/getUsersByUserTypeID/{id}")
    public ResponseEntity<List<User>> getUsersByUserTypeID(@PathVariable Integer id){
        List<User> clients = UserService.getUserByTypeID(id);
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestParam("email") String email, @RequestParam("password") String password) {
        try {
            User user = UserService.login(email, password);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        try {
            // Check if zipcode exists
            if (!ZipcodeService.existsById(user.getZipcode().getId())) {
                // If not, create new zipcode
                Zipcode newZipcode = new Zipcode();
                newZipcode.setId(user.getZipcode().getId());
                newZipcode.setDistrict(user.getZipcode().getDistrict());
                newZipcode.setCity(user.getZipcode().getCity());
                newZipcode.setLocale(user.getZipcode().getLocale());
                ZipcodeService.addNew(newZipcode);
            }

            UserService.register(user);
            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error registering user: " + e.getMessage());
        }
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateUser(@RequestBody User user) {
        try {
            if (!ZipcodeService.existsById(user.getZipcode().getId())) {
                // If not, create new zipcode
                Zipcode newZipcode = new Zipcode();
                newZipcode.setId(user.getZipcode().getId());
                newZipcode.setDistrict(user.getZipcode().getDistrict());
                newZipcode.setCity(user.getZipcode().getCity());
                newZipcode.setLocale(user.getZipcode().getLocale());
                ZipcodeService.addNew(newZipcode);
            }

            UserService.update(user);
            return ResponseEntity.ok("User updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating user: " + e.getMessage());
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) {
        try {
            User user = UserService.findUserByID(id);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
