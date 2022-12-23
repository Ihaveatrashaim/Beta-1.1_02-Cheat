package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.io.*;
import java.net.*;
import java.util.Random;

import net.minecraft.cheat.Client;
import net.minecraft.cheat.impl.combat.Velocity;
import net.minecraft.cheat.impl.exploit.NoKick;
import net.minecraft.client.Minecraft;

public class NetClientHandler extends NetHandler
{

    public NetClientHandler(Minecraft minecraft, String s, int i) throws UnknownHostException, IOException
    {
        disconnected = false;
        field_1210_g = false;
        rand = new Random();
        mc = minecraft;
        Socket socket = new Socket(InetAddress.getByName(s), i);
        netManager = new NetworkManager(socket, "Client", this);
    }

    public void processReadPackets()
    {
        if(disconnected)
        {
            return;
        } else
        {
            netManager.processReadPackets();
            return;
        }
    }

    public void handleLogin(Packet1Login packet1login)
    {
        mc.playerController = new PlayerControllerMP(mc, this);
        worldClient = new WorldClient(this, packet1login.field_4074_d, packet1login.field_4073_e);
        worldClient.multiplayerWorld = true;
        mc.func_6261_a(worldClient);
        mc.displayGuiScreen(new GuiDownloadTerrain(this));
        mc.thePlayer.field_620_ab = packet1login.protocolVersion;
    }

    public void handlePickupSpawn(Packet21PickupSpawn packet21pickupspawn)
    {
        double d = (double)packet21pickupspawn.xPosition / 32D;
        double d1 = (double)packet21pickupspawn.yPosition / 32D;
        double d2 = (double)packet21pickupspawn.zPosition / 32D;
        EntityItem entityitem = new EntityItem(worldClient, d, d1, d2, new ItemStack(packet21pickupspawn.itemId, packet21pickupspawn.count));
        entityitem.motionX = (double)packet21pickupspawn.rotation / 128D;
        entityitem.motionY = (double)packet21pickupspawn.pitch / 128D;
        entityitem.motionZ = (double)packet21pickupspawn.roll / 128D;
        entityitem.serverPosX = packet21pickupspawn.xPosition;
        entityitem.serverPosY = packet21pickupspawn.yPosition;
        entityitem.serverPosZ = packet21pickupspawn.zPosition;
        worldClient.func_712_a(packet21pickupspawn.entityId, entityitem);
    }

    public void handleVehicleSpawn(Packet23VehicleSpawn packet23vehiclespawn)
    {
        double d = (double)packet23vehiclespawn.xPosition / 32D;
        double d1 = (double)packet23vehiclespawn.yPosition / 32D;
        double d2 = (double)packet23vehiclespawn.zPosition / 32D;
        Entity obj = null;
        if(packet23vehiclespawn.type == 10)
        {
            obj = new EntityMinecart(worldClient, d, d1, d2, 0);
        }
        if(packet23vehiclespawn.type == 11)
        {
            obj = new EntityMinecart(worldClient, d, d1, d2, 1);
        }
        if(packet23vehiclespawn.type == 12)
        {
            obj = new EntityMinecart(worldClient, d, d1, d2, 2);
        }
        if(packet23vehiclespawn.type == 90)
        {
            obj = new EntityFish(worldClient, d, d1, d2);
        }
        if(packet23vehiclespawn.type == 60)
        {
            obj = new EntityArrow(worldClient, d, d1, d2);
        }
        if(packet23vehiclespawn.type == 61)
        {
            obj = new EntitySnowball(worldClient, d, d1, d2);
        }
        if(packet23vehiclespawn.type == 62)
        {
            obj = new EntityEgg(worldClient, d, d1, d2);
        }
        if(packet23vehiclespawn.type == 1)
        {
            obj = new EntityBoat(worldClient, d, d1, d2);
        }
        if(packet23vehiclespawn.type == 50)
        {
            obj = new EntityTNTPrimed(worldClient, d, d1, d2);
        }
        if(packet23vehiclespawn.type == 70)
        {
            obj = new EntityFallingSand(worldClient, d, d1, d2, Block.sand.blockID);
        }
        if(packet23vehiclespawn.type == 71)
        {
            obj = new EntityFallingSand(worldClient, d, d1, d2, Block.gravel.blockID);
        }
        if(obj != null)
        {
            obj.serverPosX = packet23vehiclespawn.xPosition;
            obj.serverPosY = packet23vehiclespawn.yPosition;
            obj.serverPosZ = packet23vehiclespawn.zPosition;
            obj.rotationYaw = 0.0F;
            obj.rotationPitch = 0.0F;
            obj.field_620_ab = packet23vehiclespawn.entityId;
            worldClient.func_712_a(packet23vehiclespawn.entityId, ((Entity) (obj)));
        }
    }

