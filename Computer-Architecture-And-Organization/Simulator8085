#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX 1000

//GLOBALS
char registers[8][16];
//global table of all required functionalities for the task..
char gacMat[14][17]=
{
    {'0','0','0','0','0','0','0','0','0','0','0','0','0','.','.','.',0}, //MVI, 13
    {'0','0','0','0','0','0','1','0','0','0','.','.','.','.','.','.',0}, //MOV, 10
    {'0','0','0','0','0','1','0','.','.','.','.','.','.','.','.','.',0}, //AND, 7
    {'0','0','0','0','0','1','1','.','.','.','.','.','.','.','.','.',0}, //OR, 7
    {'0','0','0','0','1','0','0','0','0','0','.','.','.','.','.','.',0}, //NOT, 10
    {'0','0','0','1','0','0','1','0','0','0','0','0','0','0','0','0',0}, //JMP, 16
    {'0','0','0','0','1','0','1','0','0','0','.','.','.','.','.','.',0}, //LESS, 10
    {'0','0','0','0','1','1','0','0','0','0','.','.','.','.','.','.',0}, //LEQ, 10
    {'0','0','0','0','1','1','1','0','0','0','.','.','.','.','.','.',0}, //GRE, 10
    {'0','0','0','1','0','0','0','0','0','0','.','.','.','.','.','.',0}, //GEQ, 10
    {'0','0','0','1','0','1','0','0','0','0','0','0','0','.','.','.',0}, //PRN, 13
    {'0','0','0','1','1','0','0','.','.','.','.','.','.','.','.','.',0}, //SUB, 7
    {'0','0','0','1','1','1','0','0','0','0','0','0','0','.','.','.',0}, //SL, 13
    {'0','0','0','1','1','1','1','0','0','0','0','0','0','.','.','.',0}, //SR, 13
};
//global table used for decoding the numbers of the registers
char gacMat1[8][4]=
{
    {'0','0','0',0}, //0
    {'0','0','1',0}, //1
    {'0','1','0',0}, //2
    {'0','1','1',0}, //3
    {'1','0','0',0}, //4
    {'1','0','1',0}, //5
    {'1','1','0',0}, //6
    {'1','1','1',0}, //7
};

//INSTRUCTIONS
void MVI(int, char*); //3 bits register, 16 bits value
void MOV(int, int); //3 bits register, 3 bits register
void AND(int, int, int); //3 bits register, 3 bits register, 3 bits register
void OR(int, int, int); //3 bits register, 3 bits register, 3 bits register
void NOT(int, int); //3 bits register, 3 bits register
void JMP(int, int*); //16 bit addresults
void LESS(int, int, int, int*); //3 bits register, 3 bits register, 16 bits next instruction
void LEQ(int, int, int, int*); //3 bits register, 3 bits register, 16 bits next instruction
void GRE(int, int, int, int*); //3 bits register, 3 bits register, 16 bits next instruction
void GEQ(int, int, int, int*); //3 bits register, 3 bits register, 16 bits next instruction
void PRN(int); //3 bits register
void SUB(int, int, int); //3 bits register, 3 bits register, 3 bits register
void SL(int); //3 bits register
void SR(int); //3 bits register

//CUSTOM FUNCTIONS
void strncpyMOJ(char*, char*, int);
int strcmpMOJ(char*,char*);
int strncmpMOJ(char*,char*, int);
int dekodirajinstruction(char*);
int dekodirajRegister(char*, int, int*);
int convert(char*); //convert from binary to decimal

