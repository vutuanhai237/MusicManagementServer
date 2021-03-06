package com.tma.musicManagement.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tma.musicManagement.model.Genre;

@Repository
public interface GenreRepository extends CrudRepository<Genre, Integer> {

}
