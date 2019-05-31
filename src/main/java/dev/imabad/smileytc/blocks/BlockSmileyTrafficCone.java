package dev.imabad.smileytc.blocks;

import dev.imabad.smileytc.SmileyTrafficCones;
import javax.annotation.Nullable;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing.Plane;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockSmileyTrafficCone extends BlockDirectional implements ITileEntityProvider {


    public static final PropertyDirection FACING = PropertyDirection.create("facing",
        Plane.HORIZONTAL);

    public BlockSmileyTrafficCone() {
        super(Material.ANVIL);
        setTranslationKey("trafficcone");
        setRegistryName("trafficcone");
        setCreativeTab(CreativeTabs.DECORATIONS);
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(FACING, EnumFacing.byIndex((meta & 3) + 2));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        if (state.getBlock() instanceof BlockDirectional) {
            return state.getValue(FACING).getIndex() - 2;
        }
        return 0;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state,
        EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY,
        float hitZ) {
        if (!worldIn.isRemote) {
            if (!playerIn.isSneaking()) {
                playerIn.openGui(SmileyTrafficCones.instance, 1, worldIn,
                    pos.getX(), pos.getY(), pos.getZ());
            }
        }
        return super
            .onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {

        world.setBlockState(pos,
            state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
        if(stack.hasDisplayName()){
            TileEntity tileEntity = world.getTileEntity(pos);
            if(tileEntity != null){
                ((TileSmileyTrafficCone)tileEntity).setName(stack.getDisplayName());
            }
        }
        super.onBlockPlacedBy(world, pos, state, placer, stack);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileSmileyTrafficCone();
    }
}
