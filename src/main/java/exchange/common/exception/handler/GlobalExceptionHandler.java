package exchange.common.exception.handler;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import cn.dev33.satoken.exception.SameTokenInvalidException;
import exchange.common.exception.BusinessException;
import exchange.common.exception.IResultCode;
import exchange.common.exception.ResultCode;
import exchange.common.exception.utils.StreamUtils;
import exchange.common.result.R;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/**
 * <p>
 * 全局异常处理器
 * </p>
 *
 * @author sunql
 * @since 2023-10-24
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 无效认证
     */
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(SameTokenInvalidException.class)
    public R<IResultCode> handleSameTokenInvalidException(SameTokenInvalidException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',内网认证失败'{}',无法访问系统资源", requestURI, e.getMessage());
        return R.error(ResultCode.UN_AUTHORIZED);
    }


    /**
     * 权限码异常
     */
    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(NotPermissionException.class)
    public R<IResultCode> handleNotPermissionException(NotPermissionException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',权限码校验失败'{}'", requestURI, e.getMessage());
        return R.error(ResultCode.FORBIDDEN);
    }

    /**
     * 角色权限异常
     */
    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(NotRoleException.class)
    public R<IResultCode> handleNotRoleException(NotRoleException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',角色权限校验失败'{}'", requestURI, e.getMessage());
        return R.error(ResultCode.FORBIDDEN);
    }

    /**
     * 认证失败
     */
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(NotLoginException.class)
    public R<IResultCode> handleNotLoginException(NotLoginException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',认证失败'{}',无法访问系统资源", requestURI, e.getMessage());
        return R.error(ResultCode.UN_AUTHORIZED);
    }

    /**
     * 请求方式不支持
     */
    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public R<IResultCode> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e,
                                                              HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',不支持'{}'请求", requestURI, e.getMethod());
        return R.error(HttpStatus.METHOD_NOT_ALLOWED.value(), e.getMessage());
    }

    /**
     * 业务异常
     */
    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(BusinessException.class)
    public R<IResultCode> handleServiceException(BusinessException e) {
        log.warn(e.getMessage());
        return R.error(e);
    }

    /**
     * 请求路径中缺少必需的路径变量
     */
    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(MissingPathVariableException.class)
    public R<IResultCode> handleMissingPathVariableException(MissingPathVariableException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求路径中缺少必需的路径变量'{}',发生系统异常.", requestURI);
        return R.error(HttpStatus.BAD_REQUEST.value(), String.format("请求路径中缺少必需的路径变量[%s]", e.getVariableName()));
    }

    /**
     * 请求参数类型不匹配
     */
    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public R<IResultCode> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求参数类型不匹配'{}',发生系统异常.", requestURI);
        return R.error(HttpStatus.BAD_REQUEST.value(),
                String.format("请求参数类型不匹配，参数[%s]要求类型为：'%s'，但输入值为：'%s'", e.getName(), e.getRequiredType().getName(), e.getValue()));
    }

    /**
     * 自定义验证异常
     */
    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(BindException.class)
    public R<IResultCode> handleBindException(BindException e) {
        log.error(e.getMessage());
        String message = StreamUtils.join(e.getAllErrors(), DefaultMessageSourceResolvable::getDefaultMessage, ", ");
        return R.error(HttpStatus.BAD_REQUEST.value(), message);
    }


    /**
     * 自定义验证异常
     */
    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(ConstraintViolationException.class)
    public R<IResultCode> constraintViolationException(ConstraintViolationException e) {
        log.error(e.getMessage());
        String message = StreamUtils.join(e.getConstraintViolations(), ConstraintViolation::getMessage, ", ");
        return R.error(HttpStatus.BAD_REQUEST.value(), message);
    }


    /**
     * 自定义验证异常
     */
    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public R<IResultCode> constraintNotReadableException(ConstraintViolationException e) {
        return R.error(ResultCode.BAD_REQUEST);
    }

    /**
     * 自定义验证异常
     */
    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R<IResultCode> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.warn("[methodArgumentNotValidExceptionExceptionHandler]", e);
        FieldError fieldError = e.getBindingResult().getFieldError();
        assert fieldError != null; // 断言，避免告警
        return R.error(ResultCode.BAD_REQUEST.getCode(), fieldError.getDefaultMessage());
    }

    /**
     * 处理 Resilience4j 限流抛出的异常
     */
    public R<IResultCode> requestNotPermittedExceptionHandler(HttpServletRequest req, Throwable ex) {
        log.warn("[requestNotPermittedExceptionHandler][url({}) 访问过于频繁]", req.getRequestURL(), ex);
        return R.error(ResultCode.TOO_MANY_REQUESTS);
    }

    /**
     * 拦截未知的运行时异常
     */
    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(RuntimeException.class)
    public R<IResultCode> handleRuntimeException(RuntimeException e, HttpServletRequest request) {
        if (e.getCause() instanceof InvocationTargetException
                && ((InvocationTargetException) e.getCause()).getTargetException() instanceof BusinessException) {
            log.warn(e.getMessage());
            return R.error((BusinessException) ((InvocationTargetException) e.getCause()).getTargetException());
        }
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生未知异常.", requestURI, e);
        return R.error(ResultCode.INTERNAL_SERVER_ERROR);
    }

    /**
     * 系统异常
     */
    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(Exception.class)
    public R<IResultCode> handleException(Throwable e, HttpServletRequest request) {
        // 部分特殊的库的处理
        if (Objects.equals("io.github.resilience4j.ratelimiter.RequestNotPermitted", e.getClass().getName())) {
            return requestNotPermittedExceptionHandler(request, e);
        }

        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生系统异常.", requestURI, e);
        return R.error(ResultCode.INTERNAL_SERVER_ERROR);
    }
}
