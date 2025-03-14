package ru.meowlove.MoodTracker.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Mood")
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Mood(Integer value, String comment, Date date) {
        this.value = value;
        this.comment = comment;
        this.date = date;
    }

    public Mood() {}

    @Override
    public String toString() {
        return "Mood{" +
                "id=" + id +
                ", value=" + value +
                ", comment='" + comment + '\'' +
                ", date=" + date +
                ", account=" + account +
                '}';
    }
}
