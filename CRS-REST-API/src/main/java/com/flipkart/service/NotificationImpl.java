package com.flipkart.service;

import java.util.List;

import com.flipkart.bean.Notification;
import com.flipkart.constants.StatusConstants;
import com.flipkart.dao.NotificationDao;
import com.flipkart.dao.NotificationDaoInterface;

public class NotificationImpl implements NotificationInterface{
	private static NotificationImpl instance = null;
	
	private NotificationImpl() {};
	
	public static NotificationImpl getInstance() {
		if(instance==null) {
			synchronized(NotificationImpl.class) {
				instance = new NotificationImpl();
			}
		}
		return instance;
	}

	@Override
	public StatusConstants addNotification(Notification details) {
		NotificationDaoInterface notificationDao = NotificationDao.getInstance();
		return notificationDao.addNotification(details);
	}

	@Override
	public List<Notification> getAllNotifications(String studentId) {
		NotificationDaoInterface notificationDao = NotificationDao.getInstance();
		return notificationDao.getAllNotifications(studentId);
	}

	@Override
	public List<Notification> getUnreadNotifications(String studentId) {
		NotificationDaoInterface notificationDao = NotificationDao.getInstance();
		return notificationDao.getUnreadNotifications(studentId);
	}

	@Override
	public StatusConstants markAsread(Notification details) {
		NotificationDaoInterface notificationDao = NotificationDao.getInstance();
		return notificationDao.markAsRead(details);
	}

}
