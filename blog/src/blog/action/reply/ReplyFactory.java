package blog.action.reply;

import blog.action.Action;

public class ReplyFactory {
	public static Action getAction(String cmd) {		
		switch(cmd) {
		case "write" :
			return new ReplyWriteAction();
		case "delete" :
			return new ReplyDeleteAction();
		case "list" :
			return new ReplyListAction();
		}
		return null;
	}
}
