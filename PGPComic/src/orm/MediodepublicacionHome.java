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
 * Home object for domain model class Mediodepublicacion.
 * 
 * @see .Mediodepublicacion
 * @author Hibernate Tools
 */
public class MediodepublicacionHome {

	private static final Log log = LogFactory.getLog(MediodepublicacionHome.class);

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

	public void persist(Mediodepublicacion transientInstance) {
		log.debug("persisting Mediodepublicacion instance");
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

	public void attachDirty(Mediodepublicacion instance) {
		log.debug("attaching dirty Mediodepublicacion instance");
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

	public void attachClean(Mediodepublicacion instance) {
		log.debug("attaching clean Mediodepublicacion instance");
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

	public void delete(Mediodepublicacion persistentInstance) {
		log.debug("deleting Mediodepublicacion instance");
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

	public Mediodepublicacion merge(Mediodepublicacion detachedInstance) {
		log.debug("merging Mediodepublicacion instance");
		try {
			Session session = sessionFactory.getCurrentSession();
			Transaction trans = session.beginTransaction();
			Mediodepublicacion result = (Mediodepublicacion) session.merge(detachedInstance);
			trans.commit();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}
	
	
	public void deleteAll() {
		log.debug("getting list of Mediodepublicacions");
		try {

			List<Mediodepublicacion> list = this.list();
			for (Mediodepublicacion Mediodepublicacion : list) {
				this.delete(Mediodepublicacion);
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

	public List<Mediodepublicacion> list() {
		log.debug("getting list of Mediodepublicacions");
		try {
			Session session = sessionFactory.getCurrentSession();
			Transaction trans = session.beginTransaction();
			CriteriaQuery<Mediodepublicacion> q = session.getCriteriaBuilder().createQuery(Mediodepublicacion.class);
			q.select(q.from(Mediodepublicacion.class));
			TypedQuery<Mediodepublicacion> query = session.createQuery(q);
			List<Mediodepublicacion> result = query.getResultList();
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
	
	public Mediodepublicacion findById(String id) {
		log.debug("getting Mediodepublicacion instance with id: " + id);
		try {
			Session session = sessionFactory.getCurrentSession();
			Transaction trans = session.beginTransaction();
			Mediodepublicacion instance = (Mediodepublicacion) session.get(Mediodepublicacion.class, id);
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
	public List<?> findByExample(Mediodepublicacion instance) {
		log.debug("finding Mediodepublicacion instance by example");
		try {
			Session session = sessionFactory.getCurrentSession();
			Transaction trans = session.beginTransaction();
			CriteriaQuery<Mediodepublicacion> q= session.getCriteriaBuilder().createQuery(Mediodepublicacion.class);
			q.select(q.from(Mediodepublicacion.class));
			TypedQuery<Mediodepublicacion> query =session.createQuery(q);
			List<Mediodepublicacion> results = query.getResultList();
			trans.commit();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}