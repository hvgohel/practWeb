package com.dw.practWeb.exception;

import com.dw.practWeb.dto.ErrorInfo;

public class ResourceNotFoundException extends RuntimeException implements ErrorInfo
{
    private String code;

    public ResourceNotFoundException()
    {
        super();
    }

    public ResourceNotFoundException(String msg)
    {
        super(msg);
    }

    public ResourceNotFoundException(String code, String msg)
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
