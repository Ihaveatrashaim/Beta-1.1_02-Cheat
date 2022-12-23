package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import org.lwjgl.opengl.GL11;

public class RenderPlayer extends RenderLiving
{

    public RenderPlayer()
    {
        super(new ModelBiped(0.0F), 0.5F);
        field_209_f = (ModelBiped)unusedRenderBlocks;
        field_208_g = new ModelBiped(1.0F);
        field_207_h = new ModelBiped(0.5F);
    }

    protected boolean func_187_a(EntityPlayer entityplayer, int i)
    {
        ItemStack itemstack = entityplayer.inventory.armorItemInSlot(3 - i);
        if(itemstack != null)
        {
            Item item = itemstack.getItem();
            if(item instanceof ItemArmor)
            {
                ItemArmor itemarmor = (ItemArmor)item;
                loadTexture((new StringBuilder()).append("/armor/").append(armorFilenamePrefix[itemarmor.renderIndex]).append("_").append(i != 2 ? 1 : 2).append(".png").toString());
                ModelBiped modelbiped = i != 2 ? field_208_g : field_207_h;
                modelbiped.bipedHead.field_1403_h = i == 0;
                modelbiped.field_1285_b.field_1403_h = i == 0;
                modelbiped.field_1284_c.field_1403_h = i == 1 || i == 2;
                modelbiped.bipedRightArm.field_1403_h = i == 1;
                modelbiped.bipedLeftArm.field_1403_h = i == 1;
                modelbiped.bipedRightLeg.field_1403_h = i == 2 || i == 3;
                modelbiped.bipedLeftLeg.field_1403_h = i == 2 || i == 3;
                setRenderPassModel(modelbiped);
                return true;
            }
        }
        return false;
    }

