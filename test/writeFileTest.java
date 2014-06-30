
import java.io.File;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author luan
 */
public class writeFileTest {
    public static void main(String[] args) {
        String folder="/home/luan/123/123";
    File f=new File(folder);
    if(!f.isDirectory()){
        f.mkdir();
    }else{
        System.out.println("Folder exits");
    }
    }
    
}
