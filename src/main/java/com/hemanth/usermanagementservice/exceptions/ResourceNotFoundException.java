package com.hemanth.usermanagementservice.exceptions;


public class ResourceNotFoundException extends RuntimeException{

        public ResourceNotFoundException(String message) {
            super(message);
        }
}
