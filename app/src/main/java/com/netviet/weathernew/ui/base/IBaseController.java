package com.netviet.weathernew.ui.base;

public interface IBaseController<V extends IBaseView> {

    void attachView(V view);


    void detachView(V view);


    void destroy();

}
