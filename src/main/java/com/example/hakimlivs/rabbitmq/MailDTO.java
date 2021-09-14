package com.example.hakimlivs.rabbitmq;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MailDTO {
    @JsonProperty
    String mailTo;
    @JsonProperty
    String type;

    @JsonCreator
    public MailDTO(@JsonProperty("mailTo") String mailTo, @JsonProperty("type") String type){
        this.mailTo = mailTo;
        this.type = type;
    }
}