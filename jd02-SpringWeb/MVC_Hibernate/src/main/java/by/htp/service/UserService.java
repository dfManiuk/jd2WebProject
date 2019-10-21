package by.htp.service;

import java.util.List;

import by.htp.entity.User;

public interface UserService {

	User findById(int id);
    
    User findBySSO(String sso);
     
    void saveUser(User user);
     
    void updateUser(User user);
     
    void deleteUserBySSO(String sso);
 
    List<User> findAllUsers(); 
}
