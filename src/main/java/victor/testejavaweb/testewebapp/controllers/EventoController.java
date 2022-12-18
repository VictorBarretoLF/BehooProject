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

}
