package deletethis;

import java.util.ArrayList;
import java.util.Iterator;

import generic.FileUtils;

public class Demo7 {

	public static void main(String[] args) throws Exception {
		Iterator<String[]> v = FileUtils.getDataFromXLForDP("./data/input.xlsx","input");
		while(v.hasNext())
		{
			for(String s2:v.next())
			{
				System.out.println(s2);
			}
		}


	}
}
