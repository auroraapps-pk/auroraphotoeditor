package com.auroraapps.auroraphotoeditor.AuroraDovCharney;

import java.util.ArrayList;

public class AuroraSunnyFong {


    public ArrayList<String> categoriesArray = new ArrayList<String>();
    public ArrayList<holder> arHolder = new ArrayList<holder>();

    public AuroraSunnyFong(){
        categoriesArray.add("A");//0
        categoriesArray.add("B");//1
        categoriesArray.add("C");//2
        categoriesArray.add("D");//3
        categoriesArray.add("E");//4
        categoriesArray.add("F");//5
        categoriesArray.add("G");//6
        categoriesArray.add("H");//7
        categoriesArray.add("I");//8
        categoriesArray.add("J");//9
        categoriesArray.add("K");//10
        categoriesArray.add("L");//11
        categoriesArray.add("M");//12
        categoriesArray.add("N");//13
        categoriesArray.add("O");//14
        categoriesArray.add("P");//15
        categoriesArray.add("Q");//16
        categoriesArray.add("R");//17
        categoriesArray.add("S");//18
        categoriesArray.add("T");//19
        categoriesArray.add("U");//20
        categoriesArray.add("V");//21
        categoriesArray.add("W");//22
        categoriesArray.add("X");//23
        categoriesArray.add("Y");//24
        categoriesArray.add("Z");//25

//        fillValues();
    }

    public class holder{
        public String ID,Value;
        public holder(String _ID,String _Value){
            this.ID = _ID;
            this.Value = _Value;
        }
    }

}
