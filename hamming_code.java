import java.util.*;
class hamming_code{
public static void main(String args[]){
int redundant_bits=0;
Scanner sc=new Scanner(System.in);

//Taking input in String
System.out.println("Enter the data bits:");
String a1=sc.nextLine();

//Converting string into binary format
int message_bits=a1.length();

//calculating number of parity bits
for(int i=0;i<=message_bits;i++){
double LHS=Math.pow(2,i);
double RHS=message_bits+i+1;
if(LHS>=RHS){
 redundant_bits=i;
System.out.println("Number of Parity bits required:"+redundant_bits);
break;
}
}
//total bits
int total_bits=message_bits+redundant_bits;
System.out.println("Total number of bits required:"+total_bits);

char a[]=new char[total_bits+1];//isme data and parity bits store honge
char array[]=a1.toCharArray();//input string to character array
int h=message_bits;
int m=0;//power
for(int i=1;i<=total_bits;i++){
if(i==Math.pow(2,m)){
a[i]='p';
m=m+1;
}
else{
a[i]=array[h-1];
h=h-1;
}
}

System.out.print("Before assigning parity bits:");
for(int i=total_bits;i>=1;i--){
System.out.print(a[i]);
}
System.out.println();

int c=0;//exactly that much times skip and move forward that much times for each parity bit

//calculating value of parity bits
for(int i=1;i<=total_bits;i++){
if(a[i]=='p'){
int count=0;//maintains number of 1s for each parity
System.out.println();
int l=i;
int l1=i;
//for each parity bit
while(l<=total_bits){//to ensure it does not move out of the loop
c=0;
while(c<l1){
if(l<=total_bits){
System.out.print(a[l]);
c++;
if(a[l]=='1'){
count++;
}
l++;
}
else{
break;
}
}
l=l+i;
}
if(count%2==0){
System.out.print("\tSince number of 1's are even,p"+i+"=0");
a[l1]='0';
}
else{
System.out.print("\tSince number of 1's are odd,p"+i+"=1");
a[l1]='1';
}
}
}

System.out.print("\nCodeword transmitted:");
for(int i=total_bits;i>=1;i--){
System.out.print(a[i]);
}

//RECEIVER SIDE
System.out.println("\nAt the receiver's side:");
System.out.println("Enter the Codeword received:");
String r1=sc.nextLine();//received string
int mess_recd=r1.length();

//for proper indexing
StringBuilder sb=new StringBuilder(r1);
StringBuilder r2=sb.reverse();
String r3="0"+r2.toString();//appending zero to get proper index
char r[]=r3.toCharArray();


System.out.println();
int num[]=new int[r3.length()];
for(int i=0;i<r3.length();i++){
int power=(int)Math.pow(2,i);
num[i]=power;//putting powers of 2 in array num[]
}

StringBuilder parity=new StringBuilder();
//calculating respective positions for each parity bit and appending in s1
for(int i=0;i<mess_recd && num[i]<mess_recd;i++){
StringBuilder s1=new StringBuilder();
int n=num[i];//take number from array
while(n<=mess_recd){
int c2=0;
while(n<=mess_recd && c2<num[i]){
s1.append(r[n]);
n=n+1;
c2=c2+1;
}
n=n+num[i];
}
System.out.println(s1);
int count8=0;
String s2=s1.toString();
char last[]=s2.toCharArray();
for(int j=0;j<last.length;j++){
if(last[j]=='1'){
count8++;
}
}
if(count8%2==0){
System.out.println("p"+num[i]+"=0");
parity.append(0);
}
else{
System.out.println("p"+num[i]+"=1");
parity.append(1);
}
}
//parity contains value of all parity bits
StringBuilder reverse=parity.reverse();
String par=reverse.toString();
System.out.println("Parity Bits are:"+par);
int parity_last=Integer.parseInt(par,2);
if(parity_last==0){
System.out.println("All parity bits are zero...therefore no error!");
}
else{
System.out.println("Error in "+parity_last+" bit");
System.out.println("All parity bits are not zero...therefore codeword is corrupted");
if(r[parity_last]=='0'){
r[parity_last]='1';
}
else{
r[parity_last]='0';
}
System.out.println("Corrected word:");
for(int i=r.length-1;i>=1;i--){
System.out.print(r[i]);
}
}
}}