package ru.job4j.grabber;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.*;
import java.util.List;
import java.util.Properties;

import ru.job4j.grabber.utils.Post;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class Grabber implements Grab {
    private final Properties cfg = new Properties();

    public Store store() {
        Store store = null;
        try {
            store = new PsqlStore(cfg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return store;
    }

    public Scheduler scheduler() throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();
        return scheduler;
    }

    public void cfg() {
        try (InputStream in = new FileInputStream("app.properties")) {
            cfg.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init(Parse parse, Store store, Scheduler scheduler) {
        JobDataMap data = new JobDataMap();
        data.put("store", store);
        data.put("parse", parse);
        data.put("linkToParse", cfg.getProperty("linkToParse"));
        JobDetail job = newJob(GrabJob.class)
                .usingJobData(data)
                .build();
        SimpleScheduleBuilder times = simpleSchedule()
                .withIntervalInSeconds(Integer.parseInt(cfg.getProperty("parsePeriodTime")))
                .repeatForever();
        Trigger trigger = newTrigger()
                .startNow()
                .withSchedule(times)
                .build();
        try {
            scheduler.scheduleJob(job, trigger);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class GrabJob implements Job {

        @Override
        public void execute(JobExecutionContext context) {
            JobDataMap map = context.getJobDetail().getJobDataMap();
            Store store = (Store) map.get("store");
            Parse parse = (Parse) map.get("parse");
            // В проект можно добавить новые сайты без изменения кода.
            String linkToParse = (String) map.get("linkToParse");
            List<Post> posts = parse.list(linkToParse);
            for (var pst : posts) {
                Post post = parse.detail(pst.getLink());
                if (post.getName().contains("java") || post.getDescription().contains("java")) {
                    //Программа должна считывать все вакансии
                    // относящиеся к Java и записывать их в базу.
                    store.save(post);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        /* Что бы сделать параллельный парсинг сайтов
        вот это вот нужно запускать в отдельный поток.
        Этого я делать пока не умею.
         */
        Grabber grab = new Grabber();
        grab.cfg();
        Scheduler scheduler = grab.scheduler();
        Store store = grab.store();
        grab.init(new SqlRuParse(), store, scheduler);
    }
}