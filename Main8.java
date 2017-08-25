#include<stdio.h> 
#include<algorithm>
using namespace std; 
struct st { int xh; int c,k; }
data[1010]; 
int cmp(st a,st b)//排序关键！
  { if(a.xh!=b.xh)
return a.xh<b.xh; 
else if(a.c!=b.c)
return a.c<b.c; 
else if(a.k!=b.k)
return a.k<b.k; } 
int main()
 { int test,i,n,t; 
scanf("%d",&test);
 while(test--) 
{ scanf("%d",&n); 
for(i=0;i<n;i++) 
{ scanf("%d%d%d",&data[i].xh,&data[i].c,&data[i].k);//注意比较长宽大小。
  if(data[i].c<data[i].k)
{ t=data[i].c; 
data[i].c=data[i].k; 
data[i].k=t; }
 } 
sort(data,data+n,cmp); 
for(i=0;i<n;i++) 
{ if(!(data[i].xh==data[i+1].xh&&data[i].c==data[i+1].c&&data[i].k==data[i+1].k))
 printf("%d %d %d\n",data[i].xh,data[i].c,data[i].k); } 
} 
return 0; 
}        