    public void func_6498_a(Packet28 packet28)
    {
    	if(Client.i.cheatList.getCheat(Velocity.class).isToggled()) return;
    	
        Entity entity = func_12246_a(packet28.field_6367_a);
        if(entity == null)
        {
            return;
        } else
        {
            entity.setVelocity((double)packet28.field_6366_b / 8000D, (double)packet28.field_6369_c / 8000D, (double)packet28.field_6368_d / 8000D);
            return;
        }
    }

    public void handleNamedEntitySpawn(Packet20NamedEntitySpawn packet20namedentityspawn)
    {
        double d = (double)packet20namedentityspawn.xPosition / 32D;
        double d1 = (double)packet20namedentityspawn.yPosition / 32D;
        double d2 = (double)packet20namedentityspawn.zPosition / 32D;
        float f = (float)(packet20namedentityspawn.rotation * 360) / 256F;
        float f1 = (float)(packet20namedentityspawn.pitch * 360) / 256F;
        EntityOtherPlayerMP entityotherplayermp = new EntityOtherPlayerMP(mc.theWorld, packet20namedentityspawn.name);
        entityotherplayermp.serverPosX = packet20namedentityspawn.xPosition;
        entityotherplayermp.serverPosY = packet20namedentityspawn.yPosition;
        entityotherplayermp.serverPosZ = packet20namedentityspawn.zPosition;
        int i = packet20namedentityspawn.currentItem;
        if(i == 0)
        {
            entityotherplayermp.inventory.mainInventory[entityotherplayermp.inventory.currentItem] = null;
        } else
        {
            entityotherplayermp.inventory.mainInventory[entityotherplayermp.inventory.currentItem] = new ItemStack(i);
        }
        entityotherplayermp.setPositionAndRotation(d, d1, d2, f, f1);
        worldClient.func_712_a(packet20namedentityspawn.entityId, entityotherplayermp);
    }

    public void handleEntityTeleport(Packet34EntityTeleport packet34entityteleport)
    {
        Entity entity = func_12246_a(packet34entityteleport.entityId);
        if(entity == null)
        {
            return;
        } else
        {
            entity.serverPosX = packet34entityteleport.xPosition;
            entity.serverPosY = packet34entityteleport.yPosition;
            entity.serverPosZ = packet34entityteleport.zPosition;
            double d = (double)entity.serverPosX / 32D;
            double d1 = (double)entity.serverPosY / 32D + 0.015625D;
            double d2 = (double)entity.serverPosZ / 32D;
            float f = (float)(packet34entityteleport.yaw * 360) / 256F;
            float f1 = (float)(packet34entityteleport.pitch * 360) / 256F;
            entity.setPositionAndRotation2(d, d1, d2, f, f1, 3);
            return;
        }
    }

    public void handleEntity(Packet30Entity packet30entity)
    {
        Entity entity = func_12246_a(packet30entity.entityId);
        if(entity == null)
        {
            return;
        } else
        {
            entity.serverPosX += packet30entity.xPosition;
            entity.serverPosY += packet30entity.yPosition;
            entity.serverPosZ += packet30entity.zPosition;
            double d = (double)entity.serverPosX / 32D;
            double d1 = (double)entity.serverPosY / 32D + 0.015625D;
            double d2 = (double)entity.serverPosZ / 32D;
            float f = packet30entity.rotating ? (float)(packet30entity.yaw * 360) / 256F : entity.rotationYaw;
            float f1 = packet30entity.rotating ? (float)(packet30entity.pitch * 360) / 256F : entity.rotationPitch;
            entity.setPositionAndRotation2(d, d1, d2, f, f1, 3);
            return;
        }
    }

