package com.scanit.shared;

import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;


public interface PhotoFactory extends AutoBeanFactory{
	 AutoBean<Photo> photoView();
}
