package com.example.explainme.presenter;


@SuppressWarnings({"UnusedParameters", "unused"})
public abstract class BasePresenter<View> {

    public View view;

    protected BasePresenter() {
    }

    public void attach(View view) {
        this.view = view;
    }

    public void detach() {
        this.view = null;
    }

    public boolean isViewAttached() {
        return this.view != null;
    }
}