int main()
{
    int i,j,k;
    int N = 0;  // number of lines in the input
    char c;
    char lines[MAX][17];

    while (1)
    {
        scanf("%c", &c);
        if (c == '\n')
        {
            break;
        }
        lines[N][0] = c;
        for (i=1; i<16; i++)
        {
            scanf("%c", &lines[N][i]);
        }
        lines[N][16]=0;
        N++;
        scanf("%c", &c);
    }
    for (i = 0; i < 8; i++)
    {
        for (j = 0; j < 16; j++)
        {
            registers[i][j] = '0';
        }
    }

    // vasiot kod ovde
    int niz[4];
    for (i=0; i<N; i++) //we go though all instructions
    {
        switch(dekodirajinstruction(lines[i]))
        {
        case 1:
            dekodirajRegister(lines[i],1,niz);
            i+=niz[0]; //determine the length of the instruction, does the next word is instruction or data?
            //printf("MVI r%d %d\n",niz[1],convert(lines[i]));
            MVI(niz[1],lines[i]);
            break;
        case 2:
            dekodirajRegister(lines[i],2,niz);
            i+=niz[0];
            //printf("MOV r%d r%d\n",niz[1],niz[2]);
            MOV(niz[1],niz[2]);
            break;
        case 3:
            dekodirajRegister(lines[i],3,niz);
            i+=niz[0];
            //printf("AND r%d r%d r%d\n",niz[1],niz[2],niz[3]);
            AND(niz[1],niz[2],niz[3]);
            break;
        case 4:
            dekodirajRegister(lines[i],4,niz);
            i+=niz[0];
            //printf("OR r%d r%d r%d\n",niz[1],niz[2],niz[3]);
            OR(niz[1],niz[2],niz[3]);
            break;
        case 5:
            dekodirajRegister(lines[i],5,niz);
            i+=niz[0];
            //printf("NOT r%d r%d\n",niz[1],niz[2]);
            NOT(niz[1],niz[2]);
            break;
        case 6:
            dekodirajRegister(lines[i],6,niz);
            i+=niz[0];
            //printf("JMP %d\n",convert(lines[i])+1);
            JMP(convert(lines[i]),&i);
            break;
        case 7:
            dekodirajRegister(lines[i],7,niz);
            i+=niz[0];
            //printf("LESS r%d r%d %d\n",niz[1],niz[2],convert(lines[i]+1));
            LESS(niz[1],niz[2],convert(lines[i]),&i);
            break;
        case 8:
            dekodirajRegister(lines[i],8,niz);
            i+=niz[0];
            //printf("LEQ r%d r%d %d\n",niz[1],niz[2],convert(lines[i]+1));
            LEQ(niz[1],niz[2],convert(lines[i]),&i);
            break;
        case 9:
            dekodirajRegister(lines[i],9,niz);
            i+=niz[0];
            //printf("GRE r%d r%d %d\n",niz[1],niz[2],convert(lines[i]+1));
            GRE(niz[1],niz[2],convert(lines[i]),&i);
            break;
        case 10:
            dekodirajRegister(lines[i],10,niz);
            i+=niz[0];
            //printf("GEQ r%d r%d %d\n",niz[1],niz[2],convert(lines[i]+1));
            GEQ(niz[1],niz[2],convert(lines[i]),&i);
            break;
        case 11:
            dekodirajRegister(lines[i],11,niz);
            i+=niz[0];
            //printf("PRN r%d\n",niz[1]);
            PRN(niz[1]);
            break;
        case 12:
            dekodirajRegister(lines[i],12,niz);
            i+=niz[0];
            //printf("SUB r%d r%d r%d\n",niz[1],niz[2],niz[3]);
            SUB(niz[1],niz[2],niz[3]);
            break;
        case 13:
            dekodirajRegister(lines[i],13,niz);
            i+=niz[0];
            //printf("SL r%d\n",niz[1]);
            SL(niz[1]);
            break;
        case 14:
            dekodirajRegister(lines[i],14,niz);
            i+=niz[0];
            //printf("SR r%d\n",niz[1]);
            SR(niz[1]);
            break;
        }
    }

    return 0;
}

//**********FUNCTION DEFINITIONS**********