    public void func_188_a(EntityPlayer entityplayer, double d, double d1, double d2, 
            float f, float f1)
    {
        ItemStack itemstack = entityplayer.inventory.getCurrentItem();
        field_208_g.field_1278_i = field_207_h.field_1278_i = field_209_f.field_1278_i = itemstack != null;
        field_208_g.field_1277_j = field_207_h.field_1277_j = field_209_f.field_1277_j = entityplayer.isSneaking();
        double d3 = d1 - (double)entityplayer.yOffset;
        if(entityplayer.field_12240_bw)
        {
            d3 -= 0.125D;
        }
        super.doRenderLiving(entityplayer, d, d3, d2, f, f1);
        field_208_g.field_1277_j = field_207_h.field_1277_j = field_209_f.field_1277_j = false;
        field_208_g.field_1278_i = field_207_h.field_1278_i = field_209_f.field_1278_i = false;
        float f2 = 1.6F;
        float f3 = 0.01666667F * f2;
        float f4 = entityplayer.getDistanceToEntity(renderManager.field_1226_h);
        float f5 = entityplayer.isSneaking() ? 32F : 64F;
        if(f4 < f5)
        {
            f3 = (float)((double)f3 * (Math.sqrt(f4) / 2D));
            FontRenderer fontrenderer = getFontRendererFromRenderManager();
            GL11.glPushMatrix();
            GL11.glTranslatef((float)d + 0.0F, (float)d1 + 2.3F, (float)d2);
            GL11.glNormal3f(0.0F, 1.0F, 0.0F);
            GL11.glRotatef(-renderManager.field_1225_i, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(renderManager.field_1224_j, 1.0F, 0.0F, 0.0F);
            GL11.glScalef(-f3, -f3, f3);
            String s = entityplayer.field_771_i;
            GL11.glDisable(2896 /*GL_LIGHTING*/);
            if(!entityplayer.isSneaking())
            {
                GL11.glDepthMask(false);
                GL11.glDisable(2929 /*GL_DEPTH_TEST*/);
                GL11.glEnable(3042 /*GL_BLEND*/);
                GL11.glBlendFunc(770, 771);
                Tessellator tessellator = Tessellator.instance;
                byte byte0 = 0;
                if(entityplayer.field_771_i.equals("deadmau5"))
                {
                    byte0 = -10;
                }
                GL11.glDisable(3553 /*GL_TEXTURE_2D*/);
                tessellator.startDrawingQuads();
                int j = fontrenderer.getStringWidth(s) / 2;
                tessellator.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.25F);
                tessellator.addVertex(-j - 1, -1 + byte0, 0.0D);
                tessellator.addVertex(-j - 1, 8 + byte0, 0.0D);
                tessellator.addVertex(j + 1, 8 + byte0, 0.0D);
                tessellator.addVertex(j + 1, -1 + byte0, 0.0D);
                tessellator.draw();
                GL11.glEnable(3553 /*GL_TEXTURE_2D*/);
                fontrenderer.drawString(s, -fontrenderer.getStringWidth(s) / 2, byte0, 0x20ffffff);
                GL11.glEnable(2929 /*GL_DEPTH_TEST*/);
                GL11.glDepthMask(true);
                fontrenderer.drawString(s, -fontrenderer.getStringWidth(s) / 2, byte0, -1);
                GL11.glEnable(2896 /*GL_LIGHTING*/);
                GL11.glDisable(3042 /*GL_BLEND*/);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                GL11.glPopMatrix();
            } else
            {
                GL11.glTranslatef(0.0F, 0.25F / f3, 0.0F);
                GL11.glDepthMask(false);
                GL11.glEnable(3042 /*GL_BLEND*/);
                GL11.glBlendFunc(770, 771);
                Tessellator tessellator1 = Tessellator.instance;
                GL11.glDisable(3553 /*GL_TEXTURE_2D*/);
                tessellator1.startDrawingQuads();
                int i = fontrenderer.getStringWidth(s) / 2;
                tessellator1.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.25F);
                tessellator1.addVertex(-i - 1, -1D, 0.0D);
                tessellator1.addVertex(-i - 1, 8D, 0.0D);
                tessellator1.addVertex(i + 1, 8D, 0.0D);
                tessellator1.addVertex(i + 1, -1D, 0.0D);
                tessellator1.draw();
                GL11.glEnable(3553 /*GL_TEXTURE_2D*/);
                GL11.glDepthMask(true);
                fontrenderer.drawString(s, -fontrenderer.getStringWidth(s) / 2, 0, 0x20ffffff);
                GL11.glEnable(2896 /*GL_LIGHTING*/);
                GL11.glDisable(3042 /*GL_BLEND*/);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                GL11.glPopMatrix();
            }
        }
    }

    protected void func_4015_a(EntityPlayer entityplayer, float f)
    {
        ItemStack itemstack = entityplayer.inventory.armorItemInSlot(3);
        if(itemstack != null && itemstack.getItem().shiftedIndex < 256)
        {
            GL11.glPushMatrix();
            field_209_f.bipedHead.func_926_b(0.0625F);
            if(RenderBlocks.func_1219_a(Block.blocksList[itemstack.itemID].getRenderType()))
            {
                float f1 = 0.625F;
                GL11.glTranslatef(0.0F, -0.25F, 0.0F);
                GL11.glRotatef(180F, 0.0F, 1.0F, 0.0F);
                GL11.glScalef(f1, -f1, f1);
            }
            renderManager.field_4236_f.renderItem(itemstack);
            GL11.glPopMatrix();
        }
        if(entityplayer.field_771_i.equals("deadmau5") && func_140_a(entityplayer.field_20047_bv, null))
        {
            for(int i = 0; i < 2; i++)
            {
                float f2 = (entityplayer.prevRotationYaw + (entityplayer.rotationYaw - entityplayer.prevRotationYaw) * f) - (entityplayer.prevRenderYawOffset + (entityplayer.renderYawOffset - entityplayer.prevRenderYawOffset) * f);
                float f6 = entityplayer.prevRotationPitch + (entityplayer.rotationPitch - entityplayer.prevRotationPitch) * f;
                GL11.glPushMatrix();
                GL11.glRotatef(f2, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(f6, 1.0F, 0.0F, 0.0F);
                GL11.glTranslatef(0.375F * (float)(i * 2 - 1), 0.0F, 0.0F);
                GL11.glTranslatef(0.0F, -0.375F, 0.0F);
                GL11.glRotatef(-f6, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-f2, 0.0F, 1.0F, 0.0F);
                float f7 = 1.333333F;
                GL11.glScalef(f7, f7, f7);
                field_209_f.func_20095_a(0.0625F);
                GL11.glPopMatrix();
            }

        }
        if(func_140_a(entityplayer.field_20067_q, null))
        {
            GL11.glPushMatrix();
            GL11.glTranslatef(0.0F, 0.0F, 0.125F);
            double d = (entityplayer.field_20066_r + (entityplayer.field_20063_u - entityplayer.field_20066_r) * (double)f) - (entityplayer.prevPosX + (entityplayer.posX - entityplayer.prevPosX) * (double)f);
            double d1 = (entityplayer.field_20065_s + (entityplayer.field_20062_v - entityplayer.field_20065_s) * (double)f) - (entityplayer.prevPosY + (entityplayer.posY - entityplayer.prevPosY) * (double)f);
            double d2 = (entityplayer.field_20064_t + (entityplayer.field_20061_w - entityplayer.field_20064_t) * (double)f) - (entityplayer.prevPosZ + (entityplayer.posZ - entityplayer.prevPosZ) * (double)f);
            float f8 = entityplayer.prevRenderYawOffset + (entityplayer.renderYawOffset - entityplayer.prevRenderYawOffset) * f;
            double d3 = MathHelper.sin((f8 * 3.141593F) / 180F);
            double d4 = -MathHelper.cos((f8 * 3.141593F) / 180F);
            float f9 = (float)d1 * 10F;
            if(f9 < -6F)
            {
                f9 = -6F;
            }
            if(f9 > 32F)
            {
                f9 = 32F;
            }
            float f10 = (float)(d * d3 + d2 * d4) * 100F;
            float f11 = (float)(d * d4 - d2 * d3) * 100F;
            if(f10 < 0.0F)
            {
                f10 = 0.0F;
            }
            float f12 = entityplayer.field_775_e + (entityplayer.field_774_f - entityplayer.field_775_e) * f;
            f9 += MathHelper.sin((entityplayer.prevDistanceWalkedModified + (entityplayer.distanceWalkedModified - entityplayer.prevDistanceWalkedModified) * f) * 6F) * 32F * f12;
            GL11.glRotatef(6F + f10 / 2.0F + f9, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(f11 / 2.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(-f11 / 2.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(180F, 0.0F, 1.0F, 0.0F);
            field_209_f.func_20096_b(0.0625F);
            GL11.glPopMatrix();
        }
        ItemStack itemstack1 = entityplayer.inventory.getCurrentItem();
        if(itemstack1 != null)
        {
            GL11.glPushMatrix();
            field_209_f.bipedRightArm.func_926_b(0.0625F);
            GL11.glTranslatef(-0.0625F, 0.4375F, 0.0625F);
            if(entityplayer.fishEntity != null)
            {
                itemstack1 = new ItemStack(Item.stick.shiftedIndex);
            }
            if(itemstack1.itemID < 256 && RenderBlocks.func_1219_a(Block.blocksList[itemstack1.itemID].getRenderType()))
            {
                float f3 = 0.5F;
                GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
                f3 *= 0.75F;
                GL11.glRotatef(20F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45F, 0.0F, 1.0F, 0.0F);
                GL11.glScalef(f3, -f3, f3);
            } else
            if(Item.itemsList[itemstack1.itemID].isFull3D())
            {
                float f4 = 0.625F;
                if(Item.itemsList[itemstack1.itemID].shouldRotateAroundWhenRendering())
                {
                    GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
                    GL11.glTranslatef(0.0F, -0.125F, 0.0F);
                }
                GL11.glTranslatef(0.0F, 0.1875F, 0.0F);
                GL11.glScalef(f4, -f4, f4);
                GL11.glRotatef(-100F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45F, 0.0F, 1.0F, 0.0F);
            } else
            {
                float f5 = 0.375F;
                GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
                GL11.glScalef(f5, f5, f5);
                GL11.glRotatef(60F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(-90F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(20F, 0.0F, 0.0F, 1.0F);
            }
            renderManager.field_4236_f.renderItem(itemstack1);
            GL11.glPopMatrix();
        }
    }

    protected void func_186_b(EntityPlayer entityplayer, float f)
    {
        float f1 = 0.9375F;
        GL11.glScalef(f1, f1, f1);
    }

    public void drawFirstPersonHand()
    {
        field_209_f.field_1244_k = 0.0F;
        field_209_f.setRotationAngles(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
        field_209_f.bipedRightArm.render(0.0625F);
    }

    protected void preRenderCallback(EntityLiving entityliving, float f)
    {
        func_186_b((EntityPlayer)entityliving, f);
    }

    protected boolean shouldRenderPass(EntityLiving entityliving, int i)
    {
        return func_187_a((EntityPlayer)entityliving, i);
    }

    protected void renderEquippedItems(EntityLiving entityliving, float f)
    {
        func_4015_a((EntityPlayer)entityliving, f);
    }

    public void doRenderLiving(EntityLiving entityliving, double d, double d1, double d2, 
            float f, float f1)
    {
        func_188_a((EntityPlayer)entityliving, d, d1, d2, f, f1);
    }

    public void doRender(Entity entity, double d, double d1, double d2, 
            float f, float f1)
    {
        func_188_a((EntityPlayer)entity, d, d1, d2, f, f1);
    }

    private ModelBiped field_209_f;
    private ModelBiped field_208_g;
    private ModelBiped field_207_h;
    private static final String armorFilenamePrefix[] = {
        "cloth", "chain", "iron", "diamond", "gold"
    };

}
