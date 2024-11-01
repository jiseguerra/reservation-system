package com.jiseguerra.reservation_system.reservation.controller;

import com.jiseguerra.reservation_system.common.ExampleConstant;
import com.jiseguerra.reservation_system.reservation.dto.CreateReservationDTO;
import com.jiseguerra.reservation_system.reservation.dto.ReservationDTO;
import com.jiseguerra.reservation_system.reservation.dto.UpdateReservationDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author John Ivan Seguerra
 * @version $Id: ReservationController.java, 2024-10-31 2:20 PM $$
 */
@Tag(name = "Reservation API")
public interface ReservationController {

	@Operation(summary = "Create a reservation")
	@ApiResponses(
				value = {
							@ApiResponse(
										responseCode = "201",
										description = "Created a reservation",
										content = {
													@Content(
																mediaType = "application/json",
																schema = @Schema(implementation = ReservationDTO.class)
													)
										}
							),
							@ApiResponse(responseCode = "400", description = "Invalid params", content = @Content),
							@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
				}
	)
	CompletableFuture<ReservationDTO> createReservation(@RequestBody(content = {
				@Content(examples = { @ExampleObject(ExampleConstant.CREATE_NEW_RESERVATION) })
	}) @Valid CreateReservationDTO createDTO);

	@Operation(summary = "Update a reservation")
	@ApiResponses(
				value = {
							@ApiResponse(
										responseCode = "200",
										description = "Updated reservation",
										content = {
													@Content(
																mediaType = "application/json",
																schema = @Schema(implementation = ReservationDTO.class)
													)
										}
							),
							@ApiResponse(responseCode = "400", description = "Invalid params", content = @Content),
							@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
				}
	)
	CompletableFuture<ReservationDTO> updateReservation(Long id, @RequestBody(content = {
				@Content(examples = { @ExampleObject(ExampleConstant.UPDATE_RESERVATION) })
	}) @Valid UpdateReservationDTO updateDTO);

	@Operation(summary = "Fetch all reservations")
	@ApiResponses(
				value = {
							@ApiResponse(
										responseCode = "200",
										description = "Fetched reservations",
										content = {
													@Content(
																mediaType = "application/json",
																array = @ArraySchema(schema = @Schema(implementation = ReservationDTO.class))
													)
										}
							),
							@ApiResponse(responseCode = "400", description = "Invalid params", content = @Content),
							@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
				}
	)
	CompletableFuture<List<ReservationDTO>> getReservations(
				@Parameter(description = "Customer name")
				String name,
				@Parameter(description = "Customer email")
				String emailAddress);


	@Operation(summary = "Cancel a reservation")
	@ApiResponses(
				value = {
							@ApiResponse(
										responseCode = "200",
										description = "Cancelled reservation",
										content = {
													@Content(
																mediaType = "application/json",
																array = @ArraySchema(schema = @Schema(implementation = ReservationDTO.class))
													)
										}
							),
							@ApiResponse(responseCode = "400", description = "Invalid params", content = @Content),
							@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
				}
	)
	CompletableFuture<ReservationDTO> cancelReservation(Long id);

}