//INSTRUCTIONS
void MVI(int reg, char *value) //3 bits register, 16 bits value
{
    // vasiot kod ovde
    //printf("Moving %.16s to register #%d\n", value, reg);
    strncpyMOJ(registers[reg],value,16);
}
void MOV(int reg1, int reg2) //3 bits register, 3 bits register
{
    //printf("Moving register #%d: %.16s to register #%d\n", reg2, registers[reg2], reg1);
    strncpyMOJ(registers[reg1],registers[reg2],16);
}
void AND(int reg1, int reg2, int reg3) //3 bits register, 3 bits register, 3 bits register
{
    // vasiot kod ovde
    int i;
    for (i=0; i<16; i++)
        registers[reg1][i]=((registers[reg2][i]-'0'&&registers[reg3][i]-'0')+'0');//kastiraj integer, sporedi integers, kastiraj nazad vo char
    //registers[reg1][i]=0;//NULL terminator
}
void OR(int reg1, int reg2, int reg3) //3 bits register, 3 bits register, 3 bits register
{
    // vasiot kod ovde
    int i;
    for (i=0; i<16; i++)
        registers[reg1][i]=((registers[reg2][i]-'0'||registers[reg3][i]-'0')+'0');//kastiraj integer, sporedi integers, kastiraj nazad vo char
    // registers[reg1][i]=0;//NULL terminator
}
void NOT(int reg1, int reg2) //3 bits register, 3 bits register
{
    // vasiot kod ovde
    int i;
    for (i=0; i<16; i++)
        registers[reg1][i]=(!(registers[reg2][i]-'0')+'0');//kastiraj integer, sporedi integers, kastiraj nazad vo char
    // registers[reg1][i]=0;//NULL terminator
}
void JMP(int next, int *counter) //I NEED FIX? //16 bit addresults
{
    *counter=next-1;
}
void LESS(int reg1, int reg2, int next, int *counter) //3 bits register, 3 bits register, 16 bits next instruction
{
    if (registers[reg1][0] == '1'&&registers[reg2][0] == '0')
    {
        JMP(next, counter);
        return;
    }
    else if (registers[reg1][0] == registers[reg2][0])
    {
        int i;
        int flag = 0;

        for (i = 1; i < 16; ++i)
        {
            if (registers[reg1][i] != registers[reg2][i])
            {
                flag = (registers[reg1][0] == '0') ? (registers[reg1][i] < registers[reg2][i]) : (registers[reg1][i] > registers[reg2][i]);

                if (flag)
                    JMP(next, counter);
                return;
            }
        }
    }
}
void LEQ(int reg1, int reg2, int next, int *counter) //3 bits register, 3 bits register, 16 bits next instruction
{
    if (registers[reg1][0] == '0'&&registers[reg2][0] == '1')
    {
        JMP(next, counter);
        return;
    }
    else if (registers[reg1][0] == registers[reg2][0]) //same sign
    {
        int i;
        int flag = 0;

        for (i = 1; i < 16; ++i) //skip the sign bit
        {
            if (registers[reg1][i] != registers[reg2][i])
            {
                flag = (registers[reg1][0] == '0') ? (registers[reg1][i] <= registers[reg2][i]) : (registers[reg1][i] > registers[reg2][i]);

                if (flag)
                    JMP(next, counter);
                return;
            }
            if (registers[reg1][i] == registers[reg2][i])
            {
                flag=1;
            }
        }
        if (flag)
            JMP(next, counter);
    }
}
void GRE(int reg1, int reg2, int next, int *counter) //3 bits register, 3 bits register, 16 bits next instruction
{
    if (registers[reg1][0] == '0' && registers[reg2][0] == '1')
    {
        JMP(next, counter);
        return;
    }
    else if (registers[reg1][0] == registers[reg2][0])
    {
        int i;
        int flag = 0;

        for (i = 1; i < 16; ++i)
        {
            if (registers[reg1][i] != registers[reg2][i])
            {
                flag = (registers[reg1][0] == '0') ? (registers[reg1][i] > registers[reg2][i]) : (registers[reg1][i] < registers[reg2][i]);

                if (flag)
                    JMP(next, counter);
                return;
            }
        }
    }
}
void GEQ(int reg1, int reg2, int next, int *counter) //3 bits register, 3 bits register, 16 bits next instruction
{
    if (registers[reg1][0] == '0' && registers[reg2][0] == '1')
    {
        JMP(next, counter);
        return;
    }
    else if (registers[reg1][0] == registers[reg2][0]) //same sign
    {
        int i;
        int flag = 0;

        for (i = 1; i < 16; ++i) //skip the sign bit
        {
            if (registers[reg1][i] != registers[reg2][i])
            {
                flag = (registers[reg1][0] == '0') ? (registers[reg1][i] >= registers[reg2][i]) : (registers[reg1][i] < registers[reg2][i]);

                if (flag)
                    JMP(next, counter);
                return;
            }
            if (registers[reg1][i] == registers[reg2][i])
            {
                flag=1;
            }
        }
        if (flag)
            JMP(next, counter);
    }
}
void PRN(reg) //3 bits register
{
    int i;
    for (i=0; i<16; i++) printf("%c",registers[reg][i]);
    printf("\n");
}
void SUB(int reg1, int reg2, int reg3) //3 bits register, 3 bits register, 3 bits register
{
    int i;
    char left[16],right[16],result[16];

    strncpyMOJ(left, registers[reg2],16); //make copies
    strncpyMOJ(right, registers[reg3],16);
    memset(result,0,sizeof(char)); //set the result to all 0s

    right[0]=(right[0]=='0')?'1':'0'; //invert the 1st bit

    if (left[0] == right[0])   // same sign bits(after inverting the 1st bit of the left operand), perform addition
    {
        int carry=0;

        for (i=15;i>0;i--)
        {
            result[i]=((left[i]-'0')+(right[i] -'0')+carry)%2+'0';
            carry=((left[i]-'0')+(right[i]-'0')+carry)/2;
        }

        if(carry!=0)   // OVERFLOW!
        {
            printf("OVERFLOW\n");
            for (i=0;i<16;i++)
                result[i]='0';
        }
        else
        {
            result[0]=left[0];
        }
    }
    else   // perform subtraction!
    {
        for (i=15;i>0;i--)
        {
            if (left[i]=='0'&&right[i]=='1')   // we're going to borrow..
            {
                int j;
                int flag=0;
                for (j=i-1;j>0;j--)
                {
                    if (left[j]=='1')
                    {
                        left[j]='0';
                        flag=1;
                        break;
                    }
                    else
                    {
                        left[j]='1';
                    }
                }

                if (!flag)
                    result[0]='1'; // borrowed from imaginary unit, resultult has to be made negative.

                result[i] = '1';
            }
            else   //we dont have to borrow..
            {
                result[i]=(left[i]-'0')-(right[i]-'0')+'0'; //cast in-and-out
            }
        }

        if (result[0]=='1')   // negative res.  perform 2s complement
        {
            int flip=0;
            for (i=15;i>0;i--)
            {
                if (flip==0)
                {
                    if (result[i]== '1')
                        flip=1;
                }
                else
                {
                    result[i]=(result[i] == '0')?'1':'0';
                }
            }

            result[0]=(left[0] == '0')?'1':'0'; // copy the inverted bit of the 1st operand
        }
        else
        {
            result[0] = left[0]; // cope the sign bit of the 1st operand
        }
    }

    int flag=1; //detected negative zero?
    for (i=15;i>0;i--)
    {
        if (result[i]=='1')
        {
            flag=0;
            break;
        }
    }

    if (flag!=0&&result[0]=='1')
        result[0]='0';

    strncpyMOJ(registers[reg1], result,16); //write down the final result in the register
}
void SL(int reg) //3 bits register
{
    // vasiot kod ovde
    int i;
    for (i=1; i<15; i++)
        registers[reg][i]=registers[reg][i+1];
    registers[reg][i]='0';
    //registers[reg][16]=0;//NULL terminator
}
void SR(int reg) //3 bits register
{
    // vasiot kod ovde
    int i;
    char tmp[17];
    tmp[0]=registers[reg][0];//dont change the SIGN bit!
    for (i=2; i<16; i++)
        tmp[i]=registers[reg][i-1];
    tmp[1]='0';
    //tmp[i]=0;//NULL terminator
    strcpy(registers[reg],tmp);
}

