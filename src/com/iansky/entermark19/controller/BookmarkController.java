package com.iansky.entermark19.controller;

import com.iansky.entermark19.entities.Bookmark;
import com.iansky.entermark19.entities.User;
import com.iansky.entermark19.manager.BookmarkManager;

public class BookmarkController {
	private static BookmarkController instance = new BookmarkController();
	private BookmarkController() {}
	
	public static BookmarkController getInstance() {
		return instance;
	}

	public void saveUserBookmark(User user, Bookmark bookmark) {
		BookmarkManager.getInstance().saveUserBookmark(user, bookmark);
	}
}
