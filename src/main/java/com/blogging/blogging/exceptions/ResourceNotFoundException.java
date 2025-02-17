package com.blogging.blogging.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{

    private String resourceName;
    private String fieldName;
    private long fieldValue;

    public ResourceNotFoundException(String resourceName,String fieldName,long fieldValue){
          super(String.format("%s with %s : %s , not found",resourceName,fieldName,fieldValue));
          this.fieldName=fieldName;
          this.resourceName=resourceName;
          this.fieldValue=fieldValue;
    }


}
