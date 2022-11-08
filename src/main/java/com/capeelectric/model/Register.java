package com.capeelectric.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class Register extends RegisterDetails implements UserDetails {

	  /**
     * @author capeelectricsoftware
     */
	private static final long serialVersionUID = 1L;

	private RegisterDetails registerDetails;
     
	public RegisterDetails getRegisterDetails() {
		return registerDetails;
	}


	public void setRegister(Register register) {
		this.registerDetails = register;
	}

	public Register() {
		super();
	}


	public Register(RegisterDetails registerDetails) {
		super();
		this.registerDetails = registerDetails;
	}

	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return Arrays.stream("Admin".split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return registerDetails.getPassword();
    }

    @Override
    public String getUsername() {
        return registerDetails.getEmailid();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
