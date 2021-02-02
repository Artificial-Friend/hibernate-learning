package foxtrot.uniform.charlie.kilo.dao.implementations;

import foxtrot.uniform.charlie.kilo.dao.MovieSessionDao;
import foxtrot.uniform.charlie.kilo.exception.DataProcessingException;
import foxtrot.uniform.charlie.kilo.lib.Dao;
import foxtrot.uniform.charlie.kilo.model.MovieSession;
import foxtrot.uniform.charlie.kilo.util.HibernateUtil;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class MovieSessionDaoImpl implements MovieSessionDao {
    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<MovieSession> query = session.createQuery("SELECT ms FROM MovieSession ms "
                    + "LEFT JOIN FETCH ms.cinemaHall "
                    + "LEFT JOIN FETCH ms.movie "
                    + "WHERE ms.movie.id = :movieId "
                    + "AND TO_CHAR(ms.showTime, 'YYYY-MM-DD') = :date ",
                    MovieSession.class);
            query.setParameter("movieId", movieId);
            query.setParameter("date", DateTimeFormatter.ISO_LOCAL_DATE.format(date));
            return query.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("ERROR: can't find available sessions with id "
                    + movieId + " and date " + date, e);
        }
    }

    @Override
    public MovieSession add(MovieSession movieSession) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(movieSession);
            transaction.commit();
            return movieSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("ERROR: can't add movieSession " + movieSession, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}