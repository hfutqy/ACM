 
#include<iostream>
#include<cstdio>
#include<cstring>
#include<algorithm>
#include<bitset>
using namespace std;
#define CLR(arr,val) memset(arr,val,sizeof(arr))
bitset<1000000> Hash;
const int MAX_STEP=100000;
int WQ[MAX_STEP][4],Goal[3],Cap[3],goalval;
int head=0,tail=0;
void movw(int numfrom,int numto,int other)
{
    int total=WQ[head][numfrom]+WQ[head][numto];
    WQ[tail][other]=WQ[head][other];
    WQ[tail][3]=WQ[head][3]+1;
    if(total>Cap[numto])
    {
        WQ[tail][numfrom]=total-Cap[numto];
        WQ[tail][numto]=Cap[numto];
    }
    else
    {
        WQ[tail][numfrom]=0;
        WQ[tail][numto]=total;
    }
    int hashval=WQ[tail][0]*10000+WQ[tail][1]*100+WQ[tail][2];
    if(hashval==goalval) throw WQ[head][3]+1;
    if(WQ[head][numfrom]!=0 && !Hash[hashval]) 
    {
        Hash[hashval]=true;
        if(++tail==MAX_STEP) tail=0;
    }
}
int main()
{
    int t;
    scanf("%d",&t);
    while(t--)
    {
        Hash.reset();
        scanf("%d%d%d%d%d%d",&Cap[0],&Cap[1],&Cap[2],&Goal[0],&Goal[1],&Goal[2]);
        head=0,tail=0,goalval=Goal[0]*10000+Goal[1]*100+Goal[2];
        if(Goal[1]==0 && Goal[2]==0 && Goal[0]==Cap[0]) {puts("0");continue;}
        WQ[tail][0]=Cap[0];WQ[tail][1]=0;WQ[tail][2]=0;WQ[tail][3]=0;
        ++tail;
        try{
            while(head!=tail)
            {
                movw(0,1,2);movw(1,2,0);movw(0,2,1);movw(1,0,2);movw(2,1,0);movw(2,0,1);
                if(++head==MAX_STEP) head=0;
            }
            puts("-1");
        }catch(int step)
        {
            printf("%d\n",step);
        }
    }
}        