package com.jiseguerra.reservation_system.reservation.utils;

import com.jiseguerra.reservation_system.reservation.dto.CreateReservationDTO;
import com.jiseguerra.reservation_system.reservation.dto.ReservationDTO;
import com.jiseguerra.reservation_system.reservation.entity.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

/**
 * @author John Ivan Seguerra
 * @version $Id: ReservationMapper.java, 2024-11-01 2:56 PM $$
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReservationMapper {

	@Mapping(target = "status", constant = "RESERVED")
	Reservation dtoToEntity(CreateReservationDTO dto);

	ReservationDTO entityToDTO(Reservation entity);
}
