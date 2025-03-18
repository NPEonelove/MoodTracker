package ru.meowlove.MoodTracker.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "Mood")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Mood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "value")
    private Integer value;

    @Column(name = "comment")
    private String comment;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "account_username", referencedColumnName = "username")
    private Account account;

    public Mood(Integer value, String comment, Date date) {
        this.value = value;
        this.comment = comment;
        this.date = date;
    }
}
