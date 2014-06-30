/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eproject.learning.manager.course;

import com.eproject.learning.controller.Convert;
import static com.eproject.learning.controller.CtrlAbs.ERROR;
import com.eproject.learning.controller.CtrlCourse;
import com.eproject.learning.controller.CtrlSemester;
import com.eproject.learning.controller.CtrlSubject;
import com.eproject.learning.controller.HibernateUtil;
import com.eproject.learning.entity.Course;
import com.eproject.learning.entity.Semester;
import com.eproject.learning.entity.Subject;
import com.eproject.learning.entity.SubjectSemester;
import com.eproject.learning.entity.SubjectSemesterId;
import com.eproject.learning.manager.semester.SemesterDataModel;
import com.eproject.learning.manager.subject.SubjectDataModel;
import com.eproject.learning.view.ActionBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

/**
 *
 * @author luan
 */
@ManagedBean
@ViewScoped
public class UpdateCourse implements Serializable {

    private Course course;
    private boolean addMode = false;
    private Semester selectedSemester;
    private List<Subject> selectedSubjects;
    private SemesterDataModel mediumSemestersModel;
    private SubjectDataModel mediumSubjectsModel;
    private DualListModel<Subject> dualList;

    private List<Semester> deleteSemesters;

    private List<Semester> semesters;
    private List<Subject> unUsedsubjects;
    private List<Subject> subjectsInSemester;
    private Map<Integer, List<Subject>> subjectsInSemestermap;


    public UpdateCourse() {
        String coursePara = ActionBean.getParameter("c");
        subjectsInSemestermap = new HashMap<>();
        if (ActionBean.getParameter("a").equals("add")) {
            addMode = true;
            course = new Course();
        } else if (coursePara.equalsIgnoreCase("")) {
            ActionBean.redirect("manager.xhtml?action=course");
            return;
        } else {
            course = CtrlCourse.getById(Integer.parseInt(coursePara));
        }
        initSemesters();
        initSubjectLs();

    }

    private void initSubjectLs() {
        subjectsInSemester = new ArrayList<>();
        unUsedsubjects = CtrlSubject.getAllSubject();
        for (Semester semester : course.getSemesters()) {
            List<Subject> ss = new ArrayList<>();
            for (SubjectSemester subjectSemester : semester.getSubjectSemesters()) {
                ss.add(subjectSemester.getPk().getSubject());
                for (Subject s : unUsedsubjects) {
                    if (subjectSemester.getPk().getSubject() != null && s.getSubjectId() == subjectSemester.getPk().getSubject().getSubjectId()) {
                        unUsedsubjects.remove(s);
                        break;
                    }
                }
            }
            subjectsInSemestermap.put(semester.getSemesterOrder(), ss);
        }
        dualList = new DualListModel<>(new ArrayList<Subject>(), subjectsInSemester);
    }

    private void refreshDualList() {
        dualList = new DualListModel<>(unUsedsubjects, subjectsInSemester);
    }

    public List<Semester> sortSemester(List<Semester> semesters) {
        if (semesters == null) {
            return semesters;
        }
        Collections.sort(semesters, new Comparator<Semester>() {
            @Override
            public int compare(Semester s1, Semester s2) {
                return s1.getSemesterOrder().compareTo(s2.getSemesterOrder());
            }
        });
        return semesters;
    }

    private void initSemesters() {
        if (addMode) {
            semesters = new ArrayList<>();
        } else {
            semesters = sortSemester(new Convert().setToList(course.getSemesters()));
        }
        mediumSemestersModel = new SemesterDataModel(semesters);
    }

    private void initSemesters(List<Semester> semesters) {
        this.semesters = semesters;
        mediumSemestersModel = new SemesterDataModel(semesters);
    }

    public void addSemester() {
        Semester s = new Semester();
        int order = semesters.size() + 1;
        s.setName("Semester " + order);
        s.setSemesterOrder(new Integer(order));
        s.setCourse(course);
        semesters.add(s);
        subjectsInSemestermap.put(s.getSemesterOrder(), new ArrayList<Subject>());
        initSemesters(semesters);
        System.out.println(s.getSemesterOrder() + "..................");
    }

    public void deleteSemester() {
        Semester semester = semesters.get(semesters.size() - 1);
        if (deleteSemesters == null) {
            deleteSemesters = new ArrayList<>();
        }
        if (semester.getSemesterId() != null) {
            deleteSemesters.add(semester);
        }
        semesters.remove(semester);
        initSemesters(semesters);
    }

