package com.ale.common.exception.custom;

/**
 * @author alewu
 * @date 2018/6/5 15:09
 */
public class JwtTokenMissingException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public JwtTokenMissingException(){
        super();
    }

    public JwtTokenMissingException(String message){
        super(message);
    }

}
