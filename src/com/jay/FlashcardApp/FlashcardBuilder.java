package com.jay.FlashcardApp;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FlashcardBuilder {

    //instance vars:
    private JTextArea question;
    private JTextArea answwer;
    private ArrayList<Flashcard> cardList; //arraylist of flashcard objs from 'Flashcard' class
    private JFrame frame;



    //constructor
    public FlashcardBuilder(){
        //build the user interface

        //create a frame
        frame = new JFrame("Flashcard");

        //provide gracefull close of frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //create a JPanel to hold everything together
        JPanel jPanel = new JPanel();

        //create a font from 'Font' class (which is seperate from Swing classes)
        Font myFont = new Font("Helvetica New", Font.BOLD, 21); //type, style, size
        

    }

    public static void main(String[] args){

        System.out.println("Sup world!");

        //create the frame on the event dispatching thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FlashcardBuilder();//this will run our 'FlashcardBuilder'
            }
        });
    }


}


