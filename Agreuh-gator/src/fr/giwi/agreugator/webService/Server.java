package fr.giwi.agreugator.webService;

import javax.xml.ws.Endpoint;

import fr.giwi.agreugator.blog.dao.BlogEntryManager;
import fr.giwi.agreugator.blog.dao.UserManager;
import fr.giwi.agreugator.rss.dao.RssEntryManager;

public class Server {
	public Server(final String srvUrl) throws Exception {
		System.out.println("Starting Server");

		// TODO : rendre le nom du host automatique
		// TODO : soit régler le conflit de ports, soit externaliser le serveur.
		final RssEntryManager rssmgr = new RssEntryManager();
		String rssmgrAddress = "http://localhost:8181" + srvUrl + "/services/RssEntryManager";
		Endpoint.publish(rssmgrAddress, rssmgr);

		final UserManager um = new UserManager();
		rssmgrAddress = "http://localhost:8181" + srvUrl + "/services/BlogUserManager";
		Endpoint.publish(rssmgrAddress, um);

		final BlogEntryManager bem = new BlogEntryManager();
		rssmgrAddress = "http://localhost:8181" + srvUrl + "/services/BlogEntryManager";
		Endpoint.publish(rssmgrAddress, bem);
	}

}
