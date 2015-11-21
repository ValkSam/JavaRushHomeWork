package myTests;

/**
 * Created by Valk on 17.01.15.
 */
public class testInterrupt {
    public static void main(String[] args) throws Exception {
        Thread thread = new Thread4();
        thread.start();
        ((Thread4)thread).showWarning();
        try {Thread.sleep(1000);} catch (InterruptedException e){}
        System.out.println("alive: "+thread.isAlive());
        thread.interrupt();

    }
}

class Thread4 extends Thread{
    @Override
    public void run(){
        //showWarning();
        while(true){
            if (isInterrupted()) return;
        }

    }

    public void showWarning(){
        try {  //когда вызываем showWarning из главного потока, то на Thread.sleep(1000) не будет выброса,
        // т.к. Thread.sleep(1000) к главному потоку относится, а не к this.
        // На this.join() тоже не споткнется т.к. присоетиненяется основной поток (он текущий сейчас), а он не в interrupt
            System.out.println("   "+(Thread.currentThread().getName()+" * " + this.getName()));
            this.interrupt();
            System.out.println(this.isInterrupted()); //true
            //Thread.sleep(1000);
            this.join();
        }
        catch (InterruptedException e){
            System.out.println("выброс");
        };
    }
}
