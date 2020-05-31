import com.lagou.edu.EmaliApplication;
import com.lagou.edu.service.EmailService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.Test;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EmaliApplication.class)
public class EmailTest {


    @Autowired
    private EmailService emailService;

    @Test
    public void EmailSend(){


        //boolean b = emailService.sendEmail("4766374@qq.com", "123456");


        //System.out.println(b);

    }


}
