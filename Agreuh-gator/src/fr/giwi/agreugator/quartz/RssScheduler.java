package fr.giwi.agreugator.quartz;

import java.io.IOException;

import net.sourceforge.pbeans.StoreException;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.sun.syndication.io.FeedException;

import fr.giwi.agreugator.helpers.LuceneHelper;
import fr.giwi.agreugator.helpers.RSSHelper;

public class RssScheduler implements Job {

	@Override
	public void execute(final JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("Execution du checkout des flux");
		try {
			LuceneHelper.purgeIndex();
			LuceneHelper.index(RSSHelper.getRSSContent());
		} catch (final IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final FeedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final StoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
