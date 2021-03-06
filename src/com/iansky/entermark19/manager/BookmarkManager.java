package com.iansky.entermark19.manager;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import com.iansky.entermark19.dao.BookmarkDao;
import com.iansky.entermark19.entities.Book;
import com.iansky.entermark19.entities.Bookmark;
import com.iansky.entermark19.entities.Movie;
import com.iansky.entermark19.entities.User;
import com.iansky.entermark19.entities.UserBookmark;
import com.iansky.entermark19.entities.Weblink;
import com.iansky.entermark19.util.HttpConnect;
import com.iansky.entermark19.util.IOUtil;

public class BookmarkManager {
	private static BookmarkManager instance = new BookmarkManager();
	private static BookmarkDao dao = new BookmarkDao();

	private BookmarkManager() {
	}

	public static BookmarkManager getInstance() {
		return instance;
	}

	public Movie createMovie(long id, String title, String profileUrl, int releaseYear, String[] cast,
			String[] directors, String genre, double imdbRating) {
		Movie movie = new Movie();
		movie.setId(id);
		movie.setTitle(title);
		movie.setProfileUrl(profileUrl);
		movie.setReleaseYear(releaseYear);
		movie.setCast(cast);
		movie.setDirectors(directors);
		movie.setGenre(genre);
		movie.setImdbRating(imdbRating);

		return movie;
	}

	public Book createBook(long id, String title, int publicationYear, String publisher, String[] authors, String genre,
			double amazonRating) {
		Book book = new Book();
		book.setId(id);
		book.setTitle(title);
		book.setPublicationYear(publicationYear);
		book.setPublisher(publisher);
		book.setAuthors(authors);
		book.setGenre(genre);
		book.setAmazonRating(amazonRating);

		return book;
	}

	public Weblink createWeblink(long id, String title, String url, String host) {
		Weblink weblink = new Weblink();
		weblink.setId(id);
		weblink.setTitle(title);
		weblink.setUrl(url);
		weblink.setHost(host);

		return weblink;
	}

	public Bookmark[][] getBookmarks() {
		return dao.getBookmarks();
	}

	public void saveUserBookmark(User user, Bookmark bookmark) {
		UserBookmark userBookmark = new UserBookmark();
		userBookmark.setUser(user);
		userBookmark.setBookmark(bookmark);
		
		if(bookmark instanceof Weblink) {
			try {
				String url = ((Weblink) bookmark).getUrl();
				if(!url.endsWith(".pdf")) {
					String webpage = HttpConnect.download(((Weblink)bookmark).getUrl());
					if(webpage != null) {
						IOUtil.write(webpage, bookmark.getId());
					}
				}
			} catch (MalformedURLException e) {
				// TODO: handle exception
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		dao.saveUserBookmark(userBookmark);
	}

	public void setKidFriendlyStatus(User user, String kidFriendlyStatus, Bookmark bookmark) {
		bookmark.setKidFriendlyStatus(kidFriendlyStatus);
		bookmark.setKidFriendlyMarkedBy(user);
		System.out.println(
				"Kid-Friendly status: " + kidFriendlyStatus + " , Marked by " + user.getEmail() + ", " + bookmark);

	}

	public void share(User user, Bookmark bookmark) {
		bookmark.setShareBy(user);
		System.out.println("Data to be shared: ");
		if (bookmark instanceof Book) {
			System.out.println(((Book) bookmark).getItemData());
		} else if (bookmark instanceof Weblink) {
			System.out.println(((Weblink) bookmark).getItemData());
		}
	}
}
