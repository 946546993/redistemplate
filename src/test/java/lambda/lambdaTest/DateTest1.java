package lambda.lambdaTest;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DateTest1 {
    public static void main(String[] args) {
        DateTest1 newDate = new DateTest1();
        newDate.testZonedDateTime();
    }

    public void testZonedDateTime() {
        // 获取当前时间日期
        //还是只能获取时间
        ZonedDateTime date1 = ZonedDateTime.parse("2015-12-03T10:15:30+05:30[Asia/Shanghai]");
        System.out.println("date1: " + date1);
        ZoneId id = ZoneId.of("Europe/Paris");
        System.out.println("ZoneId: " + id);
        ZoneId currentZone = ZoneId.systemDefault();
        System.out.println("当期时区: " + currentZone);
    }
}
