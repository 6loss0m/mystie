package com.poscodx.web.mvc.user;

import com.poscodx.web.mvc.Action;
import com.poscodx.web.mvc.ActionFactory;
import com.poscodx.web.mvc.main.MainAction;

public class UserActionFactory implements ActionFactory {

	public Action getAction(String actionName) {
		Action action = null;
		if ("joinform".equals(actionName)) {
			action = new JoinFormAction();
		} else if ("join".equals(actionName)) {
			action = new JoinAction();
		} else if ("joinsuccess".equals(actionName)) {
			action = new JoinSuccessAction();
		} else if ("loginform".equals(actionName)) {
			action = new LoginFormAction();
		} else if ("login".equals(actionName)) {
			action = new LoginAction();
		} else if ("logout".equals(actionName)) {
			action = new LogoutAction();
		} else if ("updateform".equals(actionName)) {
			action = new UpdateformAction();
		} else if ("update".equals(actionName)) {
			action = new UpdateAction();
		}else {
			action = new MainAction();
		}
		return action;
	}

}
