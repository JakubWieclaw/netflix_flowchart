package com.sample;

import java.util.Vector;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;

/**
 * This is a sample class to launch a rule.
 */
public class DroolsTest {

	public static final void main(String[] args) {
		try {
			// load up the knowledge base
			KieServices ks = KieServices.Factory.get();
			KieContainer kContainer = ks.getKieClasspathContainer();
			KieSession kSession = kContainer.newKieSession("ksession-rules");

			// go !
//            Message message = new Message();
//            message.setMessage("Hello World");
//            message.setStatus(Message.HELLO);
//            kSession.insert(message);
            kSession.fireAllRules();
            

	        System.out.println( "[query] Stryjkowie z bratankami/bratanicami:" );
	        QueryResults results = kSession.getQueryResults( "stryjkowie z bratankami" );
	        for ( QueryResultsRow row : results ) {
	        	Relacja relacja = ( Relacja ) row.get( "r" );
	            System.out.println( "[query] " +  relacja.osoby[0] + " jest stryjem " + relacja.osoby[1]);
	        }
	        
	        System.out.println( "[query] Wujowie z siostrzencami/siostrzenicami:" );
	        results = kSession.getQueryResults( "wujkowie z siostrzencami" );
	        for ( QueryResultsRow row : results ) {
	        	Relacja relacja = ( Relacja ) row.get( "r" );
	            System.out.println( "[query] " +  relacja.osoby[0] + " jest wujem " + relacja.osoby[1]);
	        }  
			
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	public static enum Plec {
		NIEZNANA, MEZCZYZNA, KOBIETA
	};

	public static class Osoba {
		public String imie;
		public Plec plec;
		public Vector<Relacja> relacje = new Vector<Relacja>();

		public Osoba(String imie) {
			this.imie = imie;
			this.plec = Plec.NIEZNANA;
		}

		public String toString() {
			return imie;
		}
	}

	public static class Relacja {

		public Osoba[] osoby = new Osoba[2];
		public String relacja;

		public Relacja(Osoba o1, Osoba o2, String relacja) {
			this.osoby[0] = o1;
			this.osoby[1] = o2;
			this.relacja = relacja;
			
			o1.relacje.add(this);
			o2.relacje.add(this);
		}

	}

//    public static class Message {
//
//        public static final int HELLO = 0;
//        public static final int GOODBYE = 1;
//
//        private String message;
//
//        private int status;
//
//        public String getMessage() {
//            return this.message;
//        }
//
//        public void setMessage(String message) {
//            this.message = message;
//        }
//
//        public int getStatus() {
//            return this.status;
//        }
//
//        public void setStatus(int status) {
//            this.status = status;
//        }
//
//    }

}