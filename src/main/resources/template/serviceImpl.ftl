package ${packageName}.service.impl;

import ${packageName}.common.mvc.BaseServiceImpl;
import ${packageName}.common.page.PageUtil;
import ${packageName}.common.page.PageBean;
import ${packageName}.common.page.PageParam;
import ${packageName}.entity.${entityName};
import ${packageName}.dao.${entityName}DAO;
import ${packageName}.service.${entityName}Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
/**
 * ${entityName ? uncap_first}Service 接口业务逻辑实现类
 * @author ${author}
 * @date ${date}
 */
@Slf4j
@Service("${entityName ? uncap_first}Service")
public class ${entityName}ServiceImpl extends BaseServiceImpl<${entityName}> implements ${entityName}Service{
    /** 采用这种方式注入，为了设置父类中的dao */
    private ${entityName}DAO ${entityName?uncap_first}DAO;
    @Autowired
    public void set${entityName}DAO(${entityName}DAO ${entityName?uncap_first}DAO) {
        super.setBaseDAO(${entityName?uncap_first}DAO);
        this.${entityName?uncap_first}DAO = ${entityName?uncap_first}DAO;
    }

    @Override
    public PageBean<${entityName}> list${entityName}(PageParam pageParam) {
        PageUtil.startPage(pageParam);
        List<${entityName}> list = ${entityName ? uncap_first}DAO.list${entityName}();
        return PageUtil.getPageBean(list);
    }

}