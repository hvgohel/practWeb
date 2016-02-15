package com.dw.practWeb.exception;

import com.dw.practWeb.dto.ErrorInfo;

public class AccessDeniedException extends RuntimeException implements ErrorInfo
{
    private String code;

    public AccessDeniedException()
    {
    }

    public AccessDeniedException(String msg)
    {
        super(msg);
    }

    public AccessDeniedException(String code, String msg)
    {
        super(msg);
        this.code = code;
    }

    @Override
    public String getCode()
    {
        return code;
    }

}
