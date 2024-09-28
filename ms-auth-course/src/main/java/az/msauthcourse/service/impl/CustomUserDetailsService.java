package az.msauthcourse.service.impl;

import az.msauthcourse.client.CourseDictionaryClient;
import az.msauthcourse.dao.CustomUserDetails;
import az.msauthcourse.dao.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final CourseDictionaryClient courseDictionaryClient;

    @Autowired
    public CustomUserDetailsService(CourseDictionaryClient courseDictionaryClient) {
        this.courseDictionaryClient = courseDictionaryClient;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserResponse userResponse = courseDictionaryClient.getUserByUsername(username).getBody();
        if(userResponse == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(userResponse);
    }

}
