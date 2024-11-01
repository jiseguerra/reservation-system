package com.jiseguerra.reservation_system.reservation.repository;

import com.jiseguerra.reservation_system.reservation.entity.Reservation;
import com.jiseguerra.reservation_system.reservation.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author John Ivan Seguerra
 * @version $Id: ReservationRepository.java, 2024-10-31 2:41 PM $$
 */
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	List<Reservation> findByNameAndEmailAddress(String name, String emailAddress);

	List<Reservation> findByName(String name);

	List<Reservation> findByEmailAddress(String emailAddress);

	List<Reservation> findByStatus(Status status);
}
