package fr.pumpmykins.zutils.utils;

import net.minecraft.util.math.BlockPos;

public class TpRequest {

    boolean request;
    BlockPos p;

    public TpRequest(){

        request = false;
        p = null;

    }

    public TpRequest(boolean pRequest, BlockPos pReceiver){

        request = pRequest;
        p = pReceiver;
    }

    public BlockPos getP(){

        return  p;
    }

    public void setP(BlockPos pReceiver){

        p = pReceiver;
    }


    public boolean getPrequest(){

        return request;

    }

    public void setPrequest(boolean pRequest){

        request = pRequest;

    }
}
