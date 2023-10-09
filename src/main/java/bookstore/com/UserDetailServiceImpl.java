package bookstore.com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import bookstore.web.AppUser;
import bookstore.web.AppUserRepository;


@Service
public class UserDetailServiceImpl implements UserDetailsService  {
	 private final AppUserRepository userRepository;

	    @Autowired
	    public UserDetailServiceImpl(AppUserRepository appUserRepository) {
	        this.userRepository = appUserRepository;
	    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {   
    	AppUser curruser = userRepository.findByUsername(username);
        UserDetails user = new org.springframework.security.core.userdetails.User(username, curruser.getPasswordHash(), 
        		AuthorityUtils.createAuthorityList(curruser.getRole()));
        return user;
    }   
} 
