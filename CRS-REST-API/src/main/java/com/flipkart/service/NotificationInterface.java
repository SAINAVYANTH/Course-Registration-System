package com.flipkart.service;

import java.util.List;

import com.flipkart.bean.Notification;
import com.flipkart.constants.StatusConstants;

public interface NotificationInterface {
	public StatusConstants addNotification(Notification details);
	public List<Notification> getAllNotifications(String studentId);
	public List<Notification> getUnreadNotifications(String studentId);
	public StatusConstants markAsread(Notification details);
}
