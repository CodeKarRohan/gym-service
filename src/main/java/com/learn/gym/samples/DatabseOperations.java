package com.learn.gym.samples;

import com.learn.gym.db.entity.GymMetadata;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class DatabseOperations {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private EntityManager entityManager;

    public GymMetadata createGymMetadata() {
        Session session = sessionFactory.openSession();
        GymMetadata gym1 = new GymMetadata();
       // gym1.setGymId(1);
        gym1.setGymName("Rohan Gym");
        //gym1.setGymRegDate(parseTimestamp("2020-05-01 12:30:00"));
        session.beginTransaction();
        session.save(gym1);
        session.getTransaction().commit();
        session.close();
        return gym1;

    }

    public GymMetadata UpdateGymMetadata() {

        Session session = sessionFactory.openSession();
        Transaction tx = null;
        GymMetadata gym = null;

        try{
            tx = session.beginTransaction();
             gym = (GymMetadata) session.get(GymMetadata.class, 1);
            gym.setGymName("updated name ");
            session.update(gym);
            tx.commit();
        } catch (Exception e) {
            if (null != tx ) {
                tx.rollback();
            }
        }
        finally {
            session.close();
        }

        return gym;

    }

    public void deleteTheData() {
        Session session = sessionFactory.openSession();
        Transaction tx =  null;
        try {
            tx = session.beginTransaction();
            GymMetadata gym = (GymMetadata)session.get(GymMetadata.class, 2);
            session.delete(gym);
            tx.commit();
        }catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("error is : "+ e.getMessage());
        } finally {
            session.close();
        }
    }

    public List<GymMetadata> listAllEntity() {

        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List<GymMetadata> listOfGyms = new ArrayList<>();

        try{
            tx = session.beginTransaction();
            List gyms = session.createQuery(" FROM GymMetadata ").list();

            System.out.println(" liststst "+gyms);
            for (Iterator iterator = gyms.iterator(); iterator.hasNext();) {

                listOfGyms.add((GymMetadata)iterator.next());

            }
            tx.commit();

        } catch( HibernateException e) {
            if( null != tx) {
                tx.rollback();
            }

        } finally {
            session.close();
        }
        return listOfGyms;
    }

    private static java.sql.Timestamp parseTimestamp(String timestamp) {
        try {
            return new Timestamp(DATE_TIME_FORMAT.parse(timestamp).getTime());
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
