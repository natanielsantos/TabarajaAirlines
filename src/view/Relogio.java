package view;

import java.time.LocalTime;
import java.util.*;

public class Relogio implements Runnable{

    public Relogio(){
    }
    @Override
    public void run(){
        while(true){
        	Date hora = Calendar.getInstance().getTime();
            try{
            	System.out.print("\r");
            	System.out.print(hora);
            	
                Thread.sleep(1000);
                
            }catch( InterruptedException e ){}
        }
    }
}