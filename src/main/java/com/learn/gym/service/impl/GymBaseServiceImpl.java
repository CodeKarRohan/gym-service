package com.learn.gym.service.impl;

import com.learn.gym.Utility.DatabaseUtility;
import com.learn.gym.Utility.ValidationUtility;
import com.learn.gym.db.entity.GymMetadata;
import com.learn.gym.service.GymBaseService;
import javassist.bytecode.stackmap.BasicBlock;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GymBaseServiceImpl implements GymBaseService {

    @Autowired
    SessionFactory factory;

    @Override
    public GymMetadata createGym(GymMetadata gym) {

        Transaction tx = null;
        Session session = factory.openSession();

        if ( null == gym ){
            return null;
        }

         if ( ValidationUtility.isStringNullOrEmpty(gym.getGymName()) ) {
             return null;
         }

         try{
             tx = session.beginTransaction();
             //check for name validity
             if (! DatabaseUtility.isGymNameValid(gym.getGymName(), session)) {
                 return null;
             }
             session.save(gym);
             tx.commit();
         } catch (Exception e) {
             if ( null != tx ) {
                 tx.rollback();
             }
         } finally {
              session.close();
         }
        return gym;
    }

    @Override
    public List<GymMetadata> listGym(Integer gymId) {
        Session session = factory.openSession();
        Transaction transaction = null;
        List<GymMetadata> listOfData = new ArrayList<>();

        String query = null;
        try {
            transaction = session.beginTransaction();
            Query hqlQuery = null;
            if (null == gymId) {
                query = " FROM GymMetadata";
                hqlQuery = session.createQuery(query);
            } else {
                query = "FROM GymMetadata gym where gym.gymId = : id";
                hqlQuery = session.createQuery(query);
                hqlQuery.setParameter("id", gymId);

            }
            List results = hqlQuery.list();
            for ( Object gym : results) {
                listOfData.add((GymMetadata) gym);

            }
            transaction.commit();

        } catch (HibernateException e) {
             if (transaction != null){
                 transaction.rollback();

             }

        }
        finally {
            session.close();
        }
        return listOfData;
    }

    @Override
    public void deleteGym(Integer gymId) {

    }

    @Override
    public GymMetadata updateGym(GymMetadata gym) {
        return null;
    }
}
