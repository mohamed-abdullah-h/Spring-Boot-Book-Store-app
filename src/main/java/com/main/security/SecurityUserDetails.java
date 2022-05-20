package com.main.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.main.entity.Role;
import com.main.entity.SecurityUser;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SecurityUserDetails implements UserDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1218710501121795940L;
	private SecurityUser user;
	private String password;
	private String userName;
	private String email;
	private boolean isEnabled;
	private boolean isLocked;
	private Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
	
	public SecurityUserDetails(SecurityUser user) {
		this.user = user;
		this.userName = user.getUserName();
		this.password = user.getPassword();
		this.email = user.getEmail();
		this.isEnabled  = user.isEnabled();
		this.isLocked = user.isLocked();
		initAuthorities();
	}
	
	private void initAuthorities() {
		Set<Role> roles = user.getRoles();
		roles.forEach(role->{
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		});
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		// return fixed true
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !isLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// return fixed true
		return true;
	}

	@Override
	public boolean isEnabled() {
		return isEnabled;
	}

	public String getEmail() {
		return email;
	}

	public SecurityUser getUser() {
		return user;
	}

	

}
