package com.registroAPi.resgistroApi.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Restponse {

    @JsonIgnore
    public Object object;

    public int status;

    public String errorMessage;

    public String successMessage;

    public Exception ex;

}
