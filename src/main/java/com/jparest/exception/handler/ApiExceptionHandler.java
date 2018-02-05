package com.jparest.exception.handler;

import com.jparest.exception.*;
import com.jparest.exception.message.ApiExceptionMessage;
import com.jparest.exception.message.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

/**
 * Response message base on exception.
 */

@RestControllerAdvice
public class ApiExceptionHandler {

    /**
     * Exception message when {@link CustomerNotFoundException} throw.
     *
     * @param e
     * @return
     */
    @ExceptionHandler(CustomerNotFoundException.class)
    public ApiExceptionMessage customerNotFoundException(ApiException e) {
        return new ApiExceptionMessage(new Date(), HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND,
                new ErrorMessage(e.getMessage()));
    }

    /**
     * Exception message when {@link CredentialsNotFoundException} throw.
     *
     * @param e
     * @return
     */
    @ExceptionHandler(CredentialsNotFoundException.class)
    public ApiExceptionMessage credentialsNotFoundException(ApiException e) {
        return new ApiExceptionMessage(new Date(), HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND,
                new ErrorMessage(e.getMessage()));
    }

    /**
     * Exception message when {@link PatchOperationNotSupported} throw.
     *
     * @param e
     * @return
     */
    @ExceptionHandler(PatchOperationNotSupported.class)
    public ApiExceptionMessage patchOperationNotSupported(ApiException e) {
        return new ApiExceptionMessage(new Date(), HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST,
                new ErrorMessage(e.getMessage()));
    }

    /**
     * Exception message when {@link UsernameExistException} throw.
     *
     * @param e
     * @return
     */
    @ExceptionHandler(UsernameExistException.class)
    public ApiExceptionMessage usernameExistException(ApiException e) {
        return new ApiExceptionMessage(new Date(), HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT,
                new ErrorMessage(e.getMessage()));
    }

    /**
     * Exception message when {@link AddressNotFoundException} throw.
     *
     * @param e
     * @return
     */
    @ExceptionHandler(AddressNotFoundException.class)
    public ApiExceptionMessage addressNotFoundException(ApiException e) {
        return new ApiExceptionMessage(new Date(), HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND,
                new ErrorMessage(e.getMessage()));
    }

    /**
     * Exception message when {@link ItemNotFoundException} throw.
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ItemNotFoundException.class)
    public ApiExceptionMessage itemNotFoundException(ApiException e) {
        return new ApiExceptionMessage(new Date(), HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND,
                new ErrorMessage(e.getMessage()));
    }

    /**
     * Exception message when {@link PatchFieldNotMatchException} throw.
     *
     * @param e
     * @return
     */
    @ExceptionHandler(PatchFieldNotMatchException.class)
    public ApiExceptionMessage patchFieldNotMatchException(ApiException e) {
        return new ApiExceptionMessage(new Date(), HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND,
                new ErrorMessage(e.getMessage()));
    }

    /**
     * Exception message when {@link OrderNotFoundException} throw.
     *
     * @param e
     * @return
     */
    @ExceptionHandler(OrderNotFoundException.class)
    public ApiExceptionMessage orderNotFoundException(ApiException e) {
        return new ApiExceptionMessage(new Date(), HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND,
                new ErrorMessage(e.getMessage()));
    }
}
