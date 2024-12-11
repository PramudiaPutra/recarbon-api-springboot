package api.recarbon.auth.service;

import api.recarbon.auth.entity.User;
import api.recarbon.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> allUsers() {

        return new ArrayList<>(userRepository.findAll());
    }

}
