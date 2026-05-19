package Proyecto.Virtual_Pet.controller.admin;

import Proyecto.Virtual_Pet.dto.AdminDTOs.*;
import Proyecto.Virtual_Pet.model.entity.AdminUsuario;
import Proyecto.Virtual_Pet.repository.AdminUsuarioRepository;
import Proyecto.Virtual_Pet.security.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/auth")
public class AuthAdminController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AdminUsuarioRepository usuarioRepo;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(), request.getPassword())
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = jwtUtil.generateToken(userDetails);

        AdminUsuario usuario = usuarioRepo.findByUsername(request.getUsername()).orElseThrow();

        return ResponseEntity.ok(LoginResponse.builder()
                .token(token)
                .username(usuario.getUsername())
                .nombre(usuario.getNombre())
                .rol(usuario.getRol())
                .build());
    }

    @GetMapping("/me")
    public ResponseEntity<LoginResponse> me(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        String username = jwtUtil.extractUsername(token);
        AdminUsuario usuario = usuarioRepo.findByUsername(username).orElseThrow();
        return ResponseEntity.ok(LoginResponse.builder()
                .token(token)
                .username(usuario.getUsername())
                .nombre(usuario.getNombre())
                .rol(usuario.getRol())
                .build());
    }
}