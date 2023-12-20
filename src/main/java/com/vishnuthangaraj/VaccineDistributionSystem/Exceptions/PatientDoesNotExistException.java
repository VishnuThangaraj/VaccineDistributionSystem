package com.vishnuthangaraj.VaccineDistributionSystem.Exceptions;

public class PatientDoesNotExistException extends RuntimeException{
    public PatientDoesNotExistException(String message){
        super(message);
    }
}
