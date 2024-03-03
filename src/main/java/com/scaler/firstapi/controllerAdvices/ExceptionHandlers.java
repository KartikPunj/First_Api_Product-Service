package com.scaler.firstapi.controllerAdvices;

import com.scaler.firstapi.dtos.ArthimeticExceptionDTO;
import com.scaler.firstapi.dtos.ProductNotFoundExceptionDTO;
import com.scaler.firstapi.exceptions.ProductNotExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
// this annotation is used to handle exceptions globally
public class ExceptionHandlers {
    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ArthimeticExceptionDTO> handleArithmeticException(){
        ArthimeticExceptionDTO arithmeticException = new ArthimeticExceptionDTO();
        arithmeticException.setMessage("You are trying to divide by zero");
        return new ResponseEntity<>(arithmeticException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductNotExistsException.class)
    // this annotation is used to handle a specific exception
    // here we have passed the productNotExistsException object as a parameter
    // this object was created when we threw the exception in the ProductService class
    // so that we can access the message from the exception object
    // as the exception from service class is thrown to the controller class and then to the controller advice class
    public ResponseEntity<ProductNotFoundExceptionDTO> handleProductNotExistsException(ProductNotExistsException productNotExistsException){
        ProductNotFoundExceptionDTO productNotFoundExceptionDTO = new ProductNotFoundExceptionDTO();
        productNotFoundExceptionDTO.setMessage(productNotExistsException.getMessage());
        productNotFoundExceptionDTO.setDetails("Product with the given id does not exist");
        return new ResponseEntity<>(productNotFoundExceptionDTO,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ResponseEntity<ArrayIndexOutOfBoundsException> handleArrayIndexOutOfBoundsException(){
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
