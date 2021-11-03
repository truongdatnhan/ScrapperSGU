package tool;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Check {
	public static boolean checkPhone(String phoneNumber) {
		String regex = "0\\d{9}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(phoneNumber);
		if (matcher.find()) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean checkDate(String Date) {
		String regex = "\\d{1,2}-\\d{1,2}-\\d{4}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(Date);
		if (matcher.find()) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean checkID(String ID, String type) {
		String regex = null;
		if (type.compareToIgnoreCase("NV") == 0) {
			regex = "[Nn][Vv]";
		} else if (type.compareToIgnoreCase("DG") == 0) {
			regex = "[Dd][Gg]";
		} else if (type.compareToIgnoreCase("S") == 0) {
			regex = "[Ss]";
		} else if (type.compareToIgnoreCase("TL") == 0) {
			regex = "[Tt][Ll]";
		} else if (type.compareToIgnoreCase("LV") == 0) {
			regex = "[Ll][Vv]";
		} else if (type.compareToIgnoreCase("NXB") == 0) {
			regex = "[Nn][Xx][Bb]";
		} else if (type.compareToIgnoreCase("NCC") == 0) {
			regex = "[Nn][Cc]{2}";
		} else if (type.compareToIgnoreCase("TTV") == 0) {
			regex = "[Tt]{2}[vV]";
		} else if (type.compareToIgnoreCase("PM") == 0) {
			regex = "[Mm][Aa][Pp][Mm]";
		} else if (type.compareToIgnoreCase("PP") == 0) {
			regex = "[Mm][Aa][Pp]{2}";
		}
		regex = regex + "\\d{3}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(ID);
		if (matcher.find()) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean checkEmail(String email) {
		String regex = "^(.+)@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		if (matcher.find()) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean checkNull(String s) {
		if(s.compareTo("")==0) {
			return true;
		}else {
			return false;
		}
	}
        
        public static String returnNumber(String s)
        {
            s=s.trim();
            int k=s.length();
            for(int i=0;i<k;i++)
            {
                if(s.charAt(i)==' '){
                String temp1=s.substring(0,i);
                String temp2=s.substring(i+1);
                s=temp1+temp2;
                i=-1;
                k=s.length();}
                if(s.charAt(0)=='0')
                    {
                        s=s.substring(1);
                        i=-1;
                        k=s.length();
                    }
            }
            return s;
            
        }
	public static boolean isNumeric(String s) {
            s=returnNumber(s);
		return s.matches("-?\\d+(\\.\\d+)?");
	}
	
//	public static int checkPass(String pass) {
//		int k ;
//		int d;
//		if(pass.length()<6) {
//			k = 1;
//		}else {
//			for(int i = 0;i<pass.length();i++) {
//				if(pass.charAt(i)>)
//			}
//		}
//	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String s = input.nextLine();
		if(Check.checkID(s, "NV")) {
			System.out.print("ID này hợp lệ");
		} else {
			System.out.print("ID này không hợp lệ");
		}
		
	}
	
	
}
