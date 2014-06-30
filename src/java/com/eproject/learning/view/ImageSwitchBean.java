/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eproject.learning.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.event.SlideEndEvent;

/**
 *
 * @author luan
 */
@ManagedBean
public class ImageSwitchBean {
    private Map<String, Integer>images;
    private Map<Integer, String>urls;
    private Map<Integer, String>titles;

    public ImageSwitchBean() {
        images=new HashMap<>();
        urls=new HashMap<>();
        titles=new HashMap<>();
        images.put("img1.jpg", 1);
        urls.put(1, "http://www.facebook.com");
        titles.put(1, "Facebook");
        images.put("img2.jpg", 2);
        urls.put(2, "http://www.google.com");
        titles.put(2, "Google");
        images.put("img3.jpg", 3);
        urls.put(3, "http://www.flickr.com");
        titles.put(3, "Flickr");
        images.put("img4.jpg", 4);
        urls.put(4, "http://www.aptech.vn");
        titles.put(4, "Aptech");
    }
    

    public Object[] getImages() {
        return images.keySet().toArray();
    }
    
    public String getUrl(String image){
        return urls.get(images.get(image));
    }
    public String getTitle(String image){
        return titles.get(images.get(image));
    }
    public String getEffec(){
        return "fade";
    }
}
