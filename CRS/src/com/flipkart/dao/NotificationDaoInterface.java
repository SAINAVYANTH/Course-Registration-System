package com.flipkart.dao;

import java.util.List;

import com.flipkart.bean.Notification;
import com.flipkart.constants.StatusConstants;

public interface NotificationDaoInterface {
	public List<Notification> getAllNotifications(String studentId);
	public List<Notification> getUnreadNotifications(String studentId);
	public StatusConstants addNotification(Notification details);
	public StatusConstants markAsRead(Notification details);
}
