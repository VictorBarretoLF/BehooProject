package victor.testejavaweb.testewebapp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    private String dataHoraInicio;
    private String dataHoraFim;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "usuario_evento",
            joinColumns = { @JoinColumn(name = "evento_id") },
            inverseJoinColumns = { @JoinColumn(name = "usuario_id") })
    private Set<Usuario> usuarios = new HashSet<>();

    @OneToMany(mappedBy="entrada")
    private Set<Usuario> entrada = new HashSet<>();

    public Evento() {
    }

    public Evento(String nome, Long vagas, String dataHoraInicio, String dataHoraFim) {
        this.nome = nome;
        this.vagas = vagas;
        this.dataHoraInicio = dataHoraInicio;
        this.dataHoraFim = dataHoraFim;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDataHoraInicio() {
        return dataHoraInicio;
    }

    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<Usuario> authors) {
        this.usuarios = authors;
    }

    public void setDataHoraInicio(String dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public String getDataHoraFim() {
        return dataHoraFim;
    }

    public void setDataHoraFim(String dataHoraFim) {
        this.dataHoraFim = dataHoraFim;
    }

    public Set<Usuario> getEntrada() {
        return entrada;
    }

    public void setEntrada(Set<Usuario> entrada) {
        this.entrada = entrada;
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
