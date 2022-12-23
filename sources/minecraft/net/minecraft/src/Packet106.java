package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.io.*;

public class Packet106 extends Packet
{

    public Packet106()
    {
    }

    public Packet106(int i, short word0, boolean flag)
    {
        field_20029_a = i;
        field_20028_b = word0;
        field_20030_c = flag;
    }

    public void processPacket(NetHandler nethandler)
    {
        nethandler.func_20089_a(this);
    }

    public void readPacketData(DataInputStream datainputstream) throws IOException
    {
        field_20029_a = datainputstream.readByte();
        field_20028_b = datainputstream.readShort();
        field_20030_c = datainputstream.readByte() != 0;
    }

    public void writePacketData(DataOutputStream dataoutputstream) throws IOException
    {
        dataoutputstream.writeByte(field_20029_a);
        dataoutputstream.writeShort(field_20028_b);
        dataoutputstream.writeByte(field_20030_c ? 1 : 0);
    }

    public int getPacketSize()
    {
        return 4;
    }

    public int field_20029_a;
    public short field_20028_b;
    public boolean field_20030_c;
}
