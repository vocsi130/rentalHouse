package edu.mum.rentalHouse.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig  extends WebSecurityConfigurerAdapter{

    @Autowired
    private DataSource dataSource;



    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/img/**", "/fonts/**", "/sass/**");
    }

	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery("select email, password, 1 from user u where u.email=?")
		.authoritiesByUsernameQuery("select user_mail, role_name from user_roles u where u.user_mail=?")
		.passwordEncoder(passwordEncoder()).rolePrefix("ROLE_")
		;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
		.authorizeRequests()
		.antMatchers("/register","/","/about","/login","/resources/**","/static/**","/css/**","/js/**","/images/**","/webjars/**").permitAll()
		.and().authorizeRequests().antMatchers("/allTenant").permitAll()
		.and().authorizeRequests().antMatchers("/addTenant").hasRole("ADMIN")
		.and().authorizeRequests().antMatchers("/addHouse").hasAnyRole("ADMIN")
		.and().authorizeRequests().antMatchers("/addApartment").hasAnyRole("ADMIN")
		.and().authorizeRequests().antMatchers("/addContract").hasAnyRole("ADMIN")
		.and().authorizeRequests().antMatchers("/addPayment").hasAnyRole("ADMIN")
		.and()
		.formLogin().loginPage("/login")
		.failureUrl("/login?error=true")
		.usernameParameter("name")
		.passwordParameter("password")
		.defaultSuccessUrl("/")
		.and().logout().logoutUrl("/logout").logoutSuccessUrl("/login");
	}

}
