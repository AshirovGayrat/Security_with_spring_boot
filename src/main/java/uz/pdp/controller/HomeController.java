package uz.pdp.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {


    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/profile")
    public String profile() {
        return "Welcome to the profile info";
    }







    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete-user")
    public String deleteUser() {
        return "User deleted";
    }


}
