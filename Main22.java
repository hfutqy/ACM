 
import java.util.*;
public class Main
{
	public static void main(String args[])
	{
		Scanner in = new Scanner(System.in);
		int M = in.nextInt();//M组数据
		int []arr = new int[M];
		for(int i=0; i<M; i++)
		{
			int N = in.nextInt();//每组数量N
			int brr[]=new int[N];
			int sum = 0;
			for(int j=0; j<N; j++)
			{
				brr[j]=in.nextInt();
				if(sushu(brr[j])==1)
					sum+=brr[j];
			}
			if(sum!=0)
				arr[i]=sum;
		}
		for(int e:arr)
			System.out.print(e+"\n");
	}
	
	static int sushu(int m)//素数返回1非素数返回0
	{
		int n = (int) Math.sqrt(m);
		if(m==1)
			return 0;
		for(int i=2; i<=n; i++)
		{
			if(m%i==0)
				return 0;
		}
		return 1;
	}
}       
