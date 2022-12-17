package victor.testejavaweb.testewebapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import victor.testejavaweb.testewebapp.domain.Evento;
import victor.testejavaweb.testewebapp.domain.Usuario;
import victor.testejavaweb.testewebapp.repositories.EventoRepository;
import victor.testejavaweb.testewebapp.repositories.UsuarioRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class BootStrapData implements CommandLineRunner {

    private final EventoRepository eventoRepository;
    private final UsuarioRepository usuarioRepository;

    public BootStrapData(EventoRepository eventoRepository, UsuarioRepository usuarioRepository) {
        this.eventoRepository = eventoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Started in Bootstrap");

        Evento evento = new Evento();
        evento.setNome("Festa de Rua");
        evento.setVagas(40L);
        evento.setDataHoraFim(LocalDateTime.parse("20-07-2023 18:00:00", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        evento.setDataHoraInicio(LocalDateTime.parse("20-07-2023 10:30:00", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));

        Usuario usuario = new Usuario("Víctor Barreto");
        usuario.getEventos().add(evento);
        usuarioRepository.save(usuario);

        evento.getUsuarios().add(usuario);

        eventoRepository.save(evento);

        System.out.println(evento.getUsuarios());
    }

}
