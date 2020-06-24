package com.example.demo.service;

import java.util.Optional;
import java.util.Collection;

/*
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Account;
import com.example.demo.mapper.LoginMapper;
import com.example.demo.entity.DbUserDetails;

//UserDetailsService(ユーザー情報を検索する役割)(UserDetailsインスタンスを返す)
@Service
public class UserDetailsServiceImpl implements UserDetailsService{
  
  @Autowired
	LoginMapper loginMapper;

  @Override
  @Transactional(readOnly = true)
  public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException{
    
    //DBからユーザー情報を取得
    Account account = Optional.ofNullable(loginMapper.findAccount(name))
    .orElseThrow(() -> new UsernameNotFoundException("User not found."));

    return new DbUserDetails(account, getAuthorities(account));
  }

  private Collection<GrantedAuthority> getAuthorities(Account account) {
		//認可が通った時にこのユーザに与える権限の範囲を設定する。
		return AuthorityUtils.createAuthorityList("ROLE_USER");
  }
}
  /*こっちのコードでもログインできる！！
  Account account = loginMapper.findAccount(name);

  if (account == null) {
    throw new UsernameNotFoundException("User" + name + "was not found in the database");
  }

  List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
        grantList.add(authority);

        //UserDetailsはインタフェースなのでUserクラスのコンストラクタで生成したユーザオブジェクトをキャスト
        UserDetails userDetails = (UserDetails)new User(account.getName(), account.getPassword(),grantList);

        return userDetails;
        */
