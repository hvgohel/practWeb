package com.dw.practWeb.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ErrorInfo
{
    private String code;
    private String message;

    public ErrorInfo()
    {
        // TODO Auto-generated constructor stub
    }

    public ErrorInfo(String message)
    {
        super();
        this.message = message;
    }

    public ErrorInfo(String code, String message)
    {
        super();
        this.code = code;
        this.message = message;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

}
