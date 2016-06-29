package com.company;

import org.lwjgl.glfw.GLFWErrorCallback;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.Callbacks.*;


/**
 * Created by omakhobei on 6/29/2016.
 */
public class Lesson2 {

    private long windowId;

    public void run() {


        try {
            init();
            loop();
        } finally {
            glfwTerminate();
            glfwSetErrorCallback(null).free();
        }


    }

    private void init() {
         GLFWErrorCallback.createPrint(System.err).set();
        if(!glfwInit())throw new RuntimeException("Cannot load glfw");

        glfwDefaultWindowHints();

        glfwWindowHint(GLFW_RESIZABLE,GLFW_TRUE);
        glfwWindowHint(GLFW_VISIBLE,GLFW_TRUE);

        windowId = glfwCreateWindow(300,300,"Hello again",0,0);

        if(windowId == 0)throw new RuntimeException("Failed to create window");





    }

    private void loop() {

    }

}
