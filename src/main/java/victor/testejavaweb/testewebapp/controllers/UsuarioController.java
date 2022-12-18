package victor.testejavaweb.testewebapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import victor.testejavaweb.testewebapp.domain.Evento;
import victor.testejavaweb.testewebapp.domain.Usuario;
import victor.testejavaweb.testewebapp.repositories.EventoRepository;
import victor.testejavaweb.testewebapp.repositories.UsuarioRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    EventoRepository eventoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping("/usuarios")
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @PostMapping("/usuarios")
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
        Usuario novoUsuario = new Usuario(usuario.getNome());
        usuarioRepository.save(novoUsuario);
        return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
    }

    @GetMapping("/usuarios/find-eventos-by-usuario/{usuarioId}")
    public ResponseEntity<List<Evento>> getAllEventosByUsuarioId(@PathVariable(value = "usuarioId") Long usuarioId) {

        if (!usuarioRepository.existsById(usuarioId)) {
            return ResponseEntity.notFound().build();
        }

        List<Evento> eventos = eventoRepository.findEventosByUsuariosId(usuarioId);

        return new ResponseEntity<>(eventos, HttpStatus.OK);
    }

    @PutMapping("/usuarios/inscricao/{usuarioId}/{eventoId}")
    public ResponseEntity<?> createInscricaoUsuario(
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
        // Verificando se o usuário já está inscrito
        if(evento.getUsuarios().contains(usuario)) {
            return new ResponseEntity<>("Usuário já inscrito nesse evento!", HttpStatus.BAD_REQUEST);
        }
        // Aintigido o limite de vagas
        if(evento.getUsuarios().size() == evento.getVagas()) {
            return new ResponseEntity<>("Limite de vagas atingido!", HttpStatus.BAD_REQUEST);
        }

        // Verificando se o evento já foi iniciado
        LocalDateTime timeNow = LocalDateTime.now();
        LocalDateTime eventoBeginTime = LocalDateTime.parse(evento.getDataHoraInicio(), DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        if(timeNow.isAfter(eventoBeginTime)) {
            return new ResponseEntity<>("Evento já iniciado, não é possível fazer a inscrição.", HttpStatus.BAD_REQUEST);
        }

        evento.getUsuarios().add(usuario);
        eventoRepository.save(evento);

        return new ResponseEntity<>("Usuário inscrito com sucesso!", HttpStatus.OK);
    }

    @PutMapping("/usuarios/cancelar/{usuarioId}/{eventoId}")
    public ResponseEntity<?> cancelarInscricaoUsuario(
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

        // Verificando se o usuário não está inscrito no evento
        if(!evento.getUsuarios().contains(usuario)) {
            return new ResponseEntity<>("Usuário não está inscrito no evento.", HttpStatus.BAD_REQUEST);
        }
        
        // TODO: NÃO SERÁ PERMITIDO O CANCELAMENTO DE UMA INSCRIÇÃO APÓS O USUÁRIO TER REALIZADO A ENTRADA NO EVENTO

        evento.getUsuarios().remove(usuario);
        eventoRepository.save(evento);

        return new ResponseEntity<>("Incrição para o evento cancelada!", HttpStatus.OK);
    }
}
