package com.praveen.reservation.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.praveen.reservation.model.Guest;

@Repository
public interface GuestRepository extends CrudRepository<Guest, Long> {
}
