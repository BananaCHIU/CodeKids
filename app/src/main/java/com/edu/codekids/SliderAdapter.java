package com.edu.codekids;

import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    //Arrays
    public String[] slide_title = {
            "Basic Syntax",
            "Basic Syntax",
            "Identifiers",
            "Modifiers",
            "Variables,Arrays and Enums"
    };

    public String[] slide_content= {
            //P1
            "Object − Objects have states and behaviors. Example: A dog has states - color, name, breed as well as behavior such as wagging their tail, barking, eating. An object is an instance of a class.\n" +
                    "\n\n" +
                    "Class − A class can be defined as a template/blueprint that describes the behavior/state that the object of its type supports.\n" +
                    "\n\n" +
                    "Methods − A method is basically a behavior. A class can contain many methods. It is in methods where the logics are written, data is manipulated and all the actions are executed.\n" +
                    "\n\n" +
                    "Instance Variables − Each object has its unique set of instance variables. An object's state is created by the values assigned to these instance variables.",
            //P2
            "Case Sensitivity − Java is case sensitive, which means identifier Hello and hello would have different meaning in Java.\n" +
                    "\n" +
                    "Class Names − For all class names the first letter should be in Upper Case. If several words are used to form a name of the class, each inner word's first letter should be in Upper Case.\n" +
                    "\n" +
                    "Example: class MyFirstJavaClass\n" +
                    "\n" +
                    "Method Names − All method names should start with a Lower Case letter. If several words are used to form the name of the method, then each inner word's first letter should be in Upper Case.\n" +
                    "\n" +
                    "Example: public void myMethodName()\n" +
                    "\n" +
                    "Program File Name − Name of the program file should exactly match the class name.\n" +
                    "\n" +
                    "When saving the file, you should save it using the class name (Remember Java is case sensitive) and append '.java' to the end of the name (if the file name and the class name do not match, your program will not compile).\n" +
                    "\n" +
                    "But please make a note that in case you do not have a public class present in the file then file name can be different than class name. It is also not mandatory to have a public class in the file.\n" +
                    "\n" +
                    "Example: Assume 'MyFirstJavaProgram' is the class name. Then the file should be saved as 'MyFirstJavaProgram.java'\n" +
                    "\n" +
                    "public static void main(String args[]) − Java program processing starts from the main() method which is a mandatory part of every Java program.\n"+


                    "",
            //P3
            "All Java components require names. Names used for classes, variables, and methods are called identifiers.\n" +
                    "\n" +
                    "In Java, there are several points to remember about identifiers. They are as follows −\n" +
                    "\n" +
                    "All identifiers should begin with a letter (A to Z or a to z), currency character ($) or an underscore (_).\n" +
                    "\n" +
                    "After the first character, identifiers can have any combination of characters.\n" +
                    "\n" +
                    "A key word cannot be used as an identifier.\n" +
                    "\n" +
                    "Most importantly, identifiers are case sensitive.\n" +
                    "\n" +
                    "Examples of legal identifiers: age, $salary, _value, __1_value.\n" +
                    "\n" +
                    "Examples of illegal identifiers: 123abc, -salary." +

                    "" +
                    "",


            //P4
            "Like other languages, it is possible to modify classes, methods, etc., by using modifiers. There are two categories of modifiers −\n" +
                    "\n" +
                    "Access Modifiers − default, public , protected, private\n" +
                    "\n" +
                    "Non-access Modifiers − final, abstract, strictfp\n" +
                    "\n" +
                    "We will be looking into more details about modifiers in the next section.",
            //P5
            "Java Variables\n" +
                    "Following are the types of variables in Java −\n" +
                    "\n" +
                    "Local Variables\n" +
                    "Class Variables (Static Variables)\n" +
                    "Instance Variables (Non-static Variables)\n" +
                    "Java Arrays\n" +
                    "Arrays are objects that store multiple variables of the same type. However, an array itself is an object on the heap. We will look into how to declare, construct, and initialize in the upcoming chapters.\n" +
                    "\n" +
                    "Java Enums\n" +
                    "Enums were introduced in Java 5.0. Enums restrict a variable to have one of only a few predefined values. The values in this enumerated list are called enums.\n" +
                    "\n" +
                    "With the use of enums it is possible to reduce the number of bugs in your code.\n" +
                    "\n" +
                    "For example, if we consider an application for a fresh juice shop, it would be possible to restrict the glass size to small, medium, and large. This would make sure that it would not allow anyone to order any size other than small, medium, or large."
};


    @Override
    public int getCount() {
        return slide_title.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == (ConstraintLayout) o;
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position){

        layoutInflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        TextView slideTitle = (TextView) view.findViewById(R.id.slide_title);
        TextView slideContent = (TextView) view.findViewById(R.id.slide_content);
        slideContent.setMovementMethod(new ScrollingMovementMethod());
        slideTitle.setText(slide_title[position]);
        slideContent.setText(slide_content[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object){

        container.removeView((ConstraintLayout)object);
    }



}