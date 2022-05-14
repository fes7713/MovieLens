/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Icon;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fes77
 */
public class AnimationThread extends Thread{
//    AnimationType type;
    int start;
    int target;
    double speed;
    double acc;
    
    //Interface for returning value for parent object
    interface AnimationUpdater
    {
        public void update(int returnValue);
        public boolean isHover();
    }
    
    AnimationUpdater updater;
    
    public AnimationThread(AnimationType animationType, AnimationUpdater updater)
    {
        this.updater = updater;
        switch(animationType)
        {
            case LINEAR_GROW->
            {
                start = 0;
                target = 1000;
                speed = 60;
                acc = 1;
            }
            case LINEAR_SHRINK -> {
                start = 0;
                target = - 1000;
                speed = -60;
                acc = 1;
            }
            case LINEAR_GROW_FAST -> {
                start = 0;
                target = 1000;
                speed = 100;
                acc = 1;
            }
            case LINEAR_SHRINK_FAST -> {
                start = 0;
                target = -1000;
                speed = -100;
                acc = 1;
            }

            case ACC_GROW -> {
                // 62 frames
                start = 0;
                target = 1000;
                speed = 10;
                acc = 1.015;
            }
            case ACC_SHRINK -> {
                // 62 frames
                start = 0;
                target = -1000;
                speed = -10;
                acc = 1.015;
            }
            case DESC_GROW -> {
                // 48 frames
                start = 0;
                target = 1000;
                speed = 30;
                acc = 0.985;
            }
            case DESC_SHRINK -> {
                // 48 frames
                start = 0;
                target = -1000;
                speed = -30;
                acc = 0.985;                    
            }
            case ACC_GROW_FAST -> {
                // 37 frames
                start = 0;
                target = 1000;
                speed = 0.05;
                acc = 1.25;
            }
            case ACC_SHRINK_FAST -> {
                // 37 frames
                start = 0;
                target = -1000;
                speed = -0.05;
                acc = 1.25;
            }
            case DESC_GROW_FAST -> {
                // 29 frames
                start = 0;
                target = 1000;
                speed = 180.3;
                acc = 0.85;
            }
            case DESC_SHRINK_FAST -> {
                start = 0;
                target = -1000;
                speed = -180.3;
                acc = 0.85;
            }
            case DESC_GROW_SLOW -> {
                // 48 frames
                start = 0;
                target = 1000;
//                speed = 10;
                speed = 20;
//                acc = 0.99215;
                acc = 0.9828;
            }
            case DESC_SHRINK_SLOW -> {
                // 48 frames
                start = 0;
                target = -1000;
                speed = -10;
                acc = 0.985;   
            }

            default -> throw new AssertionError(animationType.name());
        }
    }
    
    @Override
    public void run() {
//        super.run();

        


        int frames = 0;
        while(updater.isHover() & start * Integer.signum((int)(speed * 100000)) < target * Integer.signum((int)(speed * 100000)))
        {

            try {
                sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(BaseIcon.class.getName()).log(Level.SEVERE, null, ex);
            }
            speed *= acc;

            if(Math.abs(speed) < 1 && acc < 1)
                throw new IllegalArgumentException("Animation cannot complete [" + frames + "]");

            // Update value and repaint in this function
            start += speed;
            updater.update(start);
            
            
            System.out.println("Speed [frame]" + speed + "[" + frames + "]");
            frames ++ ;
            if(frames > 1000)
                throw new IllegalArgumentException("Animation takes too many frames [" + frames + "]");
        }
        
        updater.update(target);
        System.out.println("Finished");


    }
            

}
