package victor.testejavaweb.testewebapp.controllers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import victor.testejavaweb.testewebapp.domain.Evento;
import victor.testejavaweb.testewebapp.domain.Usuario;
import victor.testejavaweb.testewebapp.repositories.EventoRepository;
import victor.testejavaweb.testewebapp.repositories.UsuarioRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class EventoController {

    @Autowired
    EventoRepository eventoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping("/eventos")
    public List<Evento> getAllEventos() {
        return eventoRepository.findAll();
    }

    @PostMapping("/eventos")
    public ResponseEntity<Evento> createEvento(@RequestBody Evento evento) {
        Evento novoEvento = new Evento(evento.getNome(), evento.getVagas(), evento.getDataHoraInicio(), evento.getDataHoraFim());
        eventoRepository.save(novoEvento);
        return new ResponseEntity<>(novoEvento, HttpStatus.CREATED);
    }

    @GetMapping("/eventos/find-usuarios-by-evento/{eventoId}")
    public ResponseEntity<List<Usuario>> getAllTagsByTutorialId(@PathVariable(value = "eventoId") Long eventoId) {

        if (!eventoRepository.existsById(eventoId)) {
            return ResponseEntity.notFound().build();
        }

        List<Usuario> usuarios = usuarioRepository.findUsuariosByEventosId(eventoId);

        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @PutMapping("/eventos/entrada/{usuarioId}/{eventoId}")
    public ResponseEntity<?> entrarNoEvento(
            @PathVariable(value = "usuarioId") Long usuarioId,
            @PathVariable(value = "eventoId") Long eventoId) {

        if(!usuarioRepository.existsById(usuarioId)) {
            return new ResponseEntity<>("Usuário não encontrado.", HttpStatus.NOT_FOUND);
        }

        if(!eventoRepository.existsById(eventoId)) {
            return new ResponseEntity<>("Evento não encontrado.", HttpStatus.NOT_FOUND);
        }

        Usuario usuario = usuarioRepository.findById(usuarioId).get();
        Evento evento = eventoRepository.findById(eventoId).get();

        // TODO: O USUÁRIO SÓ PODERÁ ENTRAR NO EVENTO NO PERÍODO DE UMA HORA ANTES DO INICIO DO EVENTO
        LocalDateTime timeNow = LocalDateTime.now();
        LocalDateTime eventoBeginTime = LocalDateTime.parse(evento.getDataHoraInicio(), DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

        if(timeNow.isBefore(eventoBeginTime.minusHours(1L))) {
            return new ResponseEntity<>("Ainda não é possível a entrada no evento.", HttpStatus.BAD_REQUEST);
        }

        evento.getEntrada().add(usuario);
        eventoRepository.save(evento);

        usuario.setEntrada(evento);
        usuarioRepository.save(usuario);

        return new ResponseEntity<>("Entrada realizada com sucesso!", HttpStatus.OK);
    }

}
