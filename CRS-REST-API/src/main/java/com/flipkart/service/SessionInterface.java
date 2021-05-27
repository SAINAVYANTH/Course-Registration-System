package com.flipkart.service;

import com.flipkart.constants.StatusConstants;

public interface SessionInterface {
	public StatusConstants addSession(String userid, String sessionId, double expiry);
	public boolean checkSession(String sessionId);
	public StatusConstants removeSession(String sessionId);
	public String getUserFromSession(String sessionId);
}
