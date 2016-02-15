package com.dw.practWeb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.dw.practWeb.exception.AccessDeniedException;
import com.dw.practWeb.exception.IllegalStateException;
import com.dw.practWeb.exception.ResourceNotFoundException;
import com.dw.practWeb.exception.UnauthorizedAccessException;
import com.dw.practWeb.model.ErrorInfo;

public class BaseAPIController
{
    private Logger logger = LoggerFactory.getLogger(BaseAPIController.class);

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(com.dw.practWeb.exception.IllegalArgumentException.class)
    @ResponseBody
    public ErrorInfo errorHandler(com.dw.practWeb.exception.IllegalArgumentException exception)
    {
        logger.debug("Bad Request : ", exception);
        return getErrorInfoObj(exception);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseBody
    public ErrorInfo errorHandler(ResourceNotFoundException exception)
    {
        logger.debug("Not Found : ", exception);
        return getErrorInfoObj(exception);
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public ErrorInfo errorHandler(AccessDeniedException exception)
    {
        logger.debug("Access Denied : ", exception);
        return getErrorInfoObj(exception);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedAccessException.class)
    @ResponseBody
    public ErrorInfo errorHandler(UnauthorizedAccessException exception)
    {
        logger.debug("User Unauthorized : ", exception);
        return getErrorInfoObj(exception);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(IllegalStateException.class)
    @ResponseBody
    public ErrorInfo errorHandler(IllegalStateException exception)
    {
        logger.debug("Conflict : ", exception);
        return getErrorInfoObj(exception);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ErrorInfo errorHandler(Exception exception)
    {
        logger.debug("Unknown Server Exception : ", exception);
        return getErrorInfoObj(exception);
    }

    private ErrorInfo getErrorInfoObj(Exception exception)
    {
        ErrorInfo errorInfo = new ErrorInfo(exception.getMessage());

        if (exception instanceof com.dw.practWeb.dto.ErrorInfo)
        {
            errorInfo.setCode(((com.dw.practWeb.dto.ErrorInfo) exception).getCode());
        }
        return errorInfo;
    }
}
