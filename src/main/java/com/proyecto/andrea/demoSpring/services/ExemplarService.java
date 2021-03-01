package com.proyecto.andrea.demoSpring.services;


import com.proyecto.andrea.demoSpring.exceptions.RecordNotFoundException;
import com.proyecto.andrea.demoSpring.model.Book;
import com.proyecto.andrea.demoSpring.model.Exemplar;
import com.proyecto.andrea.demoSpring.repositories.ExemplarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExemplarService {

    @Autowired
    ExemplarRepository repository;

    public List<Exemplar> getAllExemplar(){
        List<Exemplar> exemplarList = repository.findAll();
        if(exemplarList.size() > 0){
            return exemplarList;
        }else{
            return new ArrayList<>();
        }
    }

    public Exemplar getExemplarById(Long id_exemplar) throws RecordNotFoundException {
        Optional<Exemplar> exemplar = repository.findById(id_exemplar);
        if (exemplar.isPresent()){
            return exemplar.get();
        }else{
            throw new RecordNotFoundException("No se ha encontrado ningún ejemplcar con esa id", id_exemplar);
        }
    }

    public Exemplar createExemplar(Exemplar entity){
        entity= repository.save(entity);
        return entity;
    }

    public Exemplar updateExemplar(Exemplar entity) throws RecordNotFoundException{
        if(entity.getId_exemplar() != null){
            Optional<Exemplar> exemplar = repository.findById(entity.getId_exemplar());
            if(exemplar.isPresent()){
                Exemplar newEntity = exemplar.get();
                newEntity.setLocation(entity.getLocation());
                newEntity = repository.save(newEntity);
                return newEntity;
            }else{
                throw new RecordNotFoundException("Ejemplar no encontrado", entity.getId_exemplar());
            }
        }else{
            throw new RecordNotFoundException("No se ha encontrado identificación de ese ejemplar", 0l);
        }
    }

    public void deleteExemplarById(Long id_exemplar) throws RecordNotFoundException {
        Optional<Exemplar> exemplar = repository.findById(id_exemplar);
        if (exemplar.isPresent()) {
            repository.deleteById(id_exemplar);
        } else {
            throw new RecordNotFoundException("No se ha encontrado ningún ejemplar con esa id", id_exemplar);
        }
    }

    public List<Exemplar> getExemplarByLocation(String location) throws RecordNotFoundException{
        List<Exemplar> exemplarList = repository.getByExemplarLocation(location);
        if (exemplarList.size() > 0) {
            return exemplarList;
        } else {
            return new ArrayList<Exemplar>();
        }
    }

    public List<Exemplar> getExemplarByBooks(Long id_exemplar) throws RecordNotFoundException{
        List<Exemplar> exemplars = repository.getExemplarByBooks(id_exemplar);
        if (exemplars != null){
            return exemplars;
        } else {
            throw new RecordNotFoundException("No se ha encontrado ningún ejemplar con esa id" , id_exemplar);
        }
    }
}
