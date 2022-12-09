package com.scanit.client;

public interface Photo {

	/*
	 * private int albumId; private int id; private String title; private String
	 * url; private String thumbnailUrl;
	 */
	public int getAlbumId();
	public void setAlbumId(int albumId);
	public int getId();
	public void setId(int id) ;
	public String getTitle();
	public void setTitle(String title);
	public String getUrl();
	public void setUrl(String url);
	public String getThumbnailUrl();
	public void setThumbnailUrl(String thumbnailUrl);
	
	
	
}
