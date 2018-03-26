package com.timelessapps.javafxtemplate.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import javafx.fxml.FXML;

public class LoggingService 
{
    private SceneHelper sceneHelper;

    public void writeToFile(String fileName, String text) throws FileNotFoundException
    {
        File file = new File(fileName);
        PrintWriter printWriter;
        if ( file.exists() && !file.isDirectory() )
        {
            printWriter = new PrintWriter(new FileOutputStream(new File(fileName), true));
        }
        else 
        {
            printWriter = new PrintWriter(fileName);
        }
        printWriter.println(text);
        printWriter.close();
    }
    
    public void appendToEventLogsFile(String logText) throws FileNotFoundException
    {
        String fileName = "eventLog.txt";
        writeToFile(fileName, logText);
    }
    
    public void appendToApplicationLogsFile(String logText) throws FileNotFoundException
    {
        String fileName = "applicationLog.txt";
        writeToFile(fileName, logText);
    }
    
    public void appendToEventLogsInApplication(String text)
    {
        sceneHelper.setTextArea("eventLogsTabContentArea", text);
    }
    
    public void appendToApplicationLogsInApplication(String text)
    {
        sceneHelper.setTextArea("applicationLogsTabContentArea", text);
    }
    
    //To delete. 
    public void updateEventLogsArea()
    {
        sceneHelper.setTextArea("eventLogsTabContentArea", "hello1");
        /*
        Runnable task = new Runnable()
        {
            public void run()
            {
                eventLogsTabContentArea.setText("hello");
            }
        };
        //Run the task in a background thread
        Thread backgroundThread = new Thread(task);
        //Terminate the running thread if the application exits
        backgroundThread.setDaemon(true);
        //Start the thread
        backgroundThread.start();
        */
    }
    
    @FXML
    public void updateApplicationLogArea()
    {
        
    }
}
