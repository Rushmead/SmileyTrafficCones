package dev.imabad.smileytc.packets;

import dev.imabad.smileytc.SmileyPacketHandler;
import dev.imabad.smileytc.SmileyTrafficCones;
import dev.imabad.smileytc.blocks.TileSmileyTrafficCone;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class UpdateConePacket implements IMessage {

    public UpdateConePacket(){}

    private BlockPos pos;
    private String name;

    public UpdateConePacket(BlockPos pos, String name) {
        this.pos = pos;
        this.name = name;
    }

    public BlockPos getPos() {
        return pos;
    }

    public String getName() {
        return name;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        name = ByteBufUtils.readUTF8String(buf);
        int x = buf.readInt();
        int y = buf.readInt();
        int z = buf.readInt();
        pos = new BlockPos(x, y, z);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, name);
        buf.writeInt(pos.getX());
        buf.writeInt(pos.getY());
        buf.writeInt(pos.getZ());
    }

    public static class ServerHandler implements IMessageHandler<UpdateConePacket, IMessage> {

        @Override
        public IMessage onMessage(UpdateConePacket message, MessageContext ctx) {
            doTheFuckingThing(message, ctx);
            return null;
        }

        private void doTheFuckingThing(UpdateConePacket message, MessageContext ctx){
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> {
                World world = ctx.getServerHandler().player.world;
                BlockPos blockPos = message.getPos();
                TileSmileyTrafficCone tileSmileyTrafficCone = (TileSmileyTrafficCone) world
                    .getTileEntity(blockPos);
                tileSmileyTrafficCone.setName(message.getName());
                world.markChunkDirty(blockPos, tileSmileyTrafficCone);
                SmileyPacketHandler.INSTANCE.sendToAll(
                    new UpdateConePacket(tileSmileyTrafficCone.getPos(), message.getName()));
            });
        }
    }

    public static class ClientHandler implements IMessageHandler<UpdateConePacket, IMessage>{

        @Override
        public IMessage onMessage(UpdateConePacket message, MessageContext ctx) {
            doTheFuckingThing(message, ctx);
            return null;
        }

        private void doTheFuckingThing(UpdateConePacket message, MessageContext ctx){
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> {
                World world = SmileyTrafficCones.proxy.getWorld();
                BlockPos blockPos = message.getPos();
                TileSmileyTrafficCone tileSmileyTrafficCone = (TileSmileyTrafficCone) world.getTileEntity(blockPos);
                tileSmileyTrafficCone.setName(message.getName());
                world.markChunkDirty(blockPos, tileSmileyTrafficCone);
            });
        }
    }
}
