package br.com.spedison.examples;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class _05_UsingBufferBytes {
    public static void main(String[] args) {
        ByteBuffer bb = ByteBuffer.allocate(Integer.BYTES);
        int valInt = 256;
        bb.putInt(valInt);
        byte[] bytes = bb.array();

        System.out.println("int " + valInt + " -> (byte[]) "+Arrays.toString(bytes));


        bb.clear(); //= ByteBuffer.allocate(Integer.BYTES);
        byte[] zeroZeroElevenEleven = {(byte) 0x00, (byte) 0x00, (byte) 0x0B, (byte) 0x0B};
        bb.put(zeroZeroElevenEleven);
        bb.rewind();
        System.out.println("The number is " + bb.getInt());
    }
}
