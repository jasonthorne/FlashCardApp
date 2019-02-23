package com.jay.FlashcardApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

public class FlashcardPlayer {

    //instance vars (private to this class, so can have same names as in 'FlashcardBuilder'):
    private JTextArea display;
    private JTextArea answer;
    private ArrayList<Flashcard> cardList;
    private Iterator cardIterator;
    private Flashcard currentCard;
    private int currentCardIndex;
    private JFrame frame;
    private JButton showAnwserButton;
    private boolean isShowAnswer;


    //constructor
    public FlashcardPlayer(){

        //create a frame
        frame = new JFrame("Flashcard Player"); //"Flashcard Player" is title

        //provide graceful close of frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //create a JPanel to contain frame contents (Goes on top of frame)
        JPanel mainPanel = new JPanel();

        //create a font from 'Font' class
        Font myFont = new Font("Helvetica new", Font.BOLD, 22); //type, style, size

        //create a text area for display
        display = new JTextArea(10,20); //row, column
        display.setFont(myFont); //add myFont

        //create JScrollPanes to add a scroll to text areas
        JScrollPane questionScrollPane = new JScrollPane(display); //create question scroll pane, PASSING IN 'display' text area
        questionScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS); //ensure scroll bar is always vertical
        questionScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); //ensure NO horizonal scrollbar


        //create next button
        showAnwserButton = new JButton("Show answer");
        showAnwserButton.addActionListener(new NextCardListener()); //create and add a 'NextCardListener' obj to button's actionListener

        //add components to main panel (ORDER MATTERS!)
        mainPanel.add(questionScrollPane);
        mainPanel.add(showAnwserButton);

        //Add mainPanel to frame
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel); //get the content pane from the frame, and add components to that. (one which centers everything, and 'mainPanel')
        frame.setSize(640, 500); //height, width
        frame.setVisible(true); //MAKE FRAME VISIBLE!!! :P



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

    private class NextCardListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
