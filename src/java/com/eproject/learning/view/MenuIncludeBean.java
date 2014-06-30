/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eproject.learning.view;

import com.eproject.learning.authentication.Authentication;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author luan
 */
@ManagedBean
public class MenuIncludeBean {

    private List<MenuBean> menus;

    public MenuIncludeBean() {
        initMenus();
    }

    private void initMenus() {
        menus = new ArrayList<>();
        // Menu top
        menus.add(new MenuBean("Home", "home", MenuBean.MENU_TOP, MenuBean.ROLE_ALL));
        menus.add(new MenuBean("Course", "courses", MenuBean.MENU_TOP, MenuBean.ROLE_ALL));
        menus.add(new MenuBean("News", "view-news", MenuBean.MENU_TOP, MenuBean.ROLE_ALL));
        menus.add(new MenuBean("Contact", "contact", MenuBean.MENU_TOP, MenuBean.ROLE_ALL));
        menus.add(new MenuBean("About", "about", MenuBean.MENU_TOP, MenuBean.ROLE_ALL));
        menus.add(new MenuBean("Study", "manager", MenuBean.MENU_TOP, MenuBean.ROLE_STUDENT));
        menus.add(new MenuBean("Manager", "manager", MenuBean.MENU_TOP, MenuBean.ROLE_ADMIN_STAFF));
        // Menu left
        menus.add(new MenuBean("Course", "course", MenuBean.MENU_LEFT, MenuBean.ROLE_ADMIN_STAFF));
        menus.add(new MenuBean("Role", "role", MenuBean.MENU_LEFT, MenuBean.ROLE_ADMIN));
        menus.add(new MenuBean("News", "news", MenuBean.MENU_LEFT, MenuBean.ROLE_ADMIN_STAFF));
        menus.add(new MenuBean("Subject", "subject", MenuBean.MENU_LEFT, MenuBean.ROLE_ADMIN_STAFF));
        menus.add(new MenuBean("Student", "student", MenuBean.MENU_LEFT, MenuBean.ROLE_ADMIN_STAFF));
        menus.add(new MenuBean("User", "user", MenuBean.MENU_LEFT, MenuBean.ROLE_ADMIN));
        menus.add(new MenuBean("FAQs", "faq", MenuBean.MENU_LEFT, MenuBean.ROLE_ADMIN_STAFF));
        menus.add(new MenuBean("Batch", "batch", MenuBean.MENU_LEFT, MenuBean.ROLE_ADMIN_STAFF));
        menus.add(new MenuBean("Assignment", "assignment", MenuBean.MENU_LEFT, MenuBean.ROLE_ADMIN_STAFF));
        menus.add(new MenuBean("Feedback", "feedback", MenuBean.MENU_LEFT, MenuBean.ROLE_ADMIN_STAFF));
        
        menus.add(new MenuBean("Assignment", "studentassignment", MenuBean.MENU_LEFT, MenuBean.ROLE_STUDENT));
        menus.add(new MenuBean("Mark", "mark", MenuBean.MENU_LEFT, MenuBean.ROLE_STUDENT));
        
    }

    public List<MenuBean> getMenuTop() {
        System.out.println(Authentication.getRole()+"...................");
        List<MenuBean> menuTops = new ArrayList<>();
        if (Authentication.getRole() == Authentication.ROLE_ADMIN) {
            for (MenuBean m : menus) {
                if (m.getType() == MenuBean.MENU_TOP) {
                    if (m.getRole() == MenuBean.ROLE_ADMIN || m.getRole() == MenuBean.ROLE_ADMIN_STAFF || m.getRole() == MenuBean.ROLE_ALL) {
                        menuTops.add(m);
                    }
                }
            }
        } else if (Authentication.getRole() == Authentication.ROLE_STAFF) {
            for (MenuBean m : menus) {
                if (m.getType() == MenuBean.MENU_TOP) {
                    if (m.getRole() == MenuBean.ROLE_STAFF || m.getRole() == MenuBean.ROLE_ADMIN_STAFF || m.getRole() == MenuBean.ROLE_ALL) {
                        menuTops.add(m);
                    }
                }
            }

        } else if (Authentication.getRole() == Authentication.ROLE_STUDENT) {
            for (MenuBean m : menus) {
                if (m.getType() == MenuBean.MENU_TOP) {
                    if (m.getRole() == MenuBean.ROLE_STUDENT || m.getRole() == MenuBean.ROLE_ALL) {
                        menuTops.add(m);
                    }
                }
            }

        } else {
            for (MenuBean m : menus) {
                if (m.getType() == MenuBean.MENU_TOP) {
                    if (m.getRole() == MenuBean.ROLE_ALL) {
                        menuTops.add(m);
                    }
                }
            }
        }
        return menuTops;
    }
    public List<MenuBean> getMenuLeft() {
        List<MenuBean> menuLefts = new ArrayList<>();
        if (Authentication.getRole() == Authentication.ROLE_ADMIN) {
            for (MenuBean m : menus) {
                if (m.getType() == MenuBean.MENU_LEFT) {
                    if (m.getRole() == MenuBean.ROLE_ADMIN || m.getRole() == MenuBean.ROLE_ADMIN_STAFF || m.getRole() == MenuBean.ROLE_ALL) {
                        menuLefts.add(m);
                    }
                }
            }
        } else if (Authentication.getRole() == Authentication.ROLE_STAFF) {
            for (MenuBean m : menus) {
                if (m.getType() == MenuBean.MENU_LEFT) {
                    if (m.getRole() == MenuBean.ROLE_STAFF || m.getRole() == MenuBean.ROLE_ADMIN_STAFF || m.getRole() == MenuBean.ROLE_ALL) {
                        menuLefts.add(m);
                    }
                }
            }

        } else if (Authentication.getRole() == Authentication.ROLE_STUDENT) {
            for (MenuBean m : menus) {
                if (m.getType() == MenuBean.MENU_LEFT) {
                    if (m.getRole() == MenuBean.ROLE_STUDENT || m.getRole() == MenuBean.ROLE_ALL) {
                        menuLefts.add(m);
                    }
                }
            }

        } else {
            for (MenuBean m : menus) {
                if (m.getType() == MenuBean.MENU_LEFT) {
                    if (m.getRole() == MenuBean.ROLE_ALL) {
                        menuLefts.add(m);
                    }
                }
            }
        }
        return menuLefts;
    }

}
