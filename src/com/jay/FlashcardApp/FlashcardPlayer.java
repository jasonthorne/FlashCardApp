package com.jay.FlashcardApp;

import javax.swing.*;

public class FlashcardPlayer {

    //constructor
    public FlashcardPlayer(){

    }

    public static void main (String[] args){

        //create the frame on the event dispatching thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                new FlashcardPlayer(); //this will run our 'FlashcardPlayer'

            }
        });

    }

}