    public void handleDestroyEntity(Packet29DestroyEntity packet29destroyentity)
    {
        worldClient.removeEntityFromWorld(packet29destroyentity.entityId);
    }

    public void handleFlying(Packet10Flying packet10flying)
    {
        EntityPlayerSP entityplayersp = mc.thePlayer;
        double d = ((EntityPlayer) (entityplayersp)).posX;
        double d1 = ((EntityPlayer) (entityplayersp)).posY;
        double d2 = ((EntityPlayer) (entityplayersp)).posZ;
        float f = ((EntityPlayer) (entityplayersp)).rotationYaw;
        float f1 = ((EntityPlayer) (entityplayersp)).rotationPitch;
        if(packet10flying.moving)
        {
            d = packet10flying.xPosition;
            d1 = packet10flying.yPosition;
            d2 = packet10flying.zPosition;
        }
        if(packet10flying.rotating)
        {
            f = packet10flying.yaw;
            f1 = packet10flying.pitch;
        }
        entityplayersp.field_9287_aY = 0.0F;
        entityplayersp.motionX = entityplayersp.motionY = entityplayersp.motionZ = 0.0D;
        entityplayersp.setPositionAndRotation(d, d1, d2, f, f1);
        packet10flying.xPosition = ((EntityPlayer) (entityplayersp)).posX;
        packet10flying.yPosition = ((EntityPlayer) (entityplayersp)).boundingBox.minY;
        packet10flying.zPosition = ((EntityPlayer) (entityplayersp)).posZ;
        packet10flying.stance = ((EntityPlayer) (entityplayersp)).posY;
        netManager.addToSendQueue(packet10flying);
        if(!field_1210_g)
        {
            mc.thePlayer.prevPosX = mc.thePlayer.posX;
            mc.thePlayer.prevPosY = mc.thePlayer.posY;
            mc.thePlayer.prevPosZ = mc.thePlayer.posZ;
            field_1210_g = true;
            mc.displayGuiScreen(null);
        }
    }

    public void handlePreChunk(Packet50PreChunk packet50prechunk)
    {
        worldClient.func_713_a(packet50prechunk.xPosition, packet50prechunk.yPosition, packet50prechunk.mode);
    }

    public void handleMultiBlockChange(Packet52MultiBlockChange packet52multiblockchange)
    {
        Chunk chunk = worldClient.getChunkFromChunkCoords(packet52multiblockchange.xPosition, packet52multiblockchange.zPosition);
        int i = packet52multiblockchange.xPosition * 16;
        int j = packet52multiblockchange.zPosition * 16;
        for(int k = 0; k < packet52multiblockchange.size; k++)
        {
            short word0 = packet52multiblockchange.coordinateArray[k];
            int l = packet52multiblockchange.typeArray[k] & 0xff;
            byte byte0 = packet52multiblockchange.metadataArray[k];
            int i1 = word0 >> 12 & 0xf;
            int j1 = word0 >> 8 & 0xf;
            int k1 = word0 & 0xff;
            chunk.setBlockIDWithMetadata(i1, k1, j1, l, byte0);
            worldClient.func_711_c(i1 + i, k1, j1 + j, i1 + i, k1, j1 + j);
            worldClient.func_701_b(i1 + i, k1, j1 + j, i1 + i, k1, j1 + j);
        }

    }

    public void handleMapChunk(Packet51MapChunk packet51mapchunk)
    {
        worldClient.func_711_c(packet51mapchunk.xPosition, packet51mapchunk.yPosition, packet51mapchunk.zPosition, (packet51mapchunk.xPosition + packet51mapchunk.xSize) - 1, (packet51mapchunk.yPosition + packet51mapchunk.ySize) - 1, (packet51mapchunk.zPosition + packet51mapchunk.zSize) - 1);
        worldClient.func_693_a(packet51mapchunk.xPosition, packet51mapchunk.yPosition, packet51mapchunk.zPosition, packet51mapchunk.xSize, packet51mapchunk.ySize, packet51mapchunk.zSize, packet51mapchunk.chunk);
    }

