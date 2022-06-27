package com.pratiksha.authentication.models;


public class AuthenticationResponse 
{
    private Object response;

    public AuthenticationResponse(Object response) {
        this.response = response;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

}
