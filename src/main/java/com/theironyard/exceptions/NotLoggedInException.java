package com.theironyard.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by EddyJ on 8/4/16.
 */
@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "Not logged in")
public class NotLoggedInException extends RuntimeException{
}
