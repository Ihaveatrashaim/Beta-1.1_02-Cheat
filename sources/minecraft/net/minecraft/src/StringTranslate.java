package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.io.IOException;
import java.util.Properties;

public class StringTranslate
{

    private StringTranslate()
    {
        field_20164_b = new Properties();
        try
        {
            field_20164_b.load((StringTranslate.class).getResourceAsStream("/lang/en_US.lang"));
        }
        catch(IOException ioexception)
        {
            ioexception.printStackTrace();
        }
    }

    public static StringTranslate func_20162_a()
    {
        return field_20165_a;
    }

    public String func_20163_a(String s)
    {
        return field_20164_b.getProperty(s, s);
    }

    public String func_20160_a(String s, Object aobj[])
    {
        String s1 = field_20164_b.getProperty(s, s);
        return String.format(s1, aobj);
    }

    public String func_20161_b(String s)
    {
        return field_20164_b.getProperty((new StringBuilder()).append(s).append(".name").toString(), "");
    }

    private static StringTranslate field_20165_a = new StringTranslate();
    private Properties field_20164_b;

}
