package ${package.Controller};

import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.company.project.core.Result;
import com.company.project.core.ResultCode;

<#if restControllerStyle>
<#else>
    import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
    import ${superControllerClassPackage};
</#if>


import javax.validation.Valid;
import java.util.List;

/**
* <p>
    * ${table.comment} 前端控制器
    * </p>
*
* @author ${author}
* @since ${date}
*/
@Api(tags = "${table.comment}")
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
    class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
    <#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
    <#else>
public class ${table.controllerName} {
    </#if>

    @Autowired
    private ${table.serviceName} ${table.serviceName?uncap_first};


    @ApiOperation(value = "${table.comment}详情", response = ${entity}.class)
    @GetMapping(value = "/info/{id}")
    public Result<${entity}> info(@PathVariable Long id) {

        ${entity} ${entity} = ${table.serviceName?uncap_first}.getById(id);
        return Result.ok(${entity});
    }

    @ApiOperation(value = "${table.comment}新增")
    @PostMapping(value = "/add")
    public Result<Void> add(@RequestBody ${entity} ${entity}) {

        ${table.serviceName?uncap_first}.save(${entity});
        return Result.ok(null);
    }

    @ApiOperation(value = "${table.comment}修改")
    @PostMapping(value = "/modify")
    public Result<Void> modify(@RequestBody ${entity} ${entity}) {

        ${table.serviceName?uncap_first}.updateById(${entity});
        return Result.ok(null);
    }

    @ApiOperation(value = "${table.comment}删除(单个条目)")
    @GetMapping(value = "/remove/{id}")
    public Result<Void> remove(@PathVariable Long id) {

        ${table.serviceName?uncap_first}.removeById(id);
        return Result.ok(null);
    }

    @ApiOperation(value = "${table.comment}删除(多个条目)")
    @PostMapping(value = "/removes")
    public Result<Void> removes(@RequestBody List<Long> ids) {

        ${table.serviceName?uncap_first}.removeByIds(ids);
        return Result.ok(null);
    }

}
</#if>
