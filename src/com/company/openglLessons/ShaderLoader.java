package com.company.openglLessons;

import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

/**
 * Created by omak on 3/24/17.
 */
public class ShaderLoader  {



    public static int loadVertextShader(String vertexShader){
        return compileShader(vertexShader,GL20.GL_VERTEX_SHADER);
    }

    private static int compileShader(String path, int type){
        int shaderId = GL20.glCreateShader(type);
        GL20.glShaderSource(shaderId,path);
        GL20.glCompileShader(shaderId);
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
