
import com.service.DefectlistService;
import com.service.DefectrecordService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:dispatcher-servlet.xml"})
public class DefectlistTest {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private DefectlistService defectlistService;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private DefectrecordService defectrecordService;
    @Test
    public void test(){
        System.out.println(defectlistService.getDefectlist());
        String[] strings = {"ffsd", "fs"};

    }

    @Test
    public void test2()
    {
        String s = defectrecordService.productFPQ();
        System.out.println(s);
    }


}
