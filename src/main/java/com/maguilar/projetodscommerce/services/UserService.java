package com.maguilar.projetodscommerce.services;

import com.maguilar.projetodscommerce.entities.Role;
import com.maguilar.projetodscommerce.entities.User;
import com.maguilar.projetodscommerce.projections.UserDetailsProjection;
import com.maguilar.projetodscommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<UserDetailsProjection> user = userRepository.searchUserAndRolesByEmail(username);

        if(user.isEmpty() ) {
            throw  new UsernameNotFoundException(" User not found");
        }

        User usuario = new User();
        usuario.setEmail(username);
        usuario.setPassword(user.getFirst().getPassword());

        for(UserDetailsProjection projection : user){
            usuario.addRole( new Role(projection.getRoleId(), projection.getAuthority()));
        }

        return usuario;
    }
}
