package ${packageName}.service;

import ${packageName}.entity.${entityName};
import ${packageName}.common.page.PageBean;
import ${packageName}.common.page.PageParam;

/**
 * ${entityName}服务层接口
 * @author ${author}
 * @date ${date}
 */
public interface ${entityName}Service extends BaseService<${entityName}> {

    PageBean<${entityName}> list${entityName}(PageParam pageParam);

}