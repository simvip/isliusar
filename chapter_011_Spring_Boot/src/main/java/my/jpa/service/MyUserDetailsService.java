package my.jpa.service;

import my.jpa.models.User;
import my.jpa.models.UserPrincipal;
import my.jpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by Ivan Sliusar on 31.08.2018.
 * Red Line Soft corp.
 */
@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(userName);
        if (user == null){
            throw new UsernameNotFoundException(userName);
        }
        return new UserPrincipal(user);
    }
}
