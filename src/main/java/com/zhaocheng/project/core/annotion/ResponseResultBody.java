package com.zhaocheng.project.core.annotion;

import com.zhaocheng.project.core.ResponseTypeEnum;
import com.zhaocheng.project.core.api.IResultCode;
import com.zhaocheng.project.core.api.ResultCode;
import lombok.Builder;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author zhao.cheng
 * @date 2020/9/22 10:18
 */
@Retention(RUNTIME)
@Target({TYPE, METHOD})
@Documented
@ResponseBody
public @interface ResponseResultBody {

    ResponseTypeEnum value()  default ResponseTypeEnum.MESSAGE;

}
