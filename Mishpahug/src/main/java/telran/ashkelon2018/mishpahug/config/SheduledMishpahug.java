package telran.ashkelon2018.mishpahug.config;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import telran.ashkelon2018.mishpahug.dao.EventInProgresRepository;
import telran.ashkelon2018.mishpahug.dao.SettingRepository;
import telran.ashkelon2018.mishpahug.domain.EventForWork;
import telran.ashkelon2018.mishpahug.domain.SettingsProject;
import telran.ashkelon2018.mishpahug.utils.DateUtils;

@Configuration
@EnableScheduling
public class SheduledMishpahug implements SchedulingConfigurer{

	@Autowired
	SettingRepository settingRepository;
	@Autowired
	EventInProgresRepository eventInProgresRepository;

	@Autowired
	NotificationsService notificationsService;
		
	
	@Override 	
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskScheduler());
        
//        taskRegistrar.addTriggerTask(
//            new Runnable() {
//                public void run() {
//                		System.out.println("THREAD#1  ");
//                       	nigthTask();
//                }
//            },new CronTrigger("*/18 * * * * *") );
        
        
            //new CronTrigger("*/18 * * * * *") );
        
//        taskRegistrar.addTriggerTask(
//                new Runnable() {
//                    public void run() {
//                    	System.out.println("THREAD#2  ");
//                        eventDone();
//                    }
//                }, new CronTrigger("*/10 * * * * *") );
        
//        taskRegistrar.addFixedRateTask(
//                new Runnable() {
//                    public void run() {
//                    	System.out.println("THREAD#3  ");
//                    	mishpahugsSettingRun();
//                    }
//                },1000*60*60*24*365);
        
//        taskRegistrar.addTriggerTask(
//                new Runnable() {
//                    public void run() {
//                    	System.out.println("THREAD#4  "+ taskRegistrar.getTriggerTaskList().size());
//                        Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
//                        for ( Thread t : threadSet){
//                            System.out.println("Thread :"+t+":"+"state:"+t.getState());
//                        }
	}

    @Bean
    (destroyMethod="shutdown")
    public Executor taskScheduler() {
        return Executors.newScheduledThreadPool(8);
    }
    
    
   // @Transactional(readOnly = true)
   // @Bean()
    public void nigthTask() {
    	System.out.println("Start Daemon Nigth " + LocalDateTime.now() );
    	try {
    		
			long timeTemp = DateUtils.localDateTimeToMills(LocalDateTime.now())+1000*60*60*19+500;
			 Set<EventForWork> eventsBad=eventInProgresRepository.findAllBadEvent(timeTemp);//,Status.in_process);
			System.out.println("Poluchili set" + eventsBad );
			System.out.println(eventsBad==null);
			 for (EventForWork eventForWork : eventsBad) {
				 System.out.println( eventForWork + "elemBad ");	
			}	
//			 eventsBad.forEach(elem-> {
//				 System.out.println( elem + "elemBad ");	
//			 });
			 notificationsService.workWithBadEvents(eventsBad) ;
			 eventInProgresRepository.deleteAll(eventsBad);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException("DAEMON BAD EVENT EXCEPCTION");
			
		}
    	 System.out.println("End Daemon Nigth " + LocalDateTime.now() );
    }
    
    //@Transactional(readOnly = true)
    public void eventDone() {
    	System.out.println("Start Daemon Check Done Start " + LocalDateTime.now());
    	 try {
			long timeTemp = DateUtils.localDateTimeToMills(LocalDateTime.now());
			 HashSet<EventForWork> eventsDone=eventInProgresRepository.findAllDoneEvent(timeTemp); //,Status.pending
			 System.out.println(eventsDone==null);
			 eventsDone.forEach(elem-> {
				 System.out.println( elem + "elemDone ");	
			 });
			 notificationsService.workWithDoneEvents(eventsDone) ;
			 eventInProgresRepository.deleteAll(eventsDone);
		} catch (Exception e) {
			throw new RuntimeException("DAEMON DONE EVENT EXCEPCTION");
		} 
    	 System.out.println("End Daemon Check Done finish " + LocalDateTime.now());
    }	
    	 
    public void mishpahugsSettingRun()  {
	System.out.println("Start Setting Thread "  + LocalDateTime.now());
	System.out.println("Count "+ settingRepository.count());
	if (settingRepository.findById("countEventAll").orElse(null)==null) {
		System.out.println("Change Settings First");
		SettingsProject settings = new SettingsProject("countEventAll",0);
		settingRepository.saveAndFlush(settings);
		System.out.println("End Change Settings First");
	}
		System.out.println("End Setting Thread "  + LocalDateTime.now());
	}
    
    public Date nextExecutionTimeForUpdate(TriggerContext triggerContext) {
        Calendar nextExecutionTime =  new GregorianCalendar();
        Date lastActualExecutionTime = triggerContext.lastActualExecutionTime();
        nextExecutionTime.setTime(lastActualExecutionTime != null ? 
        		lastActualExecutionTime :  Date.from(LocalDate.now().atStartOfDay(ZoneId.of("UTC+2")).toInstant()));
        nextExecutionTime.add(Calendar.MILLISECOND, 60000);
        return nextExecutionTime.getTime();
    }
   
}