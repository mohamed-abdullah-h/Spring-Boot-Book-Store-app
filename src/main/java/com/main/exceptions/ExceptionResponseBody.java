package com.main.exceptions;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionResponseBody {

	private LocalDateTime timeStamp;
	private long errorCode;
	private String [] details;
	private String errorMessage;
}
