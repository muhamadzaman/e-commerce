package com.services.userservice.exceptions;

public class ResourceNotFoundException extends RuntimeException
{
    public ResourceNotFoundException()
    { super("resource not found on server"); }

    public ResourceNotFoundException(String msg)
    { super(msg); }
}
