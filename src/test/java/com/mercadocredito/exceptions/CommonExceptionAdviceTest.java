package com.mercadocredito.exceptions;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommonExceptionAdviceTest {

    @Test
    void illegalArgumentException() throws IllegalArgumentException{
        try{
            String test = null;
            if(test == null){
                throw new IllegalArgumentException("Unit testing");
            }
        }catch(Exception e){
            Assert.assertEquals ("Unit testing", e.getMessage ());
        }
    }

}