package com.iansky.entermark19.dao;

import com.iansky.entermark19.DataStore;
import com.iansky.entermark19.entities.Bookmark;
import com.iansky.entermark19.entities.UserBookmark;

public class BookmarkDao {
	public Bookmark[][] getBookmarks(){
		return DataStore.getBookmarks();
	}

	public void saveUserBookmark(UserBookmark userBookmark) {
		DataStore.add(userBookmark);
	}
}
