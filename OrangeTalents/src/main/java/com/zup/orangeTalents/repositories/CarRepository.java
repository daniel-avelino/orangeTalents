package com.zup.orangeTalents.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zup.orangeTalents.entities.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {}
