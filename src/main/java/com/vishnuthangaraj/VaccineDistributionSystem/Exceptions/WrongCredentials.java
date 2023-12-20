package com.vishnuthangaraj.VaccineDistributionSystem.Exceptions;

public class WrongCredentials extends RuntimeException{
    public WrongCredentials(String message){
        super(message);
    }
}