//CUSTOM FUNCTIONS
void strncpyMOJ(char *reg1, char *reg2, int n)
{
    int i;
    for (i=0;i<n;i++)
        reg1[i]=reg2[i];
}
int strcmpMOJ(char *inst1,char *inst2)
{
    int i;
    for (i=0; i<strlen(inst1); i++)
        if (inst1[i]!=inst2[i])
        {
            return 1;
        }
    return 0;
}
int strncmpMOJ(char *inst1,char *inst2, int k)
{
    int i;
    for (i=0; i<k; i++)
        if (inst1[i]!=inst2[i])
        {
            return 1;
        }
    return 0;
}
int dekodirajinstruction(char *instruction)
{
    int iStart=16,iBrojac;
    char tmp[17];
    memset(tmp,'.',sizeof(tmp)); //default value
    while (1)
    {
        strncpyMOJ(tmp,instruction,iStart);
        tmp[16]=0; //NULL terminator
        for (iBrojac=0; iBrojac<14; iBrojac++)
            if (strncmp(tmp,gacMat[iBrojac],iStart)==0)
                return iBrojac+1;
        iStart-=3;
        if (iStart==4)
            return -1; //failed
    }
}
int dekodirajRegister(char *instruction, int instructionn, int *niz) //REWRITE ME!!
{
    int iBrojac;
    if (instructionn==1) //MVI, 13 //mozev ovie vo dodatna f-ja ama ne.. :)
    {
        char tmp1[4];
        niz[0]=1; //kolku zbora zafaka instructionta
        tmp1[0]=instruction[13];
        tmp1[1]=instruction[14];
        tmp1[2]=instruction[15];
        tmp1[3]=0;
        for (iBrojac=0; iBrojac<8; iBrojac++)
            if (strcmpMOJ(tmp1,gacMat1[iBrojac])==0)
            {
                niz[1]=iBrojac;
                break;
            }
    }
    else if (instructionn==2) //MOV, 10
    {
        char tmp1[4],tmp2[4];
        niz[0]=0; //kolku zbora zafaka instructionta
        tmp1[0]=instruction[10];
        tmp1[1]=instruction[11];
        tmp1[2]=instruction[12];
        tmp1[3]=0;
        tmp2[0]=instruction[13];
        tmp2[1]=instruction[14];
        tmp2[2]=instruction[15];
        tmp2[3]=0;
        for (iBrojac=0; iBrojac<8; iBrojac++)
        {
            if (strcmpMOJ(tmp1,gacMat1[iBrojac])==0) //same number may occur twice, if conditions r not chained
            {
                niz[1]=iBrojac;
            }
            if (strcmpMOJ(tmp2,gacMat1[iBrojac])==0)
            {
                niz[2]=iBrojac;
            }
        }
    }
    else if (instructionn==3) //AND, 7
    {
        char tmp1[4],tmp2[4],tmp3[4];
        niz[0]=0; //kolku zbora zafaka instructionta
        tmp1[0]=instruction[7];
        tmp1[1]=instruction[8];
        tmp1[2]=instruction[9];
        tmp1[3]=0;
        tmp2[0]=instruction[10];
        tmp2[1]=instruction[11];
        tmp2[2]=instruction[12];
        tmp2[3]=0;
        tmp3[0]=instruction[13];
        tmp3[1]=instruction[14];
        tmp3[2]=instruction[15];
        tmp3[3]=0;
        for (iBrojac=0; iBrojac<8; iBrojac++)
        {
            if (strcmpMOJ(tmp1,gacMat1[iBrojac])==0) //same number may occur twice, if conditions r not chained
            {
                niz[1]=iBrojac;
            }
            if (strcmpMOJ(tmp2,gacMat1[iBrojac])==0)
            {
                niz[2]=iBrojac;
            }
            if (strcmpMOJ(tmp3,gacMat1[iBrojac])==0)
            {
                niz[3]=iBrojac;
            }
        }
    }
    else if (instructionn==4) //OR, 7
    {
        char tmp1[4],tmp2[4],tmp3[4];
        niz[0]=0; //kolku zbora zafaka instructionta
        tmp1[0]=instruction[7];
        tmp1[1]=instruction[8];
        tmp1[2]=instruction[9];
        tmp1[3]=0;
        tmp2[0]=instruction[10];
        tmp2[1]=instruction[11];
        tmp2[2]=instruction[12];
        tmp2[3]=0;
        tmp3[0]=instruction[13];
        tmp3[1]=instruction[14];
        tmp3[2]=instruction[15];
        tmp3[3]=0;
        for (iBrojac=0; iBrojac<8; iBrojac++)
        {
            if (strcmpMOJ(tmp1,gacMat1[iBrojac])==0) //same number may occur twice, if conditions r not chained
            {
                niz[1]=iBrojac;
            }
            if (strcmpMOJ(tmp2,gacMat1[iBrojac])==0)
            {
                niz[2]=iBrojac;
            }
            if (strcmpMOJ(tmp3,gacMat1[iBrojac])==0)
            {
                niz[3]=iBrojac;
            }
        }
    }
    else if (instructionn==5) //NOT, 10
    {
        char tmp1[4],tmp2[4];
        niz[0]=0; //kolku zbora zafaka instructionta
        tmp1[0]=instruction[10];
        tmp1[1]=instruction[11];
        tmp1[2]=instruction[12];
        tmp1[3]=0;
        tmp2[0]=instruction[13];
        tmp2[1]=instruction[14];
        tmp2[2]=instruction[15];
        tmp2[3]=0;
        for (iBrojac=0; iBrojac<8; iBrojac++)
        {
            if (strcmpMOJ(tmp1,gacMat1[iBrojac])==0) //same number may occur twice, if conditions r not chained
            {
                niz[1]=iBrojac;
            }
            if (strcmpMOJ(tmp2,gacMat1[iBrojac])==0)
            {
                niz[2]=iBrojac;
            }
        }
    }
    else if (instructionn==6) //JMP, 16
    {
        //no registers needed
        niz[0]=1; //kolku zbora zafaka instructionta

    }
    else if (instructionn==7) //LESS, 10
    {
        char tmp1[4],tmp2[4];
        niz[0]=1; //kolku zbora zafaka instructionta
        tmp1[0]=instruction[10];
        tmp1[1]=instruction[11];
        tmp1[2]=instruction[12];
        tmp1[3]=0;
        tmp2[0]=instruction[13];
        tmp2[1]=instruction[14];
        tmp2[2]=instruction[15];
        tmp2[3]=0;
        for (iBrojac=0; iBrojac<8; iBrojac++)
        {
            if (strcmpMOJ(tmp1,gacMat1[iBrojac])==0) //same number may occur twice, if conditions r not chained
            {
                niz[1]=iBrojac;
            }
            if (strcmpMOJ(tmp2,gacMat1[iBrojac])==0)
            {
                niz[2]=iBrojac;
            }
        }
    }
    else if (instructionn==8) //LEQ, 10
    {
        char tmp1[4],tmp2[4];
        niz[0]=1; //kolku zbora zafaka instructionta
        tmp1[0]=instruction[10];
        tmp1[1]=instruction[11];
        tmp1[2]=instruction[12];
        tmp1[3]=0;
        tmp2[0]=instruction[13];
        tmp2[1]=instruction[14];
        tmp2[2]=instruction[15];
        tmp2[3]=0;
        for (iBrojac=0; iBrojac<8; iBrojac++)
        {
            if (strcmpMOJ(tmp1,gacMat1[iBrojac])==0) //same number may occur twice, if conditions r not chained
            {
                niz[1]=iBrojac;
            }
            if (strcmpMOJ(tmp2,gacMat1[iBrojac])==0)
            {
                niz[2]=iBrojac;
            }
        }
    }
    else if (instructionn==9) //GRE, 10
    {
        char tmp1[4],tmp2[4];
        niz[0]=1; //kolku zbora zafaka instructionta
        tmp1[0]=instruction[10];
        tmp1[1]=instruction[11];
        tmp1[2]=instruction[12];
        tmp1[3]=0;
        tmp2[0]=instruction[13];
        tmp2[1]=instruction[14];
        tmp2[2]=instruction[15];
        tmp2[3]=0;
        for (iBrojac=0; iBrojac<8; iBrojac++)
        {
            if (strcmpMOJ(tmp1,gacMat1[iBrojac])==0) //same number may occur twice, if conditions r not chained
            {
                niz[1]=iBrojac;
            }
            if (strcmpMOJ(tmp2,gacMat1[iBrojac])==0)
            {
                niz[2]=iBrojac;
            }
        }
    }
    else if (instructionn==10) //GEQ, 10
    {
        char tmp1[4],tmp2[4];
        niz[0]=1; //kolku zbora zafaka instructionta
        tmp1[0]=instruction[10];
        tmp1[1]=instruction[11];
        tmp1[2]=instruction[12];
        tmp1[3]=0;
        tmp2[0]=instruction[13];
        tmp2[1]=instruction[14];
        tmp2[2]=instruction[15];
        tmp2[3]=0;
        for (iBrojac=0; iBrojac<8; iBrojac++)
        {
            if (strcmpMOJ(tmp1,gacMat1[iBrojac])==0) //same number may occur twice, if conditions r not chained
            {
                niz[1]=iBrojac;
            }
            if (strcmpMOJ(tmp2,gacMat1[iBrojac])==0)
            {
                niz[2]=iBrojac;
            }
        }
    }
    else if (instructionn==11) //PRN, 13
    {
        char tmp[4];
        niz[0]=0; //kolku zbora zafaka instructionta
        tmp[0]=instruction[13];
        tmp[1]=instruction[14];
        tmp[2]=instruction[15];
        tmp[3]=0;
        for (iBrojac=0; iBrojac<8; iBrojac++)
            if (strcmpMOJ(tmp,gacMat1[iBrojac])==0)
            {
                niz[1]=iBrojac;
                break;
            }
    }
    else if (instructionn==12) //SUB, 7
    {
        char tmp1[4],tmp2[4],tmp3[4];
        niz[0]=0; //kolku zbora zafaka instructionta
        tmp1[0]=instruction[7];
        tmp1[1]=instruction[8];
        tmp1[2]=instruction[9];
        tmp1[3]=0;
        tmp2[0]=instruction[10];
        tmp2[1]=instruction[11];
        tmp2[2]=instruction[12];
        tmp2[3]=0;
        tmp3[0]=instruction[13];
        tmp3[1]=instruction[14];
        tmp3[2]=instruction[15];
        tmp3[3]=0;
        for (iBrojac=0; iBrojac<8; iBrojac++)
        {
            if (strcmpMOJ(tmp1,gacMat1[iBrojac])==0) //same number may occur twice, if conditions r not chained
            {
                niz[1]=iBrojac;
            }
            if (strcmpMOJ(tmp2,gacMat1[iBrojac])==0)
            {
                niz[2]=iBrojac;
            }
            if (strcmpMOJ(tmp3,gacMat1[iBrojac])==0)
            {
                niz[3]=iBrojac;
            }
        }
    }
    else if (instructionn==13) //SL, 13
    {
        char tmp[4];
        niz[0]=0; //kolku zbora zafaka instructionta
        tmp[0]=instruction[13];
        tmp[1]=instruction[14];
        tmp[2]=instruction[15];
        tmp[3]=0;
        for (iBrojac=0; iBrojac<8; iBrojac++)
            if (strcmpMOJ(tmp,gacMat1[iBrojac])==0)
            {
                niz[1]=iBrojac;
                break;
            }
    }
    else if (instructionn==14) //SR, 13
    {
        char tmp[4];
        niz[0]=0; //kolku zbora zafaka instructionta
        tmp[0]=instruction[13];
        tmp[1]=instruction[14];
        tmp[2]=instruction[15];
        tmp[3]=0;
        for (iBrojac=0; iBrojac<8; iBrojac++)
            if (strcmpMOJ(tmp,gacMat1[iBrojac])==0)
            {
                niz[1]=iBrojac;
                break;
            }
    }
    else //ERROR
    {
        //this should never occur, do nothing
    }
}
int convert(char *instruction) //convert from binary to decimal
{
    int i,k,rez=0;
    k=strlen(instruction)-1;
    for (i=k; i>=0; i--)
    {
        if (instruction[i]=='1') //pocnuva od nazad
        {
            rez+=pow(2,k-i);
        }
    }
    //if (instruction[0]=='1') rez*=-1; //IM ADDresultS FFS
    return rez;
}
