package br.com.tokunaga.moviegame.entites;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "SCORE")
public class Score {
    @Id
    @Column(name = "USERNAME")
    private String username;

    @Column(name = "RIGHT_ANSWER_COUNTER")
    private Integer rightAnswer;

    @Column(name = "QUIZZ_ANSWER_COUNTER")
    private Integer quizzAnswer;
}
