package api.recarbon.auth.controller;

import api.recarbon.auth.dto.UserInfoDto;
import api.recarbon.auth.entity.UserInfo;
import api.recarbon.auth.exception.UserExistException;
import api.recarbon.auth.exception.UserNotFoundException;
import api.recarbon.auth.payload.UserResponse;
import api.recarbon.auth.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @PostMapping("/addUser")
    public ResponseEntity<UserResponse> addUser(@RequestBody UserInfo userInfo) throws UserNotFoundException, UserExistException {
        return new ResponseEntity<>(userInfoService.addUser(userInfo), HttpStatus.OK);
    }

    @GetMapping("/getUsers")
    public ResponseEntity<List<UserInfoDto>> users() {
        return new ResponseEntity<>(userInfoService.loadAllUser(), HttpStatus.OK);
    }

    @GetMapping("/getUser")
    public ResponseEntity<UserResponse> user(@RequestBody UserInfo userInfo) throws UserNotFoundException {
        UserResponse response = userInfoService.getUser(userInfo);
        return new ResponseEntity<>(response, response.getStatusCode());
    }
}
