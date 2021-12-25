package com.team.meett.advice;

import com.team.meett.dto.ErrorResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.text.ParseException;

@RestControllerAdvice(basePackages = "com.team.meett.controller")
public class ExceptionAdvice {

    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> errorHandling(Exception e) {
        ErrorResponse response = new ErrorResponse();

        if (e instanceof InvalidDataAccessApiUsageException) {
            //status 500
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("수정되는 데이터 값이 반드시 필요합니다::Entity must not be null.");
        } else if (e instanceof EmptyResultDataAccessException) {
            //status 500
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("삭제하고자 하는 Id가 존재하지 않습니다::No class entity with id exists!");
        } else if(e instanceof DataIntegrityViolationException){
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("필수 입력 칼럼을 채워주세요::not-null property references a null or transient value");
        }else if (e instanceof HttpMessageNotReadableException) {
            //status 400
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage("임시! insert하는 데이터 값이 JsonBody가 아닐때 발생됨::Required request body is missing");
        } else if (e instanceof HttpMediaTypeNotSupportedException) {
            //status 415
            response.setStatusCode(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value());
            response.setMessage("Content type not supported::");
        } else if(e instanceof MethodArgumentTypeMismatchException){
            //status 400
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage("Request한 칼럼 타입을 확인해주세요. 설정과 다른 타입이 들어감::Failed to convert value of type to required type");
        } else if(e instanceof HttpMessageConversionException){
            //status 500
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("Type definition error");
        } else if(e instanceof IllegalArgumentException){
            // status 500
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("request param 값이 들어오지 않거나 틀렸습니다");
        } else if(e instanceof ParseException){
            // status 500
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("날짜를 parse 할 수 없습니다 값의 유무와 형식을 확인해주세요");
        }

        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

}
