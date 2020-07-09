package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.service.UserDetailsServiceImpl;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

  @Autowired
  UserDetailsServiceImpl userDetailsService;
  

  @Bean
  public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
  }

    //WebSecurity(セキュリティの設定対象外)
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/image/**");
    }
    
    //HttpSecurity(アクセス認証の設定)
    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http
          .authorizeRequests()//アクセス権限の設定
              .antMatchers("/","/signup").permitAll()//非ログインでもアクセス可
              .anyRequest().authenticated()//ログインしている場合は全urlにアクセス可
              .and()
          .formLogin()
              .loginPage("/login")//ログインページのurl
              .loginProcessingUrl("/authenticate")//認証処理のリクエストurl
              .usernameParameter("name")//リクエストパラメータのname属性を表示
              .passwordParameter("password")
              .defaultSuccessUrl("/user/show",true)//ログイン成功時の遷移url
              .permitAll()//「/login」へのアクセスは誰でもOK
              .and()
          .logout()
              .logoutUrl("/logout")
              .logoutSuccessUrl("/logout/success?logout")
              .permitAll();
    }

    @Autowired
    void configureAuthenticationManager(AuthenticationManagerBuilder auth) throws Exception {
      auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
}