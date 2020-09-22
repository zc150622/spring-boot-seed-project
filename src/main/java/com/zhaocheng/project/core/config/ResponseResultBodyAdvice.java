package com.zhaocheng.project.core.config;

import com.zhaocheng.project.core.ResponseTypeEnum;
import com.zhaocheng.project.core.annotion.ResponseResultBody;
import com.zhaocheng.project.core.api.R;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.annotation.Annotation;


/**
 * @author zhao.cheng
 * @date 2020/9/22 10:20
 */
@RestControllerAdvice
public class ResponseResultBodyAdvice implements ResponseBodyAdvice<Object> {

    private static final Class<? extends Annotation> ANNOTATION_TYPE = ResponseResultBody.class;

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
      return AnnotatedElementUtils.hasAnnotation(methodParameter.getContainingClass(), ANNOTATION_TYPE) || methodParameter.hasMethodAnnotation(ANNOTATION_TYPE);
    }

    @Override
    public Object beforeBodyWrite(Object t, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        ResponseResultBody responseResultBody = methodParameter.getMethodAnnotation(ResponseResultBody.class);

        ResponseTypeEnum type = responseResultBody.value();
        if (t instanceof R) {
            return t;
        }

        if (type.equals(ResponseTypeEnum.DATA)) {
            return R.data(t);
        }else if (type.equals(ResponseTypeEnum.STATUS)) {
            return R.status((boolean) t);
        }else if (type.equals(ResponseTypeEnum.MESSAGE)) {
            return R.success((String) t);
        }else {
            return t;
        }
    }
}