    public void onSemesterSelect(SelectEvent event) {
        Semester s = (Semester) event.getObject();
        if (!subjectsInSemestermap.containsKey(s.getSemesterOrder())
                || subjectsInSemestermap.get(s.getSemesterOrder()) == null) {
            return;
        }
//        sortSubjectSemester(new Convert().setToList(s.getSubjectSemesters()));
        subjectsInSemester = subjectsInSemestermap.get(s.getSemesterOrder());
        refreshDualList();

    }

    public void onSemesterUnselect(SelectEvent event) {
        System.out.println("Row unselect");
        Semester s = (Semester) event.getObject();
        if (!subjectsInSemestermap.containsKey(s.getSemesterOrder())
                || subjectsInSemestermap.get(s.getSemesterOrder()) == null) {
            return;
        }
        subjectsInSemestermap.put(s.getSemesterOrder(), dualList.getTarget());
    }

    public void onTransfer(TransferEvent event) {
        List<Subject> source = dualList.getSource();
        List<Subject> target = dualList.getTarget();
        List<Subject> ss = new ArrayList<>();
        for (Subject s : target) {
            s = CtrlSubject.getById(s.getSubjectId());
            ss.add(s);
            for (Subject sub : unUsedsubjects) {
                if (sub.getSubjectId().equals(s.getSubjectId())) {
                    unUsedsubjects.remove(sub);
                    break;
                }
            }
        }
        boolean has = false;
        for (Subject s : source) {
            s = CtrlSubject.getById(s.getSubjectId());
            for (Subject sub : unUsedsubjects) {
                if (sub.getSubjectId().equals(s.getSubjectId())) {
                    has = true;
                    break;
                }
            }
            if (!has) {
                unUsedsubjects.add(s);
            }
        }

        subjectsInSemestermap.put(selectedSemester.getSemesterOrder(), ss);
        subjectsInSemester = ss;
        refreshDualList();
    }

    private boolean saveCourse() {
        String code = course.getCode();
        if (course == null) {
            return false;
        }
        String result = "";
        if (course.getCourseId() == null) {
            result = CtrlCourse.add(course);
        } else {
            result = CtrlCourse.update(course);
        }
        if (!result.equals(CtrlCourse.SUCCESS)) {
            RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Update error", "Course " + course.getCode() + " can't updated. Please try again !"));
            return false;
        }
        if(deleteSemesters==null){
            return true;
        }
        for (Semester s : deleteSemesters) {
            if (s.getBatchs() != null && s.getBatchs().size() > 0) {
                RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Delete error", "Semester " + s.getName() + " has some batchs. Please delete batchs before !"));
                return false;
            }
            result = CtrlSemester.delete(s.getSemesterId());
            if (!result.equals(CtrlSemester.SUCCESS)) {
                RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Delete error", "Semester " + s.getName() + " can't deleted. Please try again !"));
                return false;
            }
        }
        try{
        course = CtrlCourse.getByCode(code);
        }catch(NullPointerException ex){
            RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Update error", "Some errors. Please try again !"));
            return false;
        }
        return true;
    }

    private Semester saveSemester(Semester semester) {
        if (semester == null) {
            return null;
        }
        String result = "";
        if (semester.getSemesterId() == null) {
            semester.setCourse(course);
            result = CtrlSemester.add(semester);
            if (!result.equals(CtrlSemester.SUCCESS)) {
                RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Some errors. Please try again !"));
                return null;
            }
        }
        semester = CtrlSemester.findByOrder(semester.getSemesterOrder(), course);
        if (semester == null) {
            RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Some errors. Please try again !"));
            System.out.println("Can't find semester");
            return null;
        }
        return semester;
    }

