
import com.eproject.learning.controller.CtrlAccount;
import com.eproject.learning.controller.CtrlBatch;
import com.eproject.learning.controller.CtrlCourse;
import com.eproject.learning.controller.CtrlMark;
import com.eproject.learning.controller.CtrlSemester;
import com.eproject.learning.controller.CtrlStudent;
import com.eproject.learning.controller.CtrlSubject;
import com.eproject.learning.controller.CtrlUser;
import com.eproject.learning.controller.HibernateUtil;
import com.eproject.learning.entity.Assignment;
import com.eproject.learning.entity.Batch;
import com.eproject.learning.entity.Course;
import com.eproject.learning.entity.FAQ;
import com.eproject.learning.entity.MarkAssignment;
import com.eproject.learning.entity.News;
import com.eproject.learning.entity.User;
import com.eproject.learning.entity.Role;
import com.eproject.learning.entity.Semester;
import com.eproject.learning.entity.Student;
import com.eproject.learning.entity.Subject;
import com.eproject.learning.entity.SubjectSemester;
import java.util.Date;
import java.util.List;
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
public class InsertAccount_Role_test {
    public static void main(String[] args) {
//        Session session=HibernateUtil.getSessionFactory().openSession();
//        session.beginTransaction();
//        // Test FAQ
////        FAQ f=new FAQ();
////        f.setAnswer("I'am 30.");
////        f.setQuestion("How old are you");
////        f.setDate(new Date());
////        session.save(f);
//        
//        // Test News
//        News n=new News();
//        n.setTitle("Hôm nay nhiệt độ sẽ khoảng 11 độ C");
//        n.setContent("Theo tin vịt mới nhận của chúng tôi, nhiệt độ hôm nay sẽ tăng từ 1-2 độ. Theo đó, nhiệt độ này là thấp hơn nhiệt độ hôm qua 1-2 độ");
//        n.setDate(new Date());
//        n.setImage("Unknow");
//        session.save(n);
//        
//        
//        
//        
//        
//        session.getTransaction().commit();
//        
//        System.out.println("Done");
//        List<Course> courses=CtrlCourse.getAllCourse();
//        Set<Batch> batchs=CtrlBatch.getBatchsInCourse(1);
//        for(Batch b:batchs){
//            System.out.println(b.getName());
////            System.out.println("Found !");
//        }
//        System.out.println(CtrlSemester.getSemesterById(1).getName());;
//        Session session=HibernateUtil.getSessionFactory().openSession();
//        session.beginTransaction();
//        Course course=(Course) session.createCriteria(Course.class)
//                .add(Restrictions.eq("courseId", 1))
//                .uniqueResult();
//        Course course=(Course)session.get(Course.class, 1);
//        Semester s=(Semester)session.get(Semester.class, 2);
//        s.getCourses().remove(course);
//        course.getSemesters().remove(s);
//        course.setTime("30 years");
        
//        session.save(course);
//        session.save(s);
//        session.getTransaction().commit();
//        List<Student> students=CtrlStudent.getAllStudents();
//        for(Student s:students){
//            System.out.println(s.getFullName());
//        }
//        try{
//            Student s=(Student)session.get(Student.class, 1);
//            s.setRoll("Hello");
//            s.setUsername("lazyStudent");
//            s.setPassword("fuck");
//            session.merge(s);
//            session.getTransaction().commit();
//        }catch(Exception ex){
//            ex.printStackTrace();
//        }
//        Student s=(Student)CtrlAccount.findById(3, CtrlAccount.STUDENT);
//        System.out.println(s.getFullName());
//        for(Subject s:CtrlSubject.getSubjectsInSemester(3)){
//            System.out.println(s.getName());
//        }

 
    Assignment assignment = new Assignment();
    assignment.setAssignmentId(4);
    for(MarkAssignment m:CtrlMark.getListMarkAssignmentsOfAssignment(4)){
        System.out.println(m.getFilePath());
    }
        System.out.println("Done !");
    }
}
