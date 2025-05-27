
package com.example.api.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.api.Entity.UserModel;


public interface IUserRepository extends JpaRepository<UserModel, String> {
   Optional<UserModel> findByCorreoElectronico(String correoElectronico);
   boolean existsByCorreoElectronicoAndContrasena(String correoElectronico, String contrasena);
   boolean existsByCorreoElectronico(String correoElectronico);
   

}

    

