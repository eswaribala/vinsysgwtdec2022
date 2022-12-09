package com.scanit.client;

import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;

interface PhotoFactory extends AutoBeanFactory {
	
	 AutoBean<Photo> photoView();
	}