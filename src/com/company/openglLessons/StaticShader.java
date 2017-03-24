package com.company.openglLessons;

/**
 * Created by omak on 3/24/17.
 */
public class StaticShader extends ShaderProgram{
    private static final String VERTEX_FILE = "res/vertexShader.glsl";
    private static final String FRAGMENT_FILE = "res/fragmentShader.glsl";
    public StaticShader() {

        super(VERTEX_FILE,FRAGMENT_FILE);
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0,"position");
    }
}
