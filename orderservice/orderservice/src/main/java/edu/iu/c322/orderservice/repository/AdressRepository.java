package edu.iu.c322.orderservice.repository;

import edu.iu.c322.orderservice.model.entity.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdressRepository extends JpaRepository<Shipping, Integer> {
}
