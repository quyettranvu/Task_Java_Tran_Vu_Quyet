package com.test.task.JWT;

import java.util.ArrayList;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.test.task.dao.UserDao;

import lombok.extern.slf4j.Slf4j;

//Custom authentication for retrieving a username, a password, and other attributes for authenticating with a username and password
//This will be looked up by DaoAuthenticationProvider, use PasswordEncoder to validate the password of UserDetails
//->the Authentication will have a principal (UserDetails) and now SecurityContext will have that authentication (type of UsernamePasswordAuthenticationToken)

@Slf4j
@Service
public class CustomerUsersDetailsService implements UserDetailsService {

    @Autowired
    UserDao userDao;

    // create a bean of model to encapsulate the user's details into an object that
    // can be easily passed around in app
    private com.test.task.POJO.User userDetail;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Inside loadUserByUsername {}", username);
        userDetail = userDao.findByEmailId(username);
        if (!Objects.isNull(userDetail)) {
            return new User(userDetail.getEmail(), userDetail.getPassword(), new ArrayList<>());
        } else {
            log.info("User not found");
            throw new UsernameNotFoundException("User not found");
        }
    }

    public com.test.task.POJO.User getUserDetail() {
        /* password not exposed */
        // com.example.server.POJO.User user = userDetail;
        // user.setPassword(null);

        return userDetail;
    }

}
