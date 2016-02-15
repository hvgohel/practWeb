package com.dw.practWeb.exception;

import com.dw.practWeb.dto.ErrorInfo;

public class IllegalArgumentException extends RuntimeException implements ErrorInfo
{
    String code;

    public IllegalArgumentException()
    {
    }

    public IllegalArgumentException(String msg)
    {
        super(msg);
    }

    public IllegalArgumentException(String code, String msg)
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
