package api.recarbon.auth.service;

import api.recarbon.auth.dto.UserInfoDto;
import api.recarbon.auth.entity.UserInfo;
import api.recarbon.auth.exception.UserExistException;
import api.recarbon.auth.exception.UserNotFoundException;
import api.recarbon.auth.payload.UserResponse;
import api.recarbon.auth.repository.IUserInfoRepository;
import api.recarbon.auth.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserInfoService {

    @Autowired
    private IUserInfoRepository repository;

    public UserResponse addUser(UserInfo userInfo) throws UserExistException, UserNotFoundException {
        try {
            repository.save(userInfo);
        } catch (Exception e) {
            throw UserExistException.create(userInfo, e);
        }
        return new UserResponse(
                HttpStatus.CREATED,
                "User Successfully Created",
                getUserByEmail(userInfo.getEmail()).map(UserInfoDto::new)
        );
    }

    public List<UserInfoDto> loadAllUser() {
        return repository.findAll().stream().map(UserInfoDto::new).collect(Collectors.toList());
    }

    public UserResponse getUser(UserInfo userInfo) throws UserNotFoundException {
        String identifier = userInfo.getEmail();
        boolean isEmail = Utility.validateEmail(identifier);
        Optional<UserInfo> data = isEmail ? getUserByEmail(identifier) : getUserByUsername(identifier);

        return new UserResponse(
                HttpStatus.OK,
                "User found with email/username: " + identifier,
                data.map(UserInfoDto::new).orElseThrow(() -> UserNotFoundException.create(identifier))
        );
    }

    private Optional<UserInfo> getUserByEmail(String email) {
        return repository.findByEmail(email);
    }

    private Optional<UserInfo> getUserByUsername(String username) {
        return repository.findByEmail(username);
    }
}
