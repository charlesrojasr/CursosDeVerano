package com.bytecode.core.dao;

import java.util.ArrayList;
import java.util.List;

//import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bytecode.core.models.entity.Role;
import com.bytecode.core.models.entity.Usuario;

@Service("jpaUserDetailsService")
public class JpaUserDetailsService implements UserDetailsService{

	@Autowired
	private IUsuarioDao usuariodao;
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuariodao.findByUsername(username);
		
		if(usuario == null) {
			throw new UsernameNotFoundException("Usuario no existe");
		}
		
		List<GrantedAuthority> listaRoles = new ArrayList<GrantedAuthority>();
		for(Role rol: usuario.getRoles()) {
			listaRoles.add(new SimpleGrantedAuthority(rol.getAuthorities()));
		}
		if(listaRoles.isEmpty()) {
			throw new UsernameNotFoundException("Usuario no tiene roles asignados");
		}
		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEstado(), true, true, true, listaRoles);
 	}

}
