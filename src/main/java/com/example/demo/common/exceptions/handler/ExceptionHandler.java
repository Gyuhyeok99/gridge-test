package com.example.demo.common.exceptions.handler;


import com.example.demo.common.code.BaseErrorCode;
import com.example.demo.common.exceptions.BaseException;

public class ExceptionHandler extends BaseException {
    public ExceptionHandler(BaseErrorCode errorCode) {super(errorCode);}
}
