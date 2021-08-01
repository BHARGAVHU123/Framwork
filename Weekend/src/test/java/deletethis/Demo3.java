package deletethis;

import java.io.IOException;

public class Demo3 {

	public static void main(String[] args) throws IOException {
		System.out.println("start");
		String[] command = {"cmd.exe", "/C", "Start", ".\\remote\\RunMe.bat"};
        Process p =  Runtime.getRuntime().exec(command);
		System.out.println("end");
	}
}
