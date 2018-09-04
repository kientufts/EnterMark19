package com.iansky.entermark19;

import com.iansky.entermark19.constants.Gender;
import com.iansky.entermark19.entities.Bookmark;
import com.iansky.entermark19.entities.User;
import com.iansky.entermark19.entities.UserBookmark;
import com.iansky.entermark19.manager.BookmarkManager;
import com.iansky.entermark19.manager.UserManager;
import com.iansky.entermark19.util.IOUtil;

public class DataStore {
	public static final int USER_BOOKMARK_LIMIT = 5;
	public static final int BOOKMARK_COUNT_PER_TYPE = 5;
	public static final int BOOKMARK_TYPE_COUNT = 3;
	public static final int TOTAL_USER_COUNT = 5;
	private static User[] users = new User[TOTAL_USER_COUNT];
	private static Bookmark[][] bookmarks = new Bookmark[BOOKMARK_TYPE_COUNT][BOOKMARK_COUNT_PER_TYPE];
	private static UserBookmark[] userBookmarks = new UserBookmark[TOTAL_USER_COUNT * USER_BOOKMARK_LIMIT];
	private static int bookmarkIndex;

	public static void loadData() {
		loadUsers();
		loadWebLinks();
		loadMovies();
		loadBooks();
	}

	private static void loadUsers() {

		String[] data = new String[TOTAL_USER_COUNT];
		IOUtil.read(data, "User.txt");
		int count = 0;
		for (String row : data) {
			String[] values = row.split("\t");

			int gender = Gender.MALE;
			if (values[5].equals("f")) {
				gender = Gender.FEMALE;
			} else if (values[5].equals("t")) {
				gender = Gender.TRANSGENDER;
			}

			users[count++] = UserManager.getInstance().createUser(Long.parseLong(values[0]), values[1], values[2],
					values[3], values[4], gender, values[6]);
		}
	}

	private static void loadWebLinks() {
		String[] data = new String[BOOKMARK_COUNT_PER_TYPE];
		IOUtil.read(data, "Web-Link.txt");
		int colNum = 0;
		for (String row : data) {
			String[] values = row.split("\t");
			bookmarks[0][colNum++] = BookmarkManager.getInstance().createWeblink(Long.parseLong(values[0]), values[1],
					values[2], values[3]);
		}
	}

	private static void loadMovies() {
		String[] data = new String[BOOKMARK_COUNT_PER_TYPE];
		IOUtil.read(data, "Movie.txt");
		int colNum = 0;
		for (String row : data) {
			String[] values = row.split("\t");
			String[] cast = values[3].split(",");
			String[] directors = values[4].split(",");
			bookmarks[1][colNum++] = BookmarkManager.getInstance().createMovie(Long.parseLong(values[0]), values[1], "",
					Integer.parseInt(values[2]), cast, directors, values[5], Double.parseDouble(values[6]));
		}
	}

	private static void loadBooks() {
		String[] data = new String[BOOKMARK_COUNT_PER_TYPE];
		IOUtil.read(data, "Book.txt");
		int colNum = 0;
		for (String row : data) {
			String[] values = row.split("\t");
			String[] authors = values[4].split(",");
			bookmarks[2][colNum++] = BookmarkManager.getInstance().createBook(Long.parseLong(values[0]), values[1],
					Integer.parseInt(values[2]), values[3], authors, values[5], Double.parseDouble(values[6]));
		}
	}

	public static User[] getUsers() {
		return users;
	}

	public static Bookmark[][] getBookmarks() {
		return bookmarks;
	}

	public static void add(UserBookmark userBookmark) {
		userBookmarks[bookmarkIndex++] = userBookmark;
	}

}
