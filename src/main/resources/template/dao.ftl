package ${packageName}.dao;

import ${packageName}.entity.${entityName};
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * ${entityName}DAO持久层映射接口
 * @author ${author}
 * @date ${date}
*/
@Repository
public interface ${entityName}DAO extends BaseDAO<${entityName}> {

    List<${entityName}> list${entityName}();

}