package ru.job4j.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

public class AlertRabbit {

    public static void main(String[] args) throws ClassNotFoundException {
        String propertiesFile = "rabbit.properties";
        Properties properties = getProperties(propertiesFile);
        String url = properties.getProperty("url");
        String login = properties.getProperty("username");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver-class-name");
        Class.forName(driver);
        try (Connection connect = DriverManager.getConnection(url, login, password)) {
            tryStatementBlock("create table if not exists rabbits(created_date timestamp)",
                    connect);
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            JobDataMap data = new JobDataMap();
            data.put("connection", connect);
            JobDetail job = newJob(Rabbit.class).usingJobData(data).build();
            SimpleScheduleBuilder times = simpleSchedule()
                    .withIntervalInSeconds(
                            Integer.parseInt(properties.getProperty("rabbit.interval")))
                    .repeatForever();
            Trigger trigger = newTrigger()
                    .startNow()
                    .withSchedule(times)
                    .build();
            scheduler.scheduleJob(job, trigger);
            Thread.sleep(10000);
            scheduler.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void tryStatementBlock(String sql, Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Properties getProperties(String filePath) {
        Properties prop = new Properties();
        try (InputStream in = AlertRabbit.class
                .getClassLoader()
                .getResourceAsStream(filePath)) {
            prop.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
       return prop;
    }

    public static class Rabbit implements Job {
        @Override
        public void execute(JobExecutionContext context) {
            tryStatementBlock("INSERT INTO rabbits(created_date) values (CURRENT_TIMESTAMP)",
                    (Connection) context
                    .getJobDetail()
                    .getJobDataMap().get("connection"));
            System.out.println("Rabbit runs here ...");
        }
    }
}