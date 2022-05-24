package br.com.tokunaga.moviegame.entites;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static br.com.tokunaga.moviegame.entites.Status.ACTIVE;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "MATCH")
public class Match {
    @Id
    @Column(name = "ID", nullable = false)
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    @Column(name = "STATUS", nullable = false)
    private Status status;

    @OneToOne(cascade = CascadeType.ALL)
    private Quizz currentQuizz;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Quizz> nextQuizz = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<Quizz> historyQuizz = new ArrayList<>();

    @Column(name = "ERROR_ANSWER_COUNTER")
    private Integer errorAnswer;

    @Column(name = "RIGHT_ANSWER_COUNTER")
    private Integer rightAnswer;

    public boolean isValidMatch() {
        return this.getStatus().equals(ACTIVE);
    }

    public boolean isValidQuizz() {
        return this.getCurrentQuizz().getStatus().equals(ACTIVE);
    }

    public boolean isValidInput(Long id) {
        boolean exist = false;

        for (Movie movie : this.getCurrentQuizz().getMovies()) {
            if (movie.getId().equals(id)) {
                exist = true;
                break;
            }
        }

        return exist;
    }
}
