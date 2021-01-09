/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.park.easyrecruit.common;

import com.park.easyrecruit.entity.Application;
import com.park.easyrecruit.entity.ApplicationComment;
import java.util.*;

/**
 *
 * @author andrei
 */
public class ApplicationDetails {
    
    private String cvLink;
    private Collection<ApplicationComment> comments = new ArrayList<>();
    
    public static ApplicationDetails From(Application a) {
        ApplicationDetails ad = new ApplicationDetails();
        ad.cvLink = a.getCvLink();
        ad.comments = new ArrayList<>(a.getComments());
        return ad;
    }

    public Collection<ApplicationComment> getComments() {
        return comments;
    }

    public String getCvLink() {
        return cvLink;
    }
}
