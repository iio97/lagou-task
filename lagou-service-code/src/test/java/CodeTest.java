import com.lagou.edu.CodeApplication;
import com.lagou.edu.service.AuthCodeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CodeApplication.class)
public class CodeTest {


    @Autowired
    private AuthCodeService authCodeService;

    @Test
    public void Test(){




        System.out.println(1);

    }


}
