package br.com.tokunaga.moviegame.entites;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "QUIZZ")
public class Quizz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "STATUS")
    private Status status;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Movie> movies = new ArrayList<>();
}
