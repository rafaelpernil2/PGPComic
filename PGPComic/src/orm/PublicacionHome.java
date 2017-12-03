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
 * Home object for domain model class Publicacion.
 * 
 * @see .Publicacion
 * @author Hibernate Tools
 */
public class PublicacionHome {

	private static final Log log = LogFactory.getLog(PublicacionHome.class);

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

	public void persist(Publicacion transientInstance) {
		log.debug("persisting Publicacion instance");
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

	public void attachDirty(Publicacion instance) {
		log.debug("attaching dirty Publicacion instance");
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

	public void attachClean(Publicacion instance) {
		log.debug("attaching clean Publicacion instance");
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

	public void delete(Publicacion persistentInstance) {
		log.debug("deleting Publicacion instance");
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

	public Publicacion merge(Publicacion detachedInstance) {
		log.debug("merging Publicacion instance");
		try {
			Session session = sessionFactory.getCurrentSession();
			Transaction trans = session.beginTransaction();
			Publicacion result = (Publicacion) session.merge(detachedInstance);
			trans.commit();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}
	public void deleteAll() {
		log.debug("getting list of Publicacions");
		try {
			List<Publicacion> list = this.list();
			for (Publicacion Publicacion : list) {
				this.delete(Publicacion);
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

	public List<Publicacion> list() {
		log.debug("getting list of Publicacions");
		try {
			Session session = sessionFactory.getCurrentSession();
			Transaction trans = session.beginTransaction();
			CriteriaQuery<Publicacion> q = session.getCriteriaBuilder().createQuery(Publicacion.class);
			q.select(q.from(Publicacion.class));
			TypedQuery<Publicacion> query = session.createQuery(q);
			List<Publicacion> result = query.getResultList();
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
	public Publicacion findById(PublicacionId id) {
		log.debug("getting Publicacion instance with id: " + id);
		try {
			Session session = sessionFactory.getCurrentSession();
			Transaction trans = session.beginTransaction();
			Publicacion instance = (Publicacion) session.get(Publicacion.class, id);
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
	public List<?> findByExample(Publicacion instance) {
		log.debug("finding Publicacion instance by example");
		try {
			Session session = sessionFactory.getCurrentSession();
			Transaction trans = session.beginTransaction();
			CriteriaQuery<Publicacion> q= session.getCriteriaBuilder().createQuery(Publicacion.class);
			q.select(q.from(Publicacion.class));
			TypedQuery<Publicacion> query =session.createQuery(q);
			List<Publicacion> results = query.getResultList();
			trans.commit();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}