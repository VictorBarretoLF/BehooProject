package victor.testejavaweb.testewebapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import victor.testejavaweb.testewebapp.domain.Evento;
import victor.testejavaweb.testewebapp.repositories.EventoRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class BootStrapData implements CommandLineRunner {

    private final EventoRepository eventoRepository;

    public BootStrapData(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Started in Bootstrap");

        Evento evento = new Evento();
        evento.setNome("Festa de Rua");
        evento.setVagas(40L);
        evento.setDataHoraInicio(LocalDateTime.parse("20-07-2023 10:30:00", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        evento.setDataHoraFim(LocalDateTime.parse("20-07-2023 18:00:00", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        eventoRepository.save(evento);
    }

}
