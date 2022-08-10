package com.sourproject.multitools.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sourproject.multitools.entities.DefaultUser;

public interface DefaultUserRepository extends JpaRepository<DefaultUser, Integer> {

}
