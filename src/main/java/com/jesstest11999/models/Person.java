package com.jesstest11999.models;

import java.util.UUID;

import lombok.Getter;

@Getter
public class Person {
    
    private UUID uuid;
    private String name;

    public Person(UUID uuid,
                  String name){
        
        this.uuid = uuid;
        this.name = name;
        
    }

}
