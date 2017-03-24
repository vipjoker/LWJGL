package com.company.openglLessons;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import java.io.IOException;
import java.io.SyncFailedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

/**
 * Created by omak on 3/24/17.
 */
public abstract class ShaderProgram {
    private int programId;
    private int vertexShaderId;
    private int fragmentShaderId;




    public ShaderProgram (String vertexFile,String fragmentFile){
        fragmentShaderId = loadFragmentShader(fragmentFile);
        vertexShaderId = loadVertextShader(vertexFile);
        programId = GL20.glCreateProgram();
        GL20.glAttachShader(programId,fragmentShaderId);
        GL20.glAttachShader(programId,vertexShaderId);

        bindAttributes();
        GL20.glLinkProgram(programId);
        GL20.glValidateProgram(programId);

    }

    protected abstract void bindAttributes();

    public void start(){
        GL20.glUseProgram(programId);
    }

    public void stop(){
        GL20.glUseProgram(0);
    }

    protected void bindAttribute(int attribute,String variableName){


        GL20.glBindAttribLocation(programId,attribute,variableName);
    }

    public void cleanUp(){
        stop();
        GL20.glDetachShader(programId,fragmentShaderId);
        GL20.glDetachShader(programId,vertexShaderId);
        GL20.glDeleteShader(fragmentShaderId);
        GL20.glDeleteShader(vertexShaderId);
        GL20.glDeleteProgram(programId);



    }

    public static int loadVertextShader(String vertexShader){
        return compileShader(vertexShader, GL20.GL_VERTEX_SHADER);
    }

    private static int compileShader(String path, int type){

        String source = loadFile(path);
        int shaderId = GL20.glCreateShader(type);
        GL20.glShaderSource(shaderId,source);
        GL20.glCompileShader(shaderId);

        if(GL20.glGetShaderi(shaderId,GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE){
            System.err.println(GL20.glGetShaderInfoLog(shaderId,500));
            System.exit(-1);
        }

        return shaderId;
    }

    public static int loadFragmentShader(String fragmentShader){
        return compileShader(fragmentShader,GL20.GL_FRAGMENT_SHADER);
    }



    public static String loadFile(String path){
        String file = null;
        Path path1 = Paths.get(path);
        try {
            file= Files.lines(path1).collect(Collectors.joining("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
