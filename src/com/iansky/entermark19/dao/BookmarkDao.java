package com.iansky.entermark19.dao;

import com.iansky.entermark19.DataStore;
import com.iansky.entermark19.entities.Bookmark;

public class BookmarkDao {
	public Bookmark[][] getBookmarks(){
		return DataStore.getBookmarks();
	}
}
