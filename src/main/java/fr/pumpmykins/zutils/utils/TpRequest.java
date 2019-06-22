package fr.pumpmykins.zutils.utils;

import net.minecraft.util.math.BlockPos;

public class TpRequest {

    boolean request;
    BlockPos p;
    long expiration;


    public TpRequest(){

        request = false;
        p = null;
        expiration = 0;

    }

    public TpRequest(boolean pRequest, BlockPos pReceiver, long pExpiration){

        request = pRequest;
        p = pReceiver;
        expiration = pExpiration;

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

    public long getExpiration(){

        return expiration;

    }

    public void setExpiration(long pExpiration){

        expiration = pExpiration;
    }


}
