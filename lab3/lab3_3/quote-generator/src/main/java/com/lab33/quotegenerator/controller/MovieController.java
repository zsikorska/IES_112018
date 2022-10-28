package com.lab33.quotegenerator.controller;

import com.lab33.quotegenerator.exception.ResourceNotFoundException;
import com.lab33.quotegenerator.model.Movie;
import com.lab33.quotegenerator.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MovieController {

    @Autowired
    private MovieService service;

    @GetMapping("/movies")
    public ResponseEntity<?> findAllMovies(){
        return new ResponseEntity<>(service.getAllMovies(), HttpStatus.OK);
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity<?> findMovieById(@PathVariable long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(service.getMovieById(id), HttpStatus.OK);
    }

    @PostMapping("/movies")
    public ResponseEntity<?> addMovie(@Valid @RequestBody Movie movie){
        return new ResponseEntity<>(service.saveMovie(movie), HttpStatus.OK);
    }

    @PostMapping("/movies/many")
    public ResponseEntity<?> addMovies(@Valid @RequestBody List<Movie> movies) {
        return new ResponseEntity<>(service.saveMovies(movies), HttpStatus.OK);
    }

    @PutMapping("/movies/{id}")
    public ResponseEntity<?> updateMovie(@PathVariable long id, @Valid @RequestBody Movie movie) throws ResourceNotFoundException {
        return new ResponseEntity<>(service.updateMovie(id, movie), HttpStatus.OK);
    }

    @DeleteMapping("/movies/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(service.deleteMovie(id), HttpStatus.OK);
    }

    @GetMapping("/movies/with-quotes")
    public ResponseEntity<?> findAllMoviesWithQuotes(){
        return new ResponseEntity<>(service.getAllMoviesWithQuotes(), HttpStatus.OK);
    }

}
