package victor.testejavaweb.testewebapp.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private Long vagas;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;

    @ManyToMany
    @JoinTable(name = "usuario_evento", joinColumns = @JoinColumn(name = "evento_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id"))
    private Set<Usuario> usuarios = new HashSet<>();

    public Evento() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getVagas() {
        return vagas;
    }

    public void setVagas(Long vagas) {
        this.vagas = vagas;
    }

    public LocalDateTime getDataHoraInicio() {
        return dataHoraInicio;
    }

    public Set<Usuario> getAuthors() {
        return usuarios;
    }

    public void setAuthors(Set<Usuario> authors) {
        this.usuarios = authors;
    }

    public void setDataHoraInicio(LocalDateTime dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public LocalDateTime getDataHoraFim() {
        return dataHoraFim;
    }

    public void setDataHoraFim(LocalDateTime dataHoraFim) {
        this.dataHoraFim = dataHoraFim;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Evento evento = (Evento) o;
        return Objects.equals(id, evento.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Evento{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", vagas=" + vagas +
                ", dataHoraInicio=" + dataHoraInicio +
                ", dataHoraFim=" + dataHoraFim +
                '}';
    }
}
