package com.jesstest11999.controllers.exception;

public record ErrorResponse(Integer status, String error, String message) {
}
