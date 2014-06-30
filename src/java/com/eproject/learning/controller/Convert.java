/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eproject.learning.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author luan
 * @param <T>
 */
public class Convert<T> {
    public List<T> setToList(Set<T> set){
        List<T> ls=new ArrayList<>();
        if(set.size() <1)
            return null;
        for(T o:set){
            ls.add(o);
        }
        return ls;
    }
    public Set<T> listToSet(List<T> ls){
        Set<T> set=new HashSet<>();
        if(ls.size() <1)
            return null;
        for(T o:ls){
            set.add(o);
        }
        return set;
    }
    
    
}
