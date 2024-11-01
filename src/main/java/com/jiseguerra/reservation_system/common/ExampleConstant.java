package com.jiseguerra.reservation_system.common;

/**
 * @author John Ivan Seguerra
 * @version $Id: ExampleConstant.java, 2024-11-01 1:15 PM $$
 */
public class ExampleConstant {
	public static final String CREATE_NEW_RESERVATION =
				"""
              {
									"name": "John Smith",
									"mobileNumber": "09123456789",
									"emailAddress": "johnsmith@example.com",
									"dateTime": "2024-10-30T18:34:42.663Z",
									"numberOfGuests": 5
              }
        """;

	public static final String UPDATE_RESERVATION =
				"""
              {
									"dateTime": "2024-10-30T18:34:42.663Z",
									"numberOfGuests": 3
              }
        """;
}
