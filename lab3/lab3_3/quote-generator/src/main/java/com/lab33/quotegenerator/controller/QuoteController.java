package com.lab33.quotegenerator.controller;

import com.lab33.quotegenerator.exception.NoQuotesFoundException;
import com.lab33.quotegenerator.exception.ResourceNotFoundException;
import com.lab33.quotegenerator.model.Quote;
import com.lab33.quotegenerator.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class QuoteController {

    @Autowired
    private QuoteService service;

    @GetMapping("/quotes")
    public ResponseEntity<?> findAllQuotes(){
        return new ResponseEntity<>(service.getAllQuotes(), HttpStatus.OK);
    }

    @GetMapping("/quotes/{id}")
    public ResponseEntity<?> findQuoteById(@PathVariable long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(service.getQuoteById(id), HttpStatus.OK);
    }

    @PostMapping("/quotes")
    public ResponseEntity<?> addQuote(@Valid @RequestBody Quote quote){
        return new ResponseEntity<>(service.saveQuote(quote), HttpStatus.OK);
    }

    @PostMapping("/quotes/many")
    public ResponseEntity<?> addQuotes(@Valid @RequestBody List<Quote> quotes){
        return new ResponseEntity<>(service.saveQuotes(quotes), HttpStatus.OK);
    }

    @PutMapping("/quotes/{id}")
    public ResponseEntity<?> updateQuote(@PathVariable long id, @Valid @RequestBody Quote quote) throws ResourceNotFoundException {
        return new ResponseEntity<>(service.updateQuote(id, quote), HttpStatus.OK);
    }

    @DeleteMapping("/quotes/{id}")
    public ResponseEntity<?> deleteQuote(@PathVariable long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(service.deleteQuote(id), HttpStatus.OK);
    }


    @GetMapping("/quotes/random")
    public ResponseEntity<?> getRandomQuote(@RequestParam(value = "movie", required = false) Long movieId) throws NoQuotesFoundException {
        if(movieId == null)
            return new ResponseEntity<>(service.getRandomQuote(), HttpStatus.OK);
        return new ResponseEntity<>(service.getRandomQuoteFromMovie(movieId), HttpStatus.OK);
    }


}
