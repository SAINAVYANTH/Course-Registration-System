package com.flipkart.dao;

import com.flipkart.constants.StatusConstants;

public interface SessionDaoInterface {
	public StatusConstants addSession(String userid, String sessionId, double expiry);
	public boolean checkSession(String sessionId);
	public StatusConstants removeSession(String sessionId);
	public String getUserFromSession(String sessionId);
}
