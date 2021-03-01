package com.proyecto.andrea.demoSpring.api;


import com.proyecto.andrea.demoSpring.exceptions.RecordNotFoundException;
import com.proyecto.andrea.demoSpring.model.Author;
import com.proyecto.andrea.demoSpring.model.Exemplar;
import com.proyecto.andrea.demoSpring.services.ExemplarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/exemplar")
public class ExemplarServiceApi {

    @Autowired
    ExemplarService service;

    @GetMapping
    public ResponseEntity<List<Exemplar>> getAllExemplar(){
        List<Exemplar> list = service.getAllExemplar();

        return new ResponseEntity<List<Exemplar>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id_exemplar}")
    public  ResponseEntity<Exemplar> getExemplarById(@PathVariable("id_exemplar") Long id_exemplar) throws RecordNotFoundException {
        Exemplar entity = service.getExemplarById(id_exemplar);

        return  new ResponseEntity<Exemplar>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/location/{location}")
    public ResponseEntity<List<Exemplar>> getExemplarByLocation(@PathVariable("location") String location) throws RecordNotFoundException {
        List<Exemplar> list = service.getExemplarByLocation(location);

        return new ResponseEntity<List<Exemplar>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/exemplar_book/{ejemplar}")
    public ResponseEntity<List<Exemplar>> getExemplarByBooks(@PathVariable("ejemplar") Long id_exemplar) throws RecordNotFoundException {
        List<Exemplar> list = service.getExemplarByBooks(id_exemplar);

        return new ResponseEntity<List<Exemplar>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Exemplar> createExemplar(@Valid @RequestBody Exemplar myExemplar) {
        Exemplar entity = service.createExemplar(myExemplar);

        return new ResponseEntity<Exemplar>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Exemplar> updateExemplar(@Valid @RequestBody Exemplar myExemplar) throws RecordNotFoundException {
        Exemplar entity = service.updateExemplar(myExemplar);

        return new ResponseEntity<Exemplar>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id_exemplar}")
    public HttpStatus deleteExemplarById(@PathVariable("id_exemplar") Long id_exemplar) throws RecordNotFoundException {
        service.deleteExemplarById(id_exemplar);

        return HttpStatus.FORBIDDEN;
    }
}
