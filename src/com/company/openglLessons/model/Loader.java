package com.company.openglLessons.model;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by omak on 3/23/17.
 */
public class Loader {
    private List<Integer> vaos = new ArrayList<>();
    private List<Integer> vbos = new ArrayList<>();
    private static final int FLOAT_LENGTH = 4;

    public RawModel loadToVAO (float[] positions,int[] indices){

        int vaoId = createVAO();
        bindIndicesBuffer(indices);
        storeDataInAtrributeList(0,positions);
        unbindVAO();

        return new RawModel(vaoId,positions.length/3);
    }

    public int createVAO(){
        int vaoId = GL30.glGenVertexArrays();
        vaos.add(vaoId);
        GL30.glBindVertexArray(vaoId);
        return vaoId;
    }

    private void storeDataInAtrributeList(int attributeNumber,float[] data){
        int vboId = GL15.glGenBuffers();
        vbos.add(vboId);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER,vboId);
        FloatBuffer buffer = storeDataInFloatBuffer(data);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER,buffer,GL15.GL_STATIC_DRAW);

        GL20.glVertexAttribPointer(attributeNumber,3, GL11.GL_FLOAT,false,0,0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER,0);

    }

    private FloatBuffer storeDataInFloatBuffer(float[] date){

        FloatBuffer floatBuffer = BufferUtils.createFloatBuffer(date.length);
        floatBuffer.put(date);
        floatBuffer.flip();
        return floatBuffer;
    }

    private void bindIndicesBuffer(int[] indices){
        int vboId = GL15.glGenBuffers();
        vbos.add(vboId);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER,vboId);
        IntBuffer buffer = storeDateInIntBuffer(indices);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER,buffer,GL15.GL_STATIC_DRAW);
    }

    private IntBuffer storeDateInIntBuffer(int[] data){
        IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }


    private void unbindVAO(){
        GL30.glBindVertexArray(0);
    }

    public void clean(){
        for (Integer vbo : vbos) {
            GL15.glDeleteBuffers(vbo);
        }

        for (Integer vao : vaos) {
            GL30.glDeleteVertexArrays(vao);
        }
    }

}
