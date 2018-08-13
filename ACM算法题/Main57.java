 
import java.util.*;

public class Main{
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = in.nextInt();
		}
		for (int i = 0; i < n; i++) {
			System.out.println(count(arr[i]));
		}

	}

	static int count(int arr) {
		int count = 0;
		int str[] = new int[4];
		str[0] = arr / 1000;
		str[1] = arr / 100 - str[0] * 10;
		str[3] = arr % 10;
		str[2] = (arr % 100 - str[3]) / 10;
		int x = 0, y = 0, z = arr;

		sort(str, 0, 3);// 从小到大排序
		x = str[3] * 1000 + str[2] * 100 + str[1] * 10 + str[0];
		y = str[0] * 1000 + str[1] * 100 + str[2] * 10 + str[3];
		boolean flag = true;
		while (flag) {
			if (x - y == z)
				flag = false;
			else {
				z = x - y;
				str[0] = z / 1000;
				str[1] = z / 100 - str[0] * 10;
				str[3] = z % 10;
				str[2] = (z % 100 - str[3]) / 10;
				sort(str, 0, 3);// 从小到大排序
				x = str[3] * 1000 + str[2] * 100 + str[1] * 10 + str[0];
				y = str[0] * 1000 + str[1] * 100 + str[2] * 10 + str[3];
			}
			count++;
		}

		return count;
	}

	static void sort(int arr[], int low, int high) {
		int l = low;
		int h = high;
		int povit = arr[low];

		while (l < h) {
			while (l < h && arr[h] >= povit)
				h--;
			if (l < h) {
				int temp = arr[h];
				arr[h] = arr[l];
				arr[l] = temp;
				l++;
			}

			while (l < h && arr[l] <= povit)
				l++;

			if (l < h) {
				int temp = arr[h];
				arr[h] = arr[l];
				arr[l] = temp;
				h--;
			}
		}
		// System.out.print(arr);
		// System.out.print("l=" + (l + 1) + "h=" + (h + 1) + "povit=" + povit
		// + "\n");
		if (l > low)
			sort(arr, low, l - 1);
		if (h < high)
			sort(arr, l + 1, high);
	}
}
       
