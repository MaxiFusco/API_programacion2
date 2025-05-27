package com.example.api.controller;

import com.example.api.Entity.AplicacionModel;
import com.example.api.Entity.UserModel;
import com.example.api.dto.AplicacionDTO;
import com.example.api.repository.IAplicacionRepository;
import com.example.api.repository.IUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/aplicaciones")
public class AplicacionController {

    @Autowired
    private IAplicacionRepository repo;

    @Autowired
    private IUserRepository userRepo;

    // ✔ POST: Guardar nueva aplicación
    @PostMapping
    public ResponseEntity<?> guardarAplicacion(@RequestBody Map<String, String> datos) {
        try {
            String correo = datos.get("usuarioCorreo");
            Optional<UserModel> usuarioOpt = userRepo.findById(correo);

            if (usuarioOpt.isEmpty()) {
                return ResponseEntity.status(404).body("Usuario no encontrado");
            }

            AplicacionModel app = new AplicacionModel();
            app.setAplicacion(datos.get("aplicacion"));
            app.setNombreUsuario(datos.get("usuario"));
            app.setContrasena(datos.get("contrasena"));
            app.setEmailRecuperacion(datos.get("emailRecuperacion"));
            app.setUsuario(usuarioOpt.get());

            repo.save(app);
            return ResponseEntity.status(201).body("Aplicación guardada");

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    // ✔ GET: Obtener todas las aplicaciones
    @GetMapping
    public List<AplicacionModel> listarAplicaciones() {
        return repo.findAll();
    }

    @GetMapping("/{nombre}")
    public ResponseEntity<?> obtenerPorNombre(@PathVariable String nombre) {
        Optional<AplicacionModel> appOpt = repo.findByAplicacion(nombre).stream().findFirst();

        if (appOpt.isEmpty()) {
            return ResponseEntity.status(404).body("No se encontraron aplicaciones con ese nombre");
        }

        AplicacionDTO dto = new AplicacionDTO(appOpt.get());
        return ResponseEntity.ok(dto); // ✅ Devuelve un objeto JSON, no un array
    }
    @GetMapping("/api/recuperar")
    public ResponseEntity<?> recuperarPorCorreo(@RequestParam String correoElectronico) {
        Optional<Usuario> usuario = usuarioRepository.findByCorreoElectronico(correoElectronico);
        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Correo no registrado");
        }
    }

    // ✔ DELETE: Eliminar aplicación por nombre
    @DeleteMapping("/{nombre}")
    public ResponseEntity<?> eliminarPorNombre(@PathVariable String nombre) {
        Optional<AplicacionModel> apps = repo.findByAplicacion(nombre);

        if (apps.isEmpty()) {
            return ResponseEntity.status(404).body("Aplicación no encontrada");
        }

        // Elimina todas las aplicaciones que coincidan con ese nombre
        repo.deleteAll();

        return ResponseEntity.ok("Se eliminaron ");
    }}
