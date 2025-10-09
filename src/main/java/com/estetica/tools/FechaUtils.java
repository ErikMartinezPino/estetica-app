package com.estetica.tools;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class FechaUtils {
	public static LocalDateTime combinar(LocalDate fecha, LocalTime hora) {
		return LocalDateTime.of(fecha, hora);
	}
}
