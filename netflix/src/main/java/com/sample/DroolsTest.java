package com.sample;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is a sample class to launch a rule.
 */
public class DroolsTest {
    public static final class GUI{
        public String answer;
        public void insertQuestionAndSetAnswer(String question, String[] possible_answers, boolean gotMovie){
            if (!gotMovie){
                int option = JOptionPane.showOptionDialog(null, question, "Select an answer", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, possible_answers, possible_answers[0]);
                if (option >= 0) {
                    answer = possible_answers[option];
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Your movie is: " + question);
            }
        }
    }

    public static final void main(String[] args) {
        try {
            // load up the knowledge base
	        KieServices ks = KieServices.Factory.get();
    	    KieContainer kContainer = ks.getKieClasspathContainer();
        	KieSession kSession = kContainer.newKieSession("ksession-rules");
            kSession.setGlobal("gui", new GUI());
            kSession.fireAllRules();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }


}
