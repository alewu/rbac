import com.ale.config.security.SecurityWebApplicationInitializer;
import com.ale.config.security.WebSecurityConfig;
import com.ale.config.ssm.AppConfig;
import com.ale.config.ssm.MvcWebApplicationInitializer;
import com.ale.config.ssm.MybatisConfig;
import com.ale.config.ssm.WebMvcConfig;
import com.ale.entity.User;
import com.github.pagehelper.PageInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


/**
 * 使用spring测试模块提供的测试请求功能，测试crud 请求的
 * spring4测试时需要servlet3.0支持
 */
// 表示使用Spring Test组件进行单元测试
@RunWith(SpringRunner.class)
// 表示测试环境使用的ApplicationContext将是WebApplicationContext类型的
@WebAppConfiguration
//  指定Bean的配置文件信息 可以有多种方式，这个例子使用的是文件路径形式，如果有多个配置文件，可以将括号中的信息配置为一个字符串数组来表示;
@ContextConfiguration(classes = {AppConfig.class, WebMvcConfig.class, MvcWebApplicationInitializer.class,
        MybatisConfig.class, SecurityWebApplicationInitializer.class, WebSecurityConfig.class})
public class MockMVCTest {

    //传入SpringMVC的IoC
    @Autowired
    private WebApplicationContext wac;

    //虚拟MVC，获得处理结果
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testPage() throws Exception {
        // 模拟请求并拿到返回值
        MvcResult result = mockMvc.perform(get("/emps").param("pn", "1"))
                                  .andReturn();

        MockHttpServletRequest request = result.getRequest();

        // 请求成功后，请求域中会有pageInfo，我们可以取出pageInfo进行验证
        PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");
        System.out.println("当前页码：" + pageInfo.getPageNum());
        System.out.println("总页码：" + pageInfo.getPages());
        System.out.println("总记录数：" + pageInfo.getTotal());
        System.out.println("需要连续显示的页码");

        int[] nums = pageInfo.getNavigatepageNums();
        for (int num : nums) {
            System.out.println(" " + num);
        }


    }

    @Test
    public void testRequestBody() throws Exception {
        String requestBody = "[{\"deptName\":\"development4\"}," +
                "{\"deptName\":\"development5\"}," +
                "{\"deptName\":\"development6\"}]";
        this.mockMvc.perform(post("/web/dept/depts") // 请求的url,请求的方法是post
                                                     .contentType(MediaType.APPLICATION_JSON_UTF8) // 数据的类型
                                                     .content(requestBody) // 内容
                                                     .accept(MediaType.APPLICATION_JSON_UTF8))
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andDo(MockMvcResultHandlers.print()) // 添加ResultHandler结果处理器，比如调试时打印结果到控制台（对返回的数据进行的判断）；
                    .andReturn(); //最后返回相应的MvcResult；然后进行自定义验证/进行下一步的异步处理（对返回的数据进行的判断）；

        //.andExpect(status().isOk())
        //.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        //.andDo(MockMvcResultHandlers.print())
        //.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
        //.andExpect(jsonPath("$.name").value("Lee"));

    }

    @Test
    public void testGetDept() throws Exception {
        this.mockMvc.perform(get("/web/dept/1"))
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn(); // 请求的url,请求的方法是post

    }

    @Test
    public void testDeleteDept() throws Exception {
        this.mockMvc.perform(delete("/web/dept/19"))
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn(); // 请求的url,请求的方法是post

    }

    @Test
    public void testDeleteDepts() throws Exception {
        // TODO

        this.mockMvc.perform(delete("/web/dept"))
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn(); // 请求的url,请求的方法是post

    }

    @Test
    public void testPutDept() throws Exception {
        this.mockMvc.perform(put("/web/dept/1"))
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn(); // 请求的url,请求的方法是post

    }

    @Test
    public void testDept() {
        RestTemplate restTemplate = new RestTemplate();
        //请求地址
        String url = "http://localhost:8080/rbac/users";
        //入参
        String deptId = "1";

        ResponseEntity<User> deptResponseEntity = restTemplate.getForEntity(url, User.class);

        User user = deptResponseEntity.getBody();
        System.out.println(user.toString());

    }


}
