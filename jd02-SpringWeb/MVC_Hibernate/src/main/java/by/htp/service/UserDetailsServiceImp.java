package by.htp.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import by.htp.dao.UserDAO;
import by.htp.entity.Authorities;
import by.htp.entity.User;


@Service("userDetailsService")
public class UserDetailsServiceImp implements UserDetailsService  {

	
	@Autowired
	private UserDAO userDao;
	
	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		


		 User user = userDao.findUserByUsername(username);
		
		// Set<Authorities> authorities = user.getAuthorities();
		 
		 	UserBuilder builder = null;
	
			builder = org.springframework.security.core.userdetails.User.withUsername(username);
		      builder.disabled(!user.isOut());
		      builder.password(user.getPassword());
		      String[] authorities = user.getAuthorities()
		          .stream().map(a -> a.getAuthority()).toArray(String[]::new);
		      builder.authorities(authorities);
	
		return builder.build();
	}

}
