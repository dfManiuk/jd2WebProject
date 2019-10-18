package by.htp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.userdetails.UserDetailsService;

@EnableWebSecurity
@Configuration
@ComponentScan(basePackages = {"by.htp" }) 
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringWebSecurityConfig extends WebSecurityConfigurerAdapter  {

	@Autowired
	@Qualifier("userDetailsService")
	private UserDetailsService userDetailsService;
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(
                passwordEncoder());
    }
	
	
	 @Bean
	  public BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	  };
	  
	  @Override
	  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	  }
	  
	  @Override
	  protected void configure(HttpSecurity http) throws Exception {
		  System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! +++");
	    http.authorizeRequests().anyRequest().hasAnyRole("ADMIN", "USER")
	    .and()
	    .authorizeRequests().antMatchers("/login**").permitAll()
	    .and()
	    .formLogin().loginPage("/login").loginProcessingUrl("/loginAction").permitAll()
	    .and()
	    .logout().logoutSuccessUrl("/login").permitAll()
	    .and()
	    .csrf().disable();
	  }
}
