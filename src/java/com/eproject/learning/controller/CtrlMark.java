/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eproject.learning.controller;

import com.eproject.learning.entity.Assignment;
import com.eproject.learning.entity.MarkAssignment;
import com.eproject.learning.entity.Course;
import com.eproject.learning.entity.MarkAssignmentId;
import com.eproject.learning.entity.Student;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author luan
 */
public class CtrlMark extends CtrlAbs {

    public static MarkAssignment getMarkAssignment(Student student, Assignment assignment) {
        assignment=CtrlAssignment.getById(assignment.getAssignmentId());
        if(assignment.getMarkAssignments()==null ){
            return null;
        }
        for (MarkAssignment m : assignment.getMarkAssignments()) {
            if(m.getStudent()==null){
                return null;
            }
            if (m.getStudent().getStudentId() == student.getStudentId()) {
                return m;
            }
        }
        return null;
    }
    public static Set<MarkAssignment> getListMarkAssignmentsOfAssignment( Integer assignmentId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Assignment ass = (Assignment) session.get(Assignment.class, assignmentId);
        return ass.getMarkAssignments();
    }

//    public static List<MarkAssignment> getAllMark() {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        return (List<MarkAssignment>) session.createCriteria(MarkAssignment.class)
//                .list();
//    }

    public static String delete(Student student, Assignment assignment) {
        Session session = null;
        Transaction tran = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tran = session.beginTransaction();
            MarkAssignment mark = (MarkAssignment) session.createCriteria(MarkAssignment.class)
                    .add(Restrictions.eq("student", student))
                    .add(Restrictions.eq("assignment", assignment))
                    .uniqueResult();
            session.delete(mark);
            tran.commit();
        } catch (Exception ex) {
            if (tran != null && tran.isActive()) {
                tran.rollback();
            }
            ex.printStackTrace();
            System.out.println("Delete mark error !");
            return ERROR;
        }
        return SUCCESS;
    }

    private static String add(Student student, Assignment assignment, float point, String filePath) {
        MarkAssignment mark = new MarkAssignment();
        mark.setStudent(student);
        mark.setAssignment(assignment);
        mark.setMark(point);
        mark.setFilePath(filePath);

        return add(mark);
    }


    private static String add(Student student, Assignment assignment, String filePath) {
        MarkAssignment mark = new MarkAssignment();
        mark.setStudent(student);
        mark.setAssignment(assignment);
        mark.setFilePath(filePath);
        return add(mark);
    }

    public static String add(MarkAssignment mark) {
        Session session = null;
        Transaction tran = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tran = session.beginTransaction();
            mark.setDate(new Date());
            session.save(mark);
            tran.commit();
        } catch (Exception ex) {
            if (tran != null && tran.isActive()) {
                tran.rollback();
            }
            ex.printStackTrace();
            System.out.println("Add mark error !");
            return ERROR;
        }
        return SUCCESS;
    }

    public static String update(MarkAssignment mark) {
        Session session = null;
        Transaction tran = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tran = session.beginTransaction();
            session.merge(mark);
            tran.commit();
        } catch (Exception ex) {
            if (tran != null && tran.isActive()) {
                tran.rollback();
            }
            ex.printStackTrace();
            System.out.println("Update mark error !");
            return ERROR;
        }
        return SUCCESS;
    }
    public static String update(MarkAssignment mark, String filePath) {
        Session session = null;
        Transaction tran = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tran = session.beginTransaction();
            mark.setFilePath(filePath);
            mark.setDate(new Date());
            session.merge(mark);
            tran.commit();
        } catch (Exception ex) {
            if (tran != null && tran.isActive()) {
                tran.rollback();
            }
            ex.printStackTrace();
            System.out.println("Update mark error !");
            return ERROR;
        }
        return SUCCESS;
    }
    public static String update(MarkAssignment mark, float point) {
        Session session = null;
        Transaction tran = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tran = session.beginTransaction();
            mark.setMark(point);
            session.merge(mark);
            tran.commit();
        } catch (Exception ex) {
            if (tran != null && tran.isActive()) {
                tran.rollback();
            }
            ex.printStackTrace();
            System.out.println("Update mark error !");
            return ERROR;
        }
        return SUCCESS;
    }

    public static String save(Student student, Assignment assignment, float point) {
        Session session = null;
        MarkAssignment mark = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();

            Assignment ass = (Assignment) session.get(Assignment.class, assignment.getAssignmentId());
            for (MarkAssignment m : ass.getMarkAssignments()) {
                if (m.getStudent().getStudentId() == student.getStudentId()) {
                    mark = (MarkAssignment) session.get(MarkAssignment.class, m.getPk());
                    mark.setAssignment(assignment);
                    mark.setStudent(student);
                    mark.setMark(point);
                    return update(mark);
                }
            }
            return ERROR;

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Update mark error !");
            return ERROR;
        }
    }

    public static String save(Student student, Assignment assignment, String filePath) {
        Session session = null;
        MarkAssignment mark = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Assignment ass = (Assignment) session.get(Assignment.class, assignment.getAssignmentId());
            for (MarkAssignment m : ass.getMarkAssignments()) {
                if (m.getStudent().getStudentId() == student.getStudentId()) {
                    mark = (MarkAssignment) session.get(MarkAssignment.class, m.getPk());
                    mark.setAssignment(assignment);
                    mark.setStudent(student);
                    mark.setFilePath(filePath);
                    return update(mark);
                }
            }
            return add(student, assignment, filePath);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Update mark error !");
            return ERROR;
        }
    }

}
