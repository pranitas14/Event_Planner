package com.example.Event.Management.Service;



import com.example.Event.Management.Entity.User;
import java.util.List;

public interface UserService {
    User createUser(User user);
    List<User> getAllUsers();
    User getUserById(Long id);
}
