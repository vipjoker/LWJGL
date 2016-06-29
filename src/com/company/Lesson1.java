package com.company;
import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;
//https://github.com/SilverTiger/lwjgl3-tutorial/wiki/Introduction
public class Lesson1 {

    private long window;
    private long smallWindow;

    public void run() {
        System.out.println("Hello LWJGL " + Version.getVersion() + "!");

        try {
            init();
            loop();


            glfwFreeCallbacks(window);
            glfwDestroyWindow(window);
        } finally {

            glfwTerminate();
            glfwSetErrorCallback(null).free();
        }
    }

    private void init() {

        GLFWErrorCallback.createPrint(System.err).set();


        if ( !glfwInit() )
            throw new IllegalStateException("Unable to initialize GLFW");


        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        int WIDTH = 300;
        int HEIGHT = 300;


        window = glfwCreateWindow(WIDTH, HEIGHT, "Hello World!", NULL, NULL);
        smallWindow = glfwCreateWindow(100,100,"Small window", NULL,NULL);

        if (  window == NULL || smallWindow == NULL)
            throw new RuntimeException("Failed to create the GLFW window");

        glfwSetKeyCallback(window, this::onKeyPressed);


        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());


        glfwSetWindowPos(smallWindow,10,10);

        glfwSetWindowPos(
                window,
                (vidmode.width() - WIDTH) / 2,
                (vidmode.height() - HEIGHT) / 2
        );


        glfwMakeContextCurrent(window);

        glfwSwapInterval(1);

        glfwShowWindow(smallWindow);
        glfwShowWindow(window);
    }

    private void loop() {

        GL.createCapabilities();


        glClearColor((float) Math.random(), 0.0f, 0.0f, 0.0f);


        while ( !glfwWindowShouldClose(window) ) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            glClearColor((float) Math.random(), 0.0f, 0.0f, 0.0f);
            glfwSwapBuffers(window);

            glfwPollEvents();
        }
    }






    public void onKeyPressed(long window, int key, int scancode, int action, int mods){
        if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
            glfwSetWindowShouldClose(window, true); // We will detect this in our rendering loop
        if(key == GLFW_KEY_V && action == GLFW_RELEASE) System.out.println("a");
    }

}