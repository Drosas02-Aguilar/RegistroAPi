package com.registroAPi.resgistroApi.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ServiceResult {

    @JsonIgnore
    public int status;
    public Object object;
    public String message;
    public String errorMessage;
    
}
