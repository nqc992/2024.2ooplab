package Lab1;
import java.util.Scanner;

public class bai6_4 {
	public static int getMonth(String month)
	{
		month = month.toLowerCase();
		switch(month)
		{
		case "january": case "jan.": case "jan": case "1": return 1;
        case "february": case "feb.": case "feb": case "2": return 2;
        case "march": case "mar.": case "mar": case "3": return 3;
        case "april": case "apr.": case "apr": case "4": return 4;
        case "may": case "5": return 5;
        case "june": case "jun.": case "jun": case "6": return 6;
        case "july": case "jul.": case "jul": case "7": return 7;
        case "august": case "aug.": case "aug": case "8": return 8;
        case "september": case "sept.": case "sep": case "9": return 9;
        case "october": case "oct.": case "oct": case "10": return 10;
        case "november": case "nov.": case "nov": case "11": return 11;
        case "december": case "dec.": case "dec": case "12": return 12;
        default: return -1;
		}
	}
	
	public static boolean isLeapYear(int year)
	{
		return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
	}
	
	public static int getDaysMonth(int month, int year)
	{
		switch(month)
		{
			case 1: case 3: case 5: case 7: case 8: case 10: case 11: case 12:
				return 31;
			case 4: case 6: case 9:
				return 30;
			case 2:
				return isLeapYear(year) ? 29 : 28;
			default:
				return -1;
		}
	}
	
	public static void main(String[] args) 
	{
		Scanner keyboard = new Scanner(System.in);
		String sMonth;
		int year;
		while(true)
		{
			System.out.println("Enter a month (ex: January, Jan., Jan or 1): ");
			sMonth = keyboard.next();
			
			System.out.println("Enter a year (ex: 1992): ");
			
			year = keyboard.nextInt();
			
			if(year < 0)
			{
				System.out.println("Invalid year! Please enter again!!");
				continue;
			}
			
			int month = getMonth(sMonth);
			
			if(month == -1)
			{
				System.out.println("Invalid Month! Please enter again!!");
				continue;
			}
			
			int days = getDaysMonth(month, year);
			
			System.out.println("Number of days: " + days);
			break;
		}
		
	}
}
