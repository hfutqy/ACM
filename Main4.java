import java.util.*;
public class Main
{
	public static void main(String []args)
	{
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		String []str = new String[N];
		for(int i=0; i<N; i++)
		{
			str[i] = in.next();
		}
		for(int i=0; i<N; i++)
		{
			ascll(str[i].toCharArray());
			System.out.println();
		}
	}
	
	 public static void ascll(char arr[])
	 {
		 int n = arr.length;
		 char temp;
		 for(int i=0; i<n;i++)
		 {
			 for(int j=0; j<n-i-1; j++)
			 {
				 if(arr[j]>arr[j+1])
				 {
					 temp = arr[j];
					 arr[j] = arr[j+1];
					 arr[j+1] = temp;
				 }
			 }
		 }
		 for(int i=0; i<n; i++)
		 {
			 System.out.print(arr[i]+" ");
		 }
	 }
}
        