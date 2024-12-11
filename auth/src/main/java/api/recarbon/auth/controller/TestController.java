package api.recarbon.auth.controller;

import api.recarbon.auth.entity.User;
import api.recarbon.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/test")
@RestController
public class TestController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public ResponseEntity<User> authUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currUser = (User) auth.getPrincipal();

        return ResponseEntity.ok(currUser);
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> findAll() {
        List<User> users = userService.allUsers();

        return ResponseEntity.ok(users);
    }
}
