
import com.eproject.learning.controller.Convert;
import com.eproject.learning.controller.HibernateUtil;
import com.eproject.learning.entity.Course;
import com.eproject.learning.entity.Semester;
import com.eproject.learning.entity.Subject;
import com.eproject.learning.entity.SubjectSemester;
import com.eproject.learning.entity.SubjectSemesterId;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
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
public class testInsertSemester {
    public static void main(String[] args) {
        Session session=HibernateUtil.getSessionFactory().openSession();
        Course coure=(Course)session.createCriteria(Course.class)
                .add(Restrictions.eq("code", "ACCP"))
                .uniqueResult();
        List<Semester> semesters=(List<Semester>)session.createCriteria(Semester.class)
                .add(Restrictions.eq("course", coure))
                .list();
        Subject subject=(Subject)session.createCriteria(Subject.class)
                .add(Restrictions.eq("code", "XML"))
                .uniqueResult();
        SubjectSemester ss=new SubjectSemester();
        SubjectSemesterId ssId=new SubjectSemesterId();
        ss.setSubjectOrder(10);
        ssId.setSemester(semesters.get(2));
        ssId.setSubject(subject);
        ss.setPk(ssId);
//        semesters.get(2).getSubjectSemesters().add(ss);
//       coure.setSemesters(new Convert().listToSet(semesters));
        Transaction tran=session.beginTransaction(); 
        session.saveOrUpdate(ss);
        tran.commit();
        
    }
}
