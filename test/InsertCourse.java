
import com.eproject.learning.controller.CtrlCourse;
import com.eproject.learning.controller.CtrlSemester;
import com.eproject.learning.controller.CtrlSubject;
import com.eproject.learning.controller.HibernateUtil;
import com.eproject.learning.entity.Course;
import com.eproject.learning.entity.Semester;
import com.eproject.learning.entity.Subject;
import com.eproject.learning.entity.SubjectSemester;
import com.eproject.learning.entity.SubjectSemesterId;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *  
 * @author luan
 */
public class InsertCourse {
    private Course course;

    public InsertCourse() {
//        course=CtrlCourse.getByCode("123");
//        if(course.getSemesters() ==null){
//            System.out.println("Semester is null ......");
//            return;
//        }
    }
    public void save(){
        
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        
        session.beginTransaction();
        course=(Course)session.createCriteria(Course.class)
                .add(Restrictions.eq("code", "123"))
                .uniqueResult();
        if(course==null){
            return;
        }
        Semester semester1=new Semester();
        semester1.setName("Semester 1");
        semester1.setSemesterOrder(1);
        semester1.setCourse(course);
        
        Set<Semester> semesters=new HashSet<>();
        semesters.add(semester1);
        if(course.getSemesters() !=null){       
        for(Semester s:course.getSemesters()){
//            session.delete(s);
        }
        }

        course.setSemesters(semesters);
        
        System.out.println(course.getCourseId()+"............");
        session.merge(course);
        session.getTransaction().commit();
    }
    public static void main(String[] args) {
        new InsertCourse().save();
    }
    
}

/**


                *                            *  
                *                          *   *
                *                        *      *
                *          *       *        *       *        *
                *          *       *       * *      * *      *
                *          *       *      *   *     *   *    *
                *          *       *     * * * *    *    *   *
                *          *       *    *       *   *      * *
                ********** *********   *         *  *        *


 */
