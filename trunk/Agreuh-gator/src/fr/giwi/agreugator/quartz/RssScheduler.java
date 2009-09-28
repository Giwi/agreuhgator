package fr.giwi.agreugator.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import fr.giwi.agreugator.helpers.LuceneHelper;
import fr.giwi.agreugator.helpers.RSSHelper;

public class RssScheduler implements Job {

	@Override
	public void execute(final JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("Execution du checkout des flux");
		LuceneHelper.index(RSSHelper.getRSSContent());
	}

}
