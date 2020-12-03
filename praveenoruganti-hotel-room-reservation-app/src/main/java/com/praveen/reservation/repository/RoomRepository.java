package com.praveen.reservation.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.praveen.reservation.model.Room;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {
}
