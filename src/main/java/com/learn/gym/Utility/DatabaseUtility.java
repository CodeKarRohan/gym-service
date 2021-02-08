package com.learn.gym.Utility;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatabaseUtility {


    public static  boolean isGymNameValid(String gymName, Session session) {
        try{
            String hql = "FROM GymMetadata E WHERE E.gymName = :gym_name";
            Query query = session.createQuery(hql);
            query.setParameter("gym_name", gymName);
            List results = query.list();
            if (results.size() != 0){
                return false;
            }
        } catch(Exception e)
        {
            return true;
        }
        return true;

    }


}
