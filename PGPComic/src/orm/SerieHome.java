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
 * Home object for domain model class Serie.
 * 
 * @see .Serie
 * @author Hibernate Tools
 */
public class SerieHome {

	private static final Log log = LogFactory.getLog(SerieHome.class);

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

	public void persist(Serie transientInstance) {
		log.debug("persisting Serie instance");
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

	public void attachDirty(Serie instance) {
		log.debug("attaching dirty Serie instance");
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

	public void attachClean(Serie instance) {
		log.debug("attaching clean Serie instance");
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

	public void delete(Serie persistentInstance) {
		log.debug("deleting Serie instance");
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

	public Serie merge(Serie detachedInstance) {
		log.debug("merging Serie instance");
		try {
			Session session = sessionFactory.getCurrentSession();
			Transaction trans = session.beginTransaction();
			Serie result = (Serie) session.merge(detachedInstance);
			trans.commit();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void deleteAll() {
		log.debug("getting list of Series");
		try {

			List<Serie> list = this.list();
			for (Serie Serie : list) {
				this.delete(Serie);
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

	public List<Serie> list() {
		log.debug("getting list of Series");
		try {
			Session session = sessionFactory.getCurrentSession();
			Transaction trans = session.beginTransaction();
			CriteriaQuery<Serie> q = session.getCriteriaBuilder().createQuery(Serie.class);
			q.select(q.from(Serie.class));
			TypedQuery<Serie> query = session.createQuery(q);
			List<Serie> result = query.getResultList();
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

	public Serie findById(int id) {
		log.debug("getting Serie instance with id: " + id);
		try {
			Session session = sessionFactory.getCurrentSession();
			Transaction trans = session.beginTransaction();
			Serie instance = (Serie) session.get(Serie.class, id);
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
	public List<?> findByExample(Serie instance) {
		log.debug("finding Serie instance by example");
		try {
			Session session = sessionFactory.getCurrentSession();
			Transaction trans = session.beginTransaction();
			CriteriaQuery<Serie> q = session.getCriteriaBuilder().createQuery(Serie.class);
			q.select(q.from(Serie.class));
			TypedQuery<Serie> query = session.createQuery(q);
			List<Serie> results = query.getResultList();
			trans.commit();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}