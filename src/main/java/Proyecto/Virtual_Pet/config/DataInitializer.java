package Proyecto.Virtual_Pet.config;

import Proyecto.Virtual_Pet.model.entity.AdminUsuario;
import Proyecto.Virtual_Pet.repository.AdminUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private AdminUsuarioRepository usuarioRepo;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public void run(String... args) {
        if (usuarioRepo.count() == 0) {

            usuarioRepo.save(AdminUsuario.builder()
                    .username("admin")
                    .password(encoder.encode("admin123"))
                    .nombre("Administrador VirtualPet")
                    .email("admin@virtualpet.co")
                    .rol("SUPER_ADMIN")
                    .activo(true)
                    .build());

            usuarioRepo.save(AdminUsuario.builder()
                    .username("operador")
                    .password(encoder.encode("operador123"))
                    .nombre("Operador Tienda")
                    .email("operador@virtualpet.co")
                    .rol("OPERADOR")
                    .activo(true)
                    .build());

            System.out.println("✅ Usuarios admin creados: admin/admin123 y operador/operador123");
        }
    }
}