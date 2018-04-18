package com.jsfd.microservice.auth.spring.security;

import com.lottery.auth.domain.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import javax.servlet.http.HttpSessionEvent;

public class EnhancedHttpSessionEventPublisher extends HttpSessionEventPublisher {

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		saveOrDeleteOnlineUser(event, Type.SAVE);
		super.sessionCreated(event);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		saveOrDeleteOnlineUser(event, Type.DELETE);
		super.sessionDestroyed(event);
	}

	public void saveOrDeleteOnlineUser(HttpSessionEvent event, Type type) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			Object principal = auth.getPrincipal();
			if (principal instanceof User) {
				User user = (User) principal;
				switch (type) {
				case SAVE:
					// OnlineUserList.add(user.getId);// List<String>
					System.out.println("################## sessionCreated" + user.getId() + "##################");
					break;
				case DELETE:
					// OnlineUserList.remove(user.getId);
					System.out.println("################## sessionDestroyed" + user.getId() + "##################");
					break;
				}
			}
		}
	}

	private static enum Type {
		SAVE, DELETE;
	}
}

