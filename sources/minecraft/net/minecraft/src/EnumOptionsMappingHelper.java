package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 


class EnumOptionsMappingHelper
{

    static final int field_20155_a[]; /* synthetic field */

    static 
    {
        field_20155_a = new int[EnumOptions.values().length];
        try
        {
            field_20155_a[EnumOptions.INVERT_MOUSE.ordinal()] = 1;
        }
        catch(NoSuchFieldError nosuchfielderror) { }
        try
        {
            field_20155_a[EnumOptions.VIEW_BOBBING.ordinal()] = 2;
        }
        catch(NoSuchFieldError nosuchfielderror1) { }
        try
        {
            field_20155_a[EnumOptions.ANAGLYPH.ordinal()] = 3;
        }
        catch(NoSuchFieldError nosuchfielderror2) { }
        try
        {
            field_20155_a[EnumOptions.LIMIT_FRAMERATE.ordinal()] = 4;
        }
        catch(NoSuchFieldError nosuchfielderror3) { }
    }
}
