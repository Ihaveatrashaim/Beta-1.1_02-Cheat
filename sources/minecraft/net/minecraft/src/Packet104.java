package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.io.*;

public class Packet104 extends Packet
{

    public Packet104()
    {
    }

    public void readPacketData(DataInputStream datainputstream) throws IOException
    {
        field_20036_a = datainputstream.readByte();
        short word0 = datainputstream.readShort();
        field_20035_b = new ItemStack[word0];
        for(int i = 0; i < word0; i++)
        {
            short word1 = datainputstream.readShort();
            if(word1 >= 0)
            {
                byte byte0 = datainputstream.readByte();
                short word2 = datainputstream.readShort();
                field_20035_b[i] = new ItemStack(word1, byte0, word2);
            }
        }

    }

    public void writePacketData(DataOutputStream dataoutputstream) throws IOException
    {
        dataoutputstream.writeByte(field_20036_a);
        dataoutputstream.writeShort(field_20035_b.length);
        for(int i = 0; i < field_20035_b.length; i++)
        {
            if(field_20035_b[i] == null)
            {
                dataoutputstream.writeShort(-1);
            } else
            {
                dataoutputstream.writeShort((short)field_20035_b[i].itemID);
                dataoutputstream.writeByte((byte)field_20035_b[i].stackSize);
                dataoutputstream.writeShort((short)field_20035_b[i].itemDamage);
            }
        }

    }

    public void processPacket(NetHandler nethandler)
    {
        nethandler.func_20094_a(this);
    }

    public int getPacketSize()
    {
        return 3 + field_20035_b.length * 5;
    }

    public int field_20036_a;
    public ItemStack field_20035_b[];
}
