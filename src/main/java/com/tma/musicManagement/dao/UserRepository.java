package com.tma.musicManagement.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tma.musicManagement.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
}