package com.retailstore.retailstoreassignment.application.adapters.in.rest.exception;

/**
 * Created by ilkayaktas on 22.05.2022 at 17:32.
 */

public class InvalidCredentialException extends Exception{
   public InvalidCredentialException(String message) {
      super(message);
   }
}
