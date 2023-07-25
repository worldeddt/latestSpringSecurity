package spring.security.schedule;


import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@EnableScheduling
public class ApplicationSchedule {

    private static final String basePath = "~/Downloads/heapdump";
    @Scheduled(cron = "0 0 0 * * *") //매일 자정
    public void readLogFile () throws IOException {
        Date d = new Date();
        d = new Date(d.getTime()+(1000*60*60*24*-1));
        SimpleDateFormat yesterday = new SimpleDateFormat("yyyy-MM-dd");

        String filePath = basePath+ yesterday.format(d)+".0.log";
        List<String> lines = Files.readAllLines(Paths.get(filePath),
                StandardCharsets.UTF_8);
    }
}