    public void handleBlockChange(Packet53BlockChange packet53blockchange)
    {
        worldClient.func_714_c(packet53blockchange.xPosition, packet53blockchange.yPosition, packet53blockchange.zPosition, packet53blockchange.type, packet53blockchange.metadata);
    }

    public void handleKickDisconnect(Packet255KickDisconnect packet255kickdisconnect)
    {
    	if(Client.i.cheatList.getCheat(NoKick.class).isToggled()) {
    		return;
    	}
    	
        netManager.networkShutdown("disconnect.kicked", new Object[0]);
        disconnected = true;
        mc.func_6261_a(null);
        mc.displayGuiScreen(new GuiConnectFailed("disconnect.disconnected", "disconnect.genericReason", new Object[] {
            packet255kickdisconnect.reason
        }));
    }

    public void handleErrorMessage(String s, Object aobj[])
    {
        if(disconnected)
        {
            return;
        } else
        {
            disconnected = true;
            mc.func_6261_a(null);
            mc.displayGuiScreen(new GuiConnectFailed("disconnect.lost", s, aobj));
            return;
        }
    }

    public void addToSendQueue(Packet packet)
    {
        if(disconnected)
        {
            return;
        } else
        {
            netManager.addToSendQueue(packet);
            return;
        }
    }

