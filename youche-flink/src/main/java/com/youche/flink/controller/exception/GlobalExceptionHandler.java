package com.youche.flink.controller.exception;

import com.alibaba.fastjson.JSON;
import com.youche.utils.Result;
import com.youche.utils.ResultUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 全局异常捕捉
 */
@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Result<List<ValidationFieldError>>> methodArgumentNotValidExceptionHandler(HttpServletRequest request, MethodArgumentNotValidException exception) {
        BindingResult bindingResult = exception.getBindingResult();
        return ResultUtils.error(makeValidationFieldErrors(bindingResult), "校验失败");
    }

    @ExceptionHandler(value = BindException.class)
    public ResponseEntity<Result<List<ValidationFieldError>>> bindExceptionHandler(HttpServletRequest request, BindException exception) {
        BindingResult bindingResult = exception.getBindingResult();
        return ResultUtils.error(makeValidationFieldErrors(bindingResult), "校验失败");
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<Result<List<String>>> constraintViolationExceptionHandler(HttpServletRequest request, ConstraintViolationException exception) {
        Set<ConstraintViolation<?>> constraintViolations = exception.getConstraintViolations();
        List<String> messages = constraintViolations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());

        return ResultUtils.error(messages, "校验失败");
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Result<String>> exceptionHandler(HttpServletRequest request, Exception exception) {
        String message = exception.getMessage();
        log.info(message);
        return ResultUtils.error(message);
    }

    private List<ValidationFieldError> makeValidationFieldErrors(BindingResult bindingResult) {

        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        List<ValidationFieldError> validationFieldErrors = fieldErrors.stream()
                .map(fieldError -> new ValidationFieldError(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());

        return validationFieldErrors;
    }
}
