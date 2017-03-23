package com.company.test;

import com.company.openglLessons.DisplayManager;
import com.company.openglLessons.Renderer;
import com.company.openglLessons.model.Loader;
import com.company.openglLessons.model.RawModel;
import org.lwjgl.opengl.Display;

/**
 * Created by omakhobei on 6/29/2016.
 */
public class Main {
    public static void main(String[] args) {



        DisplayManager.createDisplay();
        Loader loader = new Loader();
        Renderer renderer = new Renderer();

        float []vertices = {
               -.5f,-.5f,
                -.5f,.5f,
                .5f,.5f,
                .5f,-.5f
        };

        int [] indices = {
                0,1,2,
                3,2,0
        };


        RawModel rawModel = loader.loadToVAO(vertices,indices);

        while (!Display.isCloseRequested()){
            renderer.prepare();
            renderer.render(rawModel);


            DisplayManager.updateDisplay();


        }
        loader.clean();
        DisplayManager.closeDisplay();
    }
}
