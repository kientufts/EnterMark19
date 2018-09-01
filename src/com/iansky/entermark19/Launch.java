package com.iansky.entermark19;

import com.iansky.entermark19.entities.Bookmark;
import com.iansky.entermark19.entities.User;
import com.iansky.entermark19.manager.BookmarkManager;
import com.iansky.entermark19.manager.UserManager;

public class Launch {
	private static User[] users;
	private static Bookmark[][] bookmarks;

	public static void loadData() {
		System.out.println("1. Loading data ...");
		DataStore.loadData();

		users = UserManager.getInstance().getUsers();
		bookmarks = BookmarkManager.getInstance().getBookmarks();

		//System.out.println("Priting data ...");
		//printUserData();
		//printBookmarkData();
	}

	private static void printBookmarkData() {
		for (Bookmark[] bookmarkList : bookmarks) {
			for (Bookmark bookmark : bookmarkList) {
				System.out.println(bookmark);
			}
		}
	}

	private static void printUserData() {
		for (User user : users) {
			System.out.println(user);
		}
	}

	private static void start() {
		//System.out.println("\n 2. Bookmarking ...");
		for(User user: users) {
			View.browse(user, bookmarks);
		}
	}

	public static void main(String[] args) {
		loadData();
		start();
	}

}
