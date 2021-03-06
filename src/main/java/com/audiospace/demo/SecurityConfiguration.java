package com.audiospace.demo;

import com.audiospace.demo.services.UserDetailsLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  private UserDetailsLoader usersLoader;

  public SecurityConfiguration(UserDetailsLoader usersLoader) {
    this.usersLoader = usersLoader;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
      .userDetailsService(usersLoader) // How to find users by their username
      .passwordEncoder(passwordEncoder()) // How to encode and verify passwords
    ;
  }

  //Modify this part for authentication
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .formLogin()
      .loginPage("/login")
      .defaultSuccessUrl("/profile")
      .permitAll()
      .and()
      .logout()
      .logoutSuccessUrl("/login?logout")
      .permitAll()
      .and()
      .authorizeRequests()
      .antMatchers(
        "/event/create",
        "/event/submitted")
      .authenticated()
      .and()
      .authorizeRequests()
      .antMatchers(
        "/",
        "/event",
        "/register",
        "/employees",
        "/js/**", // had to add this to not restrict scripts
        "/css/**", // had to add this to not restrict stylesheets
        "/img/**") // had to add this to not restrict images
      .permitAll()
      .anyRequest().authenticated();
  }

//  @Override
//  protected void configure(HttpSecurity http) throws Exception {
//    http
//      .formLogin()
//      .loginPage("/login")
//      .defaultSuccessUrl("/profile")
//      .permitAll()
//      .and()
//      .logout()
//      .logoutSuccessUrl("/login?logout")
//      .permitAll()
//      .and()
//      .authorizeRequests()
//      .antMatchers(
//        "/",
//        "/register",
//        "/js/**", // had to add this to not restrict scripts
//        "/css/**", // had to add this to not restrict stylesheets
//        "/img/**") // had to add this to not restrict images
//      .permitAll()
//      .anyRequest().authenticated();
//  }
}
