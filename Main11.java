import java.util.*;
public class Main
{
	public static void main(String []args)
	{
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int []arr = new int[n];
		for(int i=0; i<n; i++ )
		{
			arr[i] = in.nextInt();
		}
		for(int i=0; i<n; i++)
		{
			for(int j=1; j<=arr[i]; j+=2)
			{
				System.out.print(j+" ");
			}
			System.out.println();
			for(int j=2; j<=arr[i]; j+=2)
			{
				System.out.print(j+" ");
			}
			System.out.println('\n');
		}
	}

}
        