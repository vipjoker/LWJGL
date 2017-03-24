package com.company.test;

import com.company.openglLessons.ShaderLoader;
import com.sun.prism.ps.Shader;

/**
 * Created by omak on 3/24/17.
 */
public class Test {
    public static void main(String[] args) {
        String s = ShaderLoader.loadFile("res/fragmentShader.glsl");
        System.out.println(s);
        String s2 = ShaderLoader.loadFile("res/vertexShader.glsl");
        System.out.println(s2);
    }
}
