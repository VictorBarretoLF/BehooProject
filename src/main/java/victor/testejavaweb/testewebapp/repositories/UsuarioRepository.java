package victor.testejavaweb.testewebapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import victor.testejavaweb.testewebapp.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
