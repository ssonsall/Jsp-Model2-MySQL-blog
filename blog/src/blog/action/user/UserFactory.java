package blog.action.user;

import blog.action.Action;

public class UserFactory {
	public static Action getAction(String cmd) {
		switch (cmd) {
		case "login":
			return new UserLoginAction();
		case "join":
			return new UserJoinAction();
		case "logout":
			return new UserLogoutAction();
		case "update":
			return new UserUpdateAction();
		case "authUpdate":
			return new UserAuthUpdateAction();
		case "fromNavToEmailAuth" :
			return new UserFromNavToEmailAuth();
		}
		return null;
	}
}
