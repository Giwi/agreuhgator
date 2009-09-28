package fr.giwi.agreugator.listener;

import java.io.File;
import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerUtils;

import fr.giwi.agreugator.constantes.Constantes;
import fr.giwi.agreugator.quartz.RssScheduler;

/**
 * Application Lifecycle Listener implementation class ContextListener
 * 
 */
public class ContextListener implements ServletContextListener {

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(final ServletContextEvent servletContext) {
		Constantes.LucenePath = servletContext.getServletContext().getRealPath(".") + "/Lucene/Indexes";
		final File root = new File(Constantes.LucenePath);
		if (!root.exists()) {
			root.mkdirs();
		}
		System.out.println(root.getAbsolutePath());
		try {
			final SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();

			final Scheduler sched = schedFact.getScheduler();

			sched.start();

			final JobDetail jobDetail = new JobDetail("myJob", null, RssScheduler.class);

			final Trigger trigger = TriggerUtils.makeHourlyTrigger();
			trigger.setStartTime(TriggerUtils.getEvenHourDate(new Date()));
			trigger.setName("myTrigger");
			final Trigger trigger2 = TriggerUtils.makeImmediateTrigger(0, 1);
			trigger2.setName("myTrigger2");
			sched.scheduleJob(jobDetail, trigger2);
		} catch (final SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// LuceneHelper.index(RSSHelper.getRSSContent());
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(final ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	}

}
