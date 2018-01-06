package orm;

// Fixed by Rafael Pernil Bronchalo

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Home object for domain model class ComicHasSerie.
 * 
 * @see .ComicHasSerie
 * @author Hibernate Tools
 */
public class ComicHasSerieHome {

	private static final Log log = LogFactory.getLog(ComicHasSerieHome.class);

	private SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			sessionFactory = new Configuration().configure().buildSessionFactory();
			return sessionFactory;
		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public void persist(ComicHasSerie transientInstance) {
		log.debug("persisting ComicHasSerie instance");
		try {
			Session session = sessionFactory.getCurrentSession();
			Transaction trans = session.beginTransaction();
			session.persist(transientInstance);
			trans.commit();
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(ComicHasSerie instance) {
		log.debug("attaching dirty ComicHasSerie instance");
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Transaction trans = session.beginTransaction();
			session.saveOrUpdate(instance);
			trans.commit();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ComicHasSerie instance) {
		log.debug("attaching clean ComicHasSerie instance");
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Transaction trans = session.beginTransaction();
			session.persist(instance);
			trans.commit();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(ComicHasSerie persistentInstance) {
		log.debug("deleting ComicHasSerie instance");
		try {
			Session session = sessionFactory.getCurrentSession();
			Transaction trans = session.beginTransaction();
			session.delete(persistentInstance);
			trans.commit();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ComicHasSerie merge(ComicHasSerie detachedInstance) {
		log.debug("merging ComicHasSerie instance");
		try {
			Session session = sessionFactory.getCurrentSession();
			Transaction trans = session.beginTransaction();
			ComicHasSerie result = (ComicHasSerie) session.merge(detachedInstance);
			trans.commit();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void deleteAll() {
		log.debug("getting list of ComicHasSeries");
		try {
			List<ComicHasSerie> list = this.list();
			for (ComicHasSerie ComicHasSerie : list) {
				this.delete(ComicHasSerie);
			}
			if (this.list().isEmpty()) {
				log.debug("everything deleted succesfully");
			} else {
				log.debug("there are some elements not deleted");
			}
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<ComicHasSerie> list() {
		log.debug("getting list of ComicHasSeries");
		try {
			Session session = sessionFactory.getCurrentSession();
			Transaction trans = session.beginTransaction();
			CriteriaQuery<ComicHasSerie> q = session.getCriteriaBuilder().createQuery(ComicHasSerie.class);
			q.select(q.from(ComicHasSerie.class));
			TypedQuery<ComicHasSerie> query = session.createQuery(q);
			List<ComicHasSerie> result = query.getResultList();
			trans.commit();
			if (result == null) {
				log.debug("get successful, no elements found");
			} else {
				log.debug("get successful, elements found");
			}
			return result;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public ComicHasSerie findById(ComicHasSerieId id) {
		log.debug("getting ComicHasSerie instance with id: " + id);
		try {
			Session session = sessionFactory.getCurrentSession();
			Transaction trans = session.beginTransaction();
			ComicHasSerie instance = (ComicHasSerie) session.get(ComicHasSerie.class, id);
			trans.commit();
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	@Deprecated
	public List<?> findByExample(ComicHasSerie instance) {
		log.debug("finding ComicHasSerie instance by example");
		try {
			Session session = sessionFactory.getCurrentSession();
			Transaction trans = session.beginTransaction();
			CriteriaQuery<ComicHasSerie> q = session.getCriteriaBuilder().createQuery(ComicHasSerie.class);
			q.select(q.from(instance.getClass()));
			TypedQuery<ComicHasSerie> query = session.createQuery(q);
			List<ComicHasSerie> results = query.getResultList();
			trans.commit();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}