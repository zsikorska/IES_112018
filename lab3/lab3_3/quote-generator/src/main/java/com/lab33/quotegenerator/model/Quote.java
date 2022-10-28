package com.lab33.quotegenerator.model;

import javax.persistence.*;

@Entity
@Table(name= "QUOTES")
public class Quote {

    private long id;
    private String quote;

    private Movie movie;

    public Quote(){

    }

    public Quote(String quote, Movie movie) {
        this.quote = quote;
        this.movie = movie;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId(){
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "quote", nullable = false)
    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    @ManyToOne
    @JoinColumn(name="movie_id", nullable=false)
    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

}