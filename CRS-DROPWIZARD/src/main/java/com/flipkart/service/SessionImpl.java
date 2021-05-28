package com.flipkart.service;

import com.flipkart.constants.StatusConstants;
import com.flipkart.dao.SessionDao;
import com.flipkart.dao.SessionDaoInterface;

public class SessionImpl implements SessionInterface{
	
	private static volatile SessionImpl instance = null;
	
	private SessionImpl() {};
	
	public static SessionImpl getInstance() {
		if(instance==null) {
			synchronized(SessionImpl.class) {
				instance = new SessionImpl();
			}
		}
		return instance;
	}
	
	@Override
	public StatusConstants addSession(String userid, String sessionId, double expiry) {
		SessionDaoInterface sessiondao = SessionDao.getInstance();
		return sessiondao.addSession(userid, sessionId, expiry);
	}

	@Override
	public boolean checkSession(String sessionId) {
		SessionDaoInterface sessiondao = SessionDao.getInstance();
		return sessiondao.checkSession(sessionId);
	}

	@Override
	public StatusConstants removeSession(String sessionId) {
		SessionDaoInterface sessiondao = SessionDao.getInstance();
		return sessiondao.removeSession(sessionId);
	}

	@Override
	public String getUserFromSession(String sessionId) {
		SessionDaoInterface sessiondao = SessionDao.getInstance();
		return sessiondao.getUserFromSession(sessionId);
	}
	
}