    private boolean saveSubjectSemester(Semester semester) {
        String result = "";
        boolean has = false;
        List<Subject> subjects = subjectsInSemestermap.get(semester.getSemesterOrder());
        for (SubjectSemester subjectSemester : semester.getSubjectSemesters()) {
            for (Subject s : subjects) {
                System.out.println(subjectSemester.getSubject().getSubjectId()+"----------"+s.getSubjectId());
                if (s.getSubjectId()==subjectSemester.getSubject().getSubjectId()) {
                    has = true;
                    break;
                }
            }
            if (!has) {
                System.out.println("Delete subject in semester............line 290");
                result = CtrlSemester.delete(subjectSemester);
                if (!result.equals(CtrlSemester.SUCCESS)) {
                    RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Update Error", "Some errors. Please try again !"));
                    System.out.println("False 295");
                    return false;
                }
            }
        }

        has = false;
        for (int i = 0; i < subjects.size(); i++) {
            if (semester.getSubjectSemesters() != null) {
                for (SubjectSemester subjectSemester : semester.getSubjectSemesters()) {
                    if (subjectSemester.getSubject() != null && subjectSemester.getSubject().getSubjectId().equals(subjects.get(i).getSubjectId())) {
                        subjectSemester.setSubjectOrder(i+1);
                        result = CtrlSemester.saveOrUpdateSubject(subjectSemester);
                        if (!result.equals(CtrlSemester.SUCCESS)) {
                            RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Update Error", "Some errors. Please try again !"));
                            System.out.println("False 309");
                            return false;
                        }
                        has = true;
                        break;
                    }
                }
                if (!has) {
                    SubjectSemester subjectSemester = new SubjectSemester();
                    SubjectSemesterId ssId = new SubjectSemesterId();
                    subjectSemester.setPk(ssId);
                    subjectSemester.getPk().setSemester(semester);
                    subjectSemester.getPk().setSubject(subjects.get(i));
                    subjectSemester.setSubjectOrder(i + 1);
                    result = CtrlSemester.saveOrUpdateSubject(subjectSemester);
                    if (!result.equals(CtrlSemester.SUCCESS)) {
                        RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Some errors. Please try again !"));
                        System.out.println("False 326");
                        return false;
                    }
                }
            } else {
                System.out.println("Subject in Semester is null line 328");
            }
        }
        return true;
    }

    public void save() {
        boolean success=false;
        success=saveCourse();
        if(!success){
            System.out.println("Update course error.........");
            message(FacesMessage.SEVERITY_ERROR, "Update course error ! Please try again !");
            return;
        }
        for(Semester s:semesters){
            s=saveSemester(s);
            if(s==null){
                System.out.println("Semeter is null.........");
                message(FacesMessage.SEVERITY_ERROR, "Update course error ! Please try again !");
                return;
            }
            success=saveSubjectSemester(s);
            if(!success){
                System.out.println("Update subject error.........");
                message(FacesMessage.SEVERITY_ERROR, "Update course error ! Please try again !");
                return;
            }
        }
        message(FacesMessage.SEVERITY_INFO, "Update course success !");
        return;
    }
    public void message(Severity type, String msg){
        System.out.println("Show message..............");
        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(type, "Update Course", msg));
        if(course==null){
            ActionBean.redirect("manager.xhtml?action=course");
        }else if(course.getCourseId()==null){
            ActionBean.redirect("manager.xhtml?action=course&a=add");
        }  else{
            ActionBean.redirect("manager.xhtml?action=course&a=edit&c="+course.getCourseId());
        }
        
    }

    public void reset() {
        if (addMode) {
            course = new Course();

        } else {
            course = CtrlCourse.getById(course.getCourseId());
        }
        initSubjectLs();
        initSemesters();
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setAddMode(boolean addMode) {
        this.addMode = addMode;
    }

    public void setSemesters(List<Semester> semesters) {
        this.semesters = semesters;
    }

    public List<Semester> getSemesters() {
        return semesters;
    }

    public SemesterDataModel getMediumSemestersModel() {
        return mediumSemestersModel;
    }

    public void setMediumSemestersModel(SemesterDataModel mediumSemestersModel) {
        this.mediumSemestersModel = mediumSemestersModel;
    }

    public Semester getSelectedSemester() {
        return selectedSemester;
    }

    public void setSelectedSemester(Semester selectedSemester) {
        this.selectedSemester = selectedSemester;
    }

    public void setSubjectsInSemester(List<Subject> subjectsInSemester) {
        this.subjectsInSemester = subjectsInSemester;
    }

    public List<Subject> getSubjectsInSemester() {
        return subjectsInSemester;
    }

    public List<Subject> getSelectedSubjects() {
        return selectedSubjects;
    }

    public void setSelectedSubjects(List<Subject> selectedSubjects) {
        this.selectedSubjects = selectedSubjects;
    }



    public Map<Integer, List<Subject>> getSubjectsInSemestermap() {
        return subjectsInSemestermap;
    }

    public void setSubjectsInSemestermap(Map<Integer, List<Subject>> subjectsInSemestermap) {
        this.subjectsInSemestermap = subjectsInSemestermap;
    }

    public void setMediumSubjectsModel(SubjectDataModel mediumSubjectsModel) {
        this.mediumSubjectsModel = mediumSubjectsModel;
    }

    public SubjectDataModel getMediumSubjectsModel() {
        return mediumSubjectsModel;
    }

    public void setUnUsedsubjects(List<Subject> unUsedsubjects) {
        this.unUsedsubjects = unUsedsubjects;
    }

    public void setDualList(DualListModel<Subject> dualList) {
        this.dualList = dualList;
    }

    public List<Subject> getUnUsedsubjects() {
        return unUsedsubjects;
    }

    public DualListModel<Subject> getDualList() {
        return dualList;
    }

}
