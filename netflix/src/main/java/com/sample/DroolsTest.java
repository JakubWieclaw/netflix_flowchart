package com.sample;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import java.util.Scanner;

/**
 * This is a sample class to launch a rule.
 */
public class DroolsTest {
    public static final class GUI{
    	Scanner scanner = new Scanner(System.in);
        public String answer;
        public void insertQuestionAndSetAnswer(String question, String[] possible_answers, boolean gotMovie){
            if (gotMovie == false){
                System.out.println(question);
                for (int i = 0; i < possible_answers.length; i++){
                    System.out.println(i + ". " + possible_answers[i]);
                }
                System.out.println("Please enter the number of your answer:");
                answer = scanner.nextLine();
                System.out.println("Your answer is: " + answer);
            }
            else{
                System.out.println("Your movie is: " + question);
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
