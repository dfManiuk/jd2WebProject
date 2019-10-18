package by.htp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.htp.dao.UserDAO;
import by.htp.entity.User;


@Service("userDetailsService")
public class UserDetailsServiceImp implements UserDetailsService  {

	
	@Autowired
	private UserDAO userDao;
	
	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! detSer");
		
		User user = userDao.findUserByUsername(username);
		UserBuilder builder = null;
		if (user != null) {
//			builder = org.springframework.security.core.userdetails.User.withUsername(username);
//		      builder.disabled(!user.isOut());
//		      builder.password(user.getPassword());
//		      String[] authorities = user.getAuthorities()
//		          .stream().map(a -> a.getAuthority()).toArray(String[]::new);
//
//		      builder.authorities(authorities);
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! detSer");
		}else {
			 throw new UsernameNotFoundException("User not found.");
		}
		
		return builder.build();
	}

}
