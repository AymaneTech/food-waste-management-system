package net.foodeals.common.models;

import java.util.Map;

/**
 * ErrorResponse
 */
public record ErrorResponse(int code, String status, String message, Map<String, String> errors) {
}
