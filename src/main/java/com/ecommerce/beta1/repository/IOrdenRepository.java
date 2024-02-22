package com.ecommerce.beta1.repository;

import com.ecommerce.beta1.model.Orden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IOrdenRepository extends JpaRepository<Orden, Integer> { //
}