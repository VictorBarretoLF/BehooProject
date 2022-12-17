package victor.testejavaweb.testewebapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import victor.testejavaweb.testewebapp.domain.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long> {
}