    public void handleCollect(Packet22Collect packet22collect)
    {
        Entity entity = func_12246_a(packet22collect.collectedEntityId);
        Object obj = (EntityLiving)func_12246_a(packet22collect.collectorEntityId);
        if(obj == null)
        {
            obj = mc.thePlayer;
        }
        if(entity != null)
        {
            worldClient.playSoundAtEntity(entity, "random.pop", 0.2F, ((rand.nextFloat() - rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
            mc.effectRenderer.func_1192_a(new EntityPickupFX(mc.theWorld, entity, ((Entity) (obj)), -0.5F));
            worldClient.removeEntityFromWorld(packet22collect.collectedEntityId);
        }
    }

    public void handleChat(Packet3Chat packet3chat)
    {
        mc.ingameGUI.addChatMessage(packet3chat.message);
    }

    public void handleArmAnimation(Packet18ArmAnimation packet18armanimation)
    {
        Entity entity = func_12246_a(packet18armanimation.entityId);
        if(entity == null)
        {
            return;
        }
        if(packet18armanimation.animate == 1)
        {
            EntityPlayer entityplayer = (EntityPlayer)entity;
            entityplayer.swingItem();
        } else
        if(packet18armanimation.animate == 100)
        {
            entity.field_9300_bu = true;
        } else
        if(packet18armanimation.animate == 101)
        {
            entity.field_9300_bu = false;
        } else
        if(packet18armanimation.animate == 102)
        {
            entity.field_9299_bv = true;
        } else
        if(packet18armanimation.animate == 103)
        {
            entity.field_9299_bv = false;
        } else
        if(packet18armanimation.animate == 104)
        {
            entity.field_12240_bw = true;
        } else
        if(packet18armanimation.animate == 105)
        {
            entity.field_12240_bw = false;
        } else
        if(packet18armanimation.animate == 2)
        {
            entity.performHurtAnimation();
        }
    }

    public void handleHandshake(Packet2Handshake packet2handshake)
    {
        if(packet2handshake.username.equals("-"))
        {
            addToSendQueue(new Packet1Login(mc.session.playerName, "Password", 8));
        } else
        {
            try
            {
                URL url = new URL((new StringBuilder()).append("http://www.minecraft.net/game/joinserver.jsp?user=").append(mc.session.playerName).append("&sessionId=").append(mc.session.field_6543_c).append("&serverId=").append(packet2handshake.username).toString());
                BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(url.openStream()));
                String s = bufferedreader.readLine();
                bufferedreader.close();
                if(s.equalsIgnoreCase("ok"))
                {
                    addToSendQueue(new Packet1Login(mc.session.playerName, "Password", 8));
                } else
                {
                    netManager.networkShutdown("disconnect.loginFailedInfo", new Object[] {
                        s
                    });
                }
            }
            catch(Exception exception)
            {
                exception.printStackTrace();
                netManager.networkShutdown("disconnect.genericReason", new Object[] {
                    (new StringBuilder()).append("Internal client error: ").append(exception.toString()).toString()
                });
            }
        }
    }

    public void disconnect()
    {
        disconnected = true;
        netManager.networkShutdown("disconnect.closed", new Object[0]);
    }

    public void handleMobSpawn(Packet24MobSpawn packet24mobspawn)
    {
        double d = (double)packet24mobspawn.xPosition / 32D;
        double d1 = (double)packet24mobspawn.yPosition / 32D;
        double d2 = (double)packet24mobspawn.zPosition / 32D;
        float f = (float)(packet24mobspawn.yaw * 360) / 256F;
        float f1 = (float)(packet24mobspawn.pitch * 360) / 256F;
        EntityLiving entityliving = (EntityLiving)EntityList.createEntity(packet24mobspawn.type, mc.theWorld);
        entityliving.serverPosX = packet24mobspawn.xPosition;
        entityliving.serverPosY = packet24mobspawn.yPosition;
        entityliving.serverPosZ = packet24mobspawn.zPosition;
        entityliving.field_620_ab = packet24mobspawn.entityId;
        entityliving.setPositionAndRotation(d, d1, d2, f, f1);
        entityliving.field_9343_G = true;
        worldClient.func_712_a(packet24mobspawn.entityId, entityliving);
    }

    public void handleUpdateTime(Packet4UpdateTime packet4updatetime)
    {
        mc.theWorld.setWorldTime(packet4updatetime.time);
    }

    public void handleSpawnPosition(Packet6SpawnPosition packet6spawnposition)
    {
        worldClient.spawnX = packet6spawnposition.xPosition;
        worldClient.spawnY = packet6spawnposition.yPosition;
        worldClient.spawnZ = packet6spawnposition.zPosition;
    }

    public void func_6497_a(Packet39 packet39)
    {
        Object obj = func_12246_a(packet39.field_6365_a);
        Entity entity = func_12246_a(packet39.field_6364_b);
        if(packet39.field_6365_a == mc.thePlayer.field_620_ab)
        {
            obj = mc.thePlayer;
        }
        if(obj == null)
        {
            return;
        } else
        {
            ((Entity) (obj)).mountEntity(entity);
            return;
        }
    }

    public void func_9447_a(Packet38 packet38)
    {
        Entity entity = func_12246_a(packet38.field_9274_a);
        if(entity != null)
        {
            entity.func_9282_a(packet38.field_9273_b);
        }
    }

    private Entity func_12246_a(int i)
    {
        if(i == mc.thePlayer.field_620_ab)
        {
            return mc.thePlayer;
        } else
        {
            return worldClient.func_709_b(i);
        }
    }

    public void handleHealth(Packet8 packet8)
    {
        mc.thePlayer.setHealth(packet8.healthMP);
    }

    public void func_9448_a(Packet9 packet9)
    {
        mc.respawn();
    }

    public void func_12245_a(Packet60 packet60)
    {
        Explosion explosion = new Explosion(mc.theWorld, null, packet60.field_12236_a, packet60.field_12235_b, packet60.field_12239_c, packet60.field_12238_d);
        explosion.field_12251_g = packet60.field_12237_e;
        explosion.func_12247_b();
    }

    public void func_20087_a(Packet100 packet100)
    {
        if(packet100.field_20037_b == 0)
        {
            InventoryBasic inventorybasic = new InventoryBasic(packet100.field_20040_c, packet100.field_20039_d);
            mc.thePlayer.displayGUIChest(inventorybasic);
            mc.thePlayer.field_20068_h.unusedList = packet100.field_20038_a;
        } else
        if(packet100.field_20037_b == 2)
        {
            TileEntityFurnace tileentityfurnace = new TileEntityFurnace();
            mc.thePlayer.displayGUIFurnace(tileentityfurnace);
            mc.thePlayer.field_20068_h.unusedList = packet100.field_20038_a;
        } else
        if(packet100.field_20037_b == 1)
        {
            EntityPlayerSP entityplayersp = mc.thePlayer;
            mc.thePlayer.displayWorkbenchGUI(MathHelper.floor_double(((EntityPlayer) (entityplayersp)).posX), MathHelper.floor_double(((EntityPlayer) (entityplayersp)).posY), MathHelper.floor_double(((EntityPlayer) (entityplayersp)).posZ));
            mc.thePlayer.field_20068_h.unusedList = packet100.field_20038_a;
        }
    }

    public void func_20088_a(Packet103 packet103)
    {
        if(packet103.field_20042_a == -1)
        {
            mc.thePlayer.inventory.func_20076_b(packet103.field_20043_c);
        } else
        if(packet103.field_20042_a == 0)
        {
            mc.thePlayer.field_20069_g.func_20119_a(packet103.field_20041_b, packet103.field_20043_c);
        } else
        if(packet103.field_20042_a == mc.thePlayer.field_20068_h.unusedList)
        {
            mc.thePlayer.field_20068_h.func_20119_a(packet103.field_20041_b, packet103.field_20043_c);
        }
    }

    public void func_20089_a(Packet106 packet106)
    {
        CraftingInventoryCB craftinginventorycb = null;
        if(packet106.field_20029_a == 0)
        {
            craftinginventorycb = mc.thePlayer.field_20069_g;
        } else
        if(packet106.field_20029_a == mc.thePlayer.field_20068_h.unusedList)
        {
            craftinginventorycb = mc.thePlayer.field_20068_h;
        }
        if(craftinginventorycb != null)
        {
            if(packet106.field_20030_c)
            {
                craftinginventorycb.func_20113_a(packet106.field_20028_b);
            } else
            {
                craftinginventorycb.func_20110_b(packet106.field_20028_b);
                addToSendQueue(new Packet106(packet106.field_20029_a, packet106.field_20028_b, true));
            }
        }
    }

    public void func_20094_a(Packet104 packet104)
    {
        if(packet104.field_20036_a == 0)
        {
            mc.thePlayer.field_20069_g.func_20115_a(packet104.field_20035_b);
        } else
        if(packet104.field_20036_a == mc.thePlayer.field_20068_h.unusedList)
        {
            mc.thePlayer.field_20068_h.func_20115_a(packet104.field_20035_b);
        }
    }

    public void func_20093_a(Packet130 packet130)
    {
        if(mc.theWorld.blockExists(packet130.field_20020_a, packet130.field_20019_b, packet130.field_20022_c))
        {
            TileEntity tileentity = mc.theWorld.getBlockTileEntity(packet130.field_20020_a, packet130.field_20019_b, packet130.field_20022_c);
            if(tileentity instanceof TileEntitySign)
            {
                TileEntitySign tileentitysign = (TileEntitySign)tileentity;
                for(int i = 0; i < 4; i++)
                {
                    tileentitysign.signText[i] = packet130.field_20021_d[i];
                }

                tileentitysign.onInventoryChanged();
            }
        }
    }

    public void func_20090_a(Packet105 packet105)
    {
        func_4114_b(packet105);
        if(mc.thePlayer.field_20068_h != null && mc.thePlayer.field_20068_h.unusedList == packet105.field_20032_a)
        {
            mc.thePlayer.field_20068_h.func_20112_a(packet105.field_20031_b, packet105.field_20033_c);
        }
    }

    public void handlePlayerInventory(Packet5PlayerInventory packet5playerinventory)
    {
        Entity entity = func_12246_a(packet5playerinventory.type);
        if(entity != null)
        {
            entity.func_20045_c(packet5playerinventory.stacks, packet5playerinventory.field_20044_c);
        }
    }

    public void func_20092_a(Packet101 packet101)
    {
        mc.thePlayer.func_20059_m();
    }

    private boolean disconnected;
    private NetworkManager netManager;
    public String field_1209_a;
    private Minecraft mc;
    private WorldClient worldClient;
    private boolean field_1210_g;
    Random rand;
}
