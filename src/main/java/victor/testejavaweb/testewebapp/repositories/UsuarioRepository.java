package victor.testejavaweb.testewebapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import victor.testejavaweb.testewebapp.domain.Usuario;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findUsuariosByEventosId(Long eventoId);
}
