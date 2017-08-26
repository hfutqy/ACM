 
import java.util.*;
public class Main
{
	public static void main(String args[])
	{
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int arr[] = new int[N],brr[] = new int[N],crr[] = new int[N];
		for(int i=0; i<N; i++)
		{
			arr[i] = in.nextInt();
			int flag = 0,x=1,y=-1;//flag记录非素数跳出条件；x,y表示上下浮动数值
			
			if(sushu(arr[i])==1)
			{
				brr[i]=arr[i];
				crr[i]=0;
				continue;
			}
			while(flag==0)
			{
				if(sushu(arr[i]+x)==1)
				{
					flag=1;
					brr[i]=arr[i]+x;
					crr[i]=x;
				}
				if(sushu(arr[i]+y)==1)
				{
					flag=1;
					brr[i]=arr[i]+y;
					crr[i]=0-y;
				}	
				x++;
				y--;
			}
		}
		for(int i=0; i<N; i++)
			System.out.println(brr[i] + " " + crr[i]);
		
		
	}
	static int sushu(int m)//素数返回1非素数返回0
	{
		int n = (int) Math.sqrt(m);
		if(m<=1)
			return 0;
		for(int i=2; i<=n; i++)
		{
			if(m%i==0)
				return 0;
		}
		return 1;
	}
}
       
