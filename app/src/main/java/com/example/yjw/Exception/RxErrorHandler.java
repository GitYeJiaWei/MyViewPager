package com.example.yjw.Exception;

import android.content.Context;
import android.widget.Toast;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.HttpException;


public class RxErrorHandler
{


    private Context mContext;

    public RxErrorHandler(Context context)
    {

        this.mContext = context;
    }

    public BaseException handleError(Throwable e)
    {

        BaseException exception = new BaseException();

      if (e instanceof ConnectException)
        {
            //网络链接失败
            exception.setDisplayMessage(ErrorMessageFactory.create(mContext, 0x8));
        } else if (e instanceof SocketTimeoutException)
        {
            exception.setDisplayMessage(ErrorMessageFactory.create(mContext, 0x7));
        }else if (e instanceof ApiException)
        {
            //API异常
            exception.setDisplayMessage(((ApiException) e).getDisplayMessage());
        } else
        {
            //未知异常
            exception.setDisplayMessage(ErrorMessageFactory.create(mContext, 0x4));
        }

        return exception;
    }

    public void showErrorMessage(BaseException e)
    {
        Toast.makeText(mContext, e.getDisplayMessage(), Toast.LENGTH_LONG).show();
    }
}
