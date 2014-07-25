package com.oceaneconsulting.tanaguru.service.adapter.impl;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.oceaneconsulting.tanaguru.entity.WsUser;
import com.oceaneconsulting.tanaguru.service.WsUserService;
import com.oceaneconsulting.tanaguru.service.adapter.UserServiceSecurityProvider;

@Component
public class UserServiceSecurityProviderImpl implements UserServiceSecurityProvider {
	
	private static final Logger LOGGER = Logger.getLogger(UserServiceSecurityProviderImpl.class);
	
	@Autowired
	WsUserService wsUserService;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		
		LOGGER.debug("Verify credentials for user identified by [" +login+ "]");
		UserDetails userDetails = null;
		try {
			WsUser wsUser = wsUserService.getUser(login);
			
			if(wsUser!=null){	
				Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
				if(wsUser != null && wsUser.getRole() != null && wsUser.getRole().getRole() != null){
					GrantedAuthority role = new SimpleGrantedAuthority(wsUser.getRole().getRole()); 
					roles.add(role);
				}
				userDetails = new User(wsUser.getEmail(), wsUser.getPassword(), roles);
			}
			
		} catch (Exception e){//Transform all exception into UsernameNotFoundException
			LOGGER.debug("No credentials for user identified by [" +login+ "]");
			throw new UsernameNotFoundException("No credentials for user identified by [" +login+ "]");
		}
		return userDetails;
	}

}
