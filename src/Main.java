import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

public class Main extends Tool
{
	public static void main(String[] args)
	{
		
		//File f = new File("/storage/emulated/0/");
		Scanner in = new Scanner(System.in);
		File f = new File("/storage/emulated/0/图片2.txt");
		try
		{
			Writer write = new FileWriter(f);

			//Scanner in = new Scanner(System.in);
			String url = doGet(in.next());
			//System.out.println(url);
			String zz = "(https://www.elitebabes.com/).+(\" title)";
			//String zz1 = "";
			String urlt = null;
			System.out.println("over!!");
			//String str = url.substring(url.indexOf("<ul class=\"gallery-a b\">"),url.indexOf("<div class='m-pagination"));
			//System.out.println(str);
			//String g = "(https://k5x5n5g8.ssl.hwcdn.net/content/)[\\w]++(/)[\\w]++(.jpg)";
			Pattern pat = Pattern.compile(zz);
			Matcher mat = pat.matcher(url);	
			while(mat.find()){
				//	System.out.println(mat.group().substring(0,mat.group().length()-7));
				urlt =doGet(mat.group().substring(0,mat.group().length()-7));
				//System.out.println(urlt);
				String zzbd = "(https://k5x5n5g8.ssl.hwcdn.net/content/)[\\w]{6}+(/)[\\d]{4}+(-)[\\d]{2}+(.jpg)";
				Pattern patt = Pattern.compile(zzbd);
				Matcher matt = patt.matcher(urlt);	
				while(matt.find()){
					System.out.println(matt.group());
					write.write(matt.group());write.flush();write.write("\r\n");

					/*Pattern pato = Pattern.compile(g);
					 Matcher mato = pato.matcher(matt.group());	
					 while(mato.find()){
					 //	System.out.println(".");
					 //if(mato.group().length()==58)	
					 {

					 //System.out.println(mato.group());

					 }
					 }*/
				}
			}
			write.close();
		}
		catch (IOException e)
		{}
	}

}
