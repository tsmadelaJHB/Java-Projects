package za.co.wethinkcode;

/**
 * Hello world!
 *
 */
 
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class App 
{
    public static void main(String[] args) {
    	App app = new App();
    	app.test();
    }
    
    public void test() {
    	try {
    		Scanner sc = new Scanner(new File(this.getClass().getResource("/test.prop").toString()));
    		if (sc.hasNext()) {
    			System.out.println(sc.next());
    		}
    	} catch (FileNotFoundException e) {
    		//
    	}
    }
}
