package com.lab33.quotegenerator.service;

import com.lab33.quotegenerator.exception.ResourceNotFoundException;
import com.lab33.quotegenerator.model.Movie;
import com.lab33.quotegenerator.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository repository;

    @Autowired
    private QuoteService quoteService;

    public Movie getMovieById(long id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Movie not found for this id: " + id));
    }

    public List<Movie> getAllMovies() {
        return repository.findAll();
    }

    public Movie saveMovie(Movie movie) {
        return repository.save(movie);
    }

    public List<Movie> saveMovies(List<Movie> movies) {
        return repository.saveAll(movies);
    }

    public Movie updateMovie(long id, Movie newMovie) throws ResourceNotFoundException {
        Movie movie = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Movie not found for this id: " + id));
        movie.setTitle(newMovie.getTitle());
        movie.setYear(newMovie.getYear());
        return repository.save(movie);
    }

    public String deleteMovie(long id) throws ResourceNotFoundException {
        Movie movie = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Movie not found for this id: " + id));
        repository.delete(movie);
        return "Movie with the id: " + id + " - deleted";
    }

    public List<Movie> getAllMoviesWithQuotes() {
        List<Movie> movies = repository.findAll();
        List<Movie> moviesWithQuotes = new ArrayList<>();
        for(Movie movie: movies){
            if(!quoteService.getAllQuotesFromMovie(movie.getId()).isEmpty())
                moviesWithQuotes.add(movie);
        }
        return moviesWithQuotes;
    }

}
