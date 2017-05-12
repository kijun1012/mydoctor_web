package com.mydoctor.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mydoctor.model.UserCheckList;
import com.mydoctor.model.Weight;

@Repository
@Transactional
public class WeightDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public List<Weight> getAllWeight(String userId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Weight as w where w.username=:username");
		query.setParameter("username", userId);
		
		List<Weight> weightList = query.list();
		
		session.clear();
		return weightList;
	}
	
	
public List<String> addWeight(List<Weight> weightList,String userId) {
		
		Session session = sessionFactory.getCurrentSession();
		List<String> status = new ArrayList<String>();
		int lastCount = this.getAllWeight(userId).size();
		
		
		for (int i = 0; i < weightList.size(); i++) {
			
			session.saveOrUpdate("Weight",weightList.get(i));
		}
		
		session.flush();
		session.clear();
		
		int allDataCount = this.getAllWeight(userId).size();
		
		
		
		if ((allDataCount-lastCount) > 0){
			System.out.println("드루와두르와");
			UserCheckList userCheckList = (UserCheckList)session.get(UserCheckList.class,userId);
			userCheckList.setWeight(weightList.get(weightList.size()-1).getWeightValue());
			session.saveOrUpdate("UserCheckList",userCheckList);
			session.flush();
			session.clear();
		}
		
		status.add(Integer.toString(allDataCount));
		status.add(Integer.toString(allDataCount-lastCount));
		status.add(Integer.toString(lastCount));
		return status;
	}

}
