<#if tableMetaData??>
<#assign customFields=tableMetaData.customFields>
<#assign entityName=tableMetaData.entityName>
package ${packageName}.controller;

import ${packageName}.entity.${entityName};
import ${packageName}.common.page.PageBean;
import ${packageName}.common.page.PageParam;
import ${packageName}.common.response.Response;
import ${packageName}.service.${entityName}Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static ${packageName}.constants.RestURIConstants.*;

/**
 *  ${entityName} 控制器
 * @author ${author}
 * @date ${date}
 */
@Api(value = "${entityName}控制器", description = "")
@Validated
@RequestMapping(APP_PREFIX)
@RestController
public class ${entityName}Controller {
    @Autowired
    private ${entityName}Service ${entityName?uncap_first}Service;

    /**
     * @author ${author}
     * @date ${date}
     * @description 增加单个${entityName}
     */
    @ApiOperation(value = "增加单个${entityName}", notes = "")
    @PostMapping(${entityName?upper_case}S)
    public Response save${entityName}(@Validated ${entityName} ${entityName?uncap_first}) {
        ${entityName?uncap_first}Service.insert(${entityName?uncap_first});
        return Response.ok().put("${entityName?uncap_first}Id", ${entityName?uncap_first}.get${entityName}Id());
    }
    <#if !( customFields[2].columnName ? ends_with("_id") && customFields[3].columnName == "gmt_create" ) >

    /**
     * @author ${author}
     * @date ${date}
     * @description 查询单个${entityName}
     */
    @ApiOperation(value = "查询单个${entityName}", notes = "")
    @ApiImplicitParam(name = "${entityName?uncap_first}Id", value = "", required = true, dataType = "Long")
    @GetMapping(${entityName?upper_case}S + ${entityName?upper_case}_ID)
    public Response get${entityName}(@PathVariable String ${entityName?uncap_first}Id) {
        ${entityName} ${entityName?uncap_first} = ${entityName?uncap_first}Service.get(${entityName?uncap_first}Id);
        return Response.ok().put("${entityName?uncap_first}", ${entityName?uncap_first});
    }

    /**
     * @author ${author}
     * @date ${date}
     * @description 分页查询${entityName}
     */
     @ApiOperation(value = "分页查询${entityName}", notes = "")
    @GetMapping(${entityName?upper_case}S)
    public Response list${entityName}(PageParam pageParam) {
        PageBean<${entityName}> pageBean = ${entityName?uncap_first}Service.list${entityName}(pageParam);
        return Response.ok().put("pageBean", pageBean);
    }

    /**
     * @author ${author}
     * @date ${date}
     * @description 更新单个${entityName}
    */
    @ApiOperation(value = "更新单个${entityName}", notes = "")
    @PutMapping(${entityName?upper_case}S)
    public Response update${entityName}(@Validated ${entityName} ${entityName?uncap_first}) {
        ${entityName?uncap_first}Service.update(${entityName?uncap_first});
        return Response.ok();
    }

    /**
     * @author ${author}
     * @date ${date}
     * @description 删除单个${entityName}
     */
    @ApiOperation(value = "删除单个${entityName}", notes = "")
    @ApiImplicitParam(name = "${entityName?uncap_first}Id", value = "", required = true, dataType = "Long")
    @DeleteMapping(${entityName?upper_case}S + ${entityName?upper_case}_ID)
    public Response remove${entityName}(@PathVariable String ${entityName?uncap_first}Id) {
        ${entityName?uncap_first}Service.delete(${entityName?uncap_first}Id);
        return Response.ok();
    }
    </#if>
}


</#if>