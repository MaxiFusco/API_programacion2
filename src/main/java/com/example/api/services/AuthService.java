
package com.example.api.services;

import com.example.api.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
     @Autowired
    private IUserRepository userRepository;

    public boolean validateCredentials(String correoElectronico, String contrasena) {
        return userRepository.existsByCorreoElectronicoAndContrasena(correoElectronico, contrasena);
    }
    
}
