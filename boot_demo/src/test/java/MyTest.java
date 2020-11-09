import com.boot.example.BootDemoApplication;
import com.boot.example.entity.User;
import com.boot.example.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
/**
 * @author shenwenwen
 * @date 2020/9/2
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BootDemoApplication.class})
public class MyTest {

    protected static final Logger logger = LoggerFactory.getLogger(BootDemoApplication.class);
    @Autowired
    UserService userService;

   @Test
    public void findAllUsers() throws Exception{
        if(userService.findAllUsers().size()!=0){
        }else {
            System.out.println("没有数据!");

        }
    }

}
