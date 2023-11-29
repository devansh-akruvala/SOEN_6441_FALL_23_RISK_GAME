package com.soen6441.risk_game_u14.log_observer_pattern;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.Serializable;

/**
 * A concrete class implementing the Observer interface.
 * Objects of this class are linked to the Observable LogEntryBuffer.
 * They are notified on any changes.
 */
public class Logger implements Observer, Serializable {
    private static int D_Count = 0;

    /**
     * This method internally updates the log file upon each notification from the observable.
     */
    public void update(Observable p_Observable) {
        String l_Updated = ((LogEntryBuffer) p_Observable).getResult();
        String l_Path = "logs\\LogFile";
        if (D_Count == 0) {
            try {
                PrintWriter l_PrintWriter = new PrintWriter(l_Path);
                l_PrintWriter.println("");
                l_PrintWriter.close();
                D_Count++;

            } catch (Exception l_Exp) {
            }
        }

        try {
            File l_File = new File(l_Path);
            FileWriter l_FileWriter = new FileWriter(l_File, true);
            BufferedWriter l_BufferedWriter = new BufferedWriter(l_FileWriter);
            PrintWriter l_PrintWriter = new PrintWriter(l_BufferedWriter);
            l_PrintWriter.println(l_Updated);
            l_PrintWriter.close();
            l_BufferedWriter.close();
            l_FileWriter.close();
        } catch (Exception l_Exp) {
        }
    }
}

