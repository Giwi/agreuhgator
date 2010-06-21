package fr.giwi.agreugator.helpers;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;

import fr.giwi.agreugator.entities.RSSEntry;

public class StoreHelper {

	/**
	 * @param p
	 * @param em
	 */
	public static void storeUpdate(final RSSEntry p, final EntityManager em) {
		if (em.find(RSSEntry.class, p.getId()) != null) {
			em.merge(p);
		} else {
			em.persist(p);
		}
		em.getFlushMode();
		em.setFlushMode(FlushModeType.COMMIT);
		em.flush();

	}

}
