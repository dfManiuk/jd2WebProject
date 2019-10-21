package by.htp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;

@EnableWebSecurity
@Configuration
@ComponentScan(basePackages = {"by.htp" }) 
public class SpringWebSecurityConfig extends WebSecurityConfigurerAdapter  {

	@Autowired
	//@Qualifier("userDetailsService")
	private UserDetailsService userDetailsService;
	
    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
	
        // Users in memory.
    	 
      //  auth.inMemoryAuthentication().withUser("user1").password("12345").roles("USER");
       // auth.inMemoryAuthentication().withUser("admin1").password("12345").roles("USER, ADMIN");
         auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        // For User in database.
       // auth.userDetailsService(userDetailsService);

    }
  
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	String encoded=new BCryptPasswordEncoder().encode("1234");
    	System.out.println("LLLLLLLLLLLLLLLLLLLLLL" + ": "+encoded);
    
    	

    	 
        // The pages does not require login
        http.authorizeRequests().antMatchers("/", "/welcome", "/login", "/logout").permitAll();
 
        // /userInfo page requires login as USER or ADMIN.
        // If no login, it will redirect to /login page.
        http.authorizeRequests().antMatchers("/userInfo").access("hasAnyRole('USER', 'ADMIN')");
 
        // For ADMIN only.
        http.authorizeRequests().antMatchers("/admin").access("hasRole('ADMIN')");
 
        // When the user has logged in as XX.
        // But access a page that requires role YY,
        // AccessDeniedException will throw.
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
 
        // Config for Login Form
        http.authorizeRequests().and().formLogin()//
                // Submit URL of login page.
                .loginProcessingUrl("/loginAction") // Submit URL
                .loginPage("/login")
                .defaultSuccessUrl("/userInfo")
                .failureUrl("/login?error=true")
                .usernameParameter("username")
                .passwordParameter("password")            
                // Config for Logout Page
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful");

    }
    

    @Bean
    public PasswordEncoder passwordEncoder() {
    	 BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
         return bCryptPasswordEncoder;  
    }
 
 
    @Bean
    public AuthenticationTrustResolver getAuthenticationTrustResolver() {
        return new AuthenticationTrustResolverImpl();
    }
 
}
