package publiceTest;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class LocalDateTimeTest {

    @Test
    public void test()
    {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime localDateTime = now.minusHours(95);
        System.out.println(localDateTime);
        System.out.println(now.compareTo(localDateTime));
    }
}
