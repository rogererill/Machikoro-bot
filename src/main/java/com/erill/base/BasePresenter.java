package com.erill.base;

/**
 * Created by Roger Erill on 11/4/17.
 */
public class BasePresenter<V> {

    private V view;

    public void bindView(V view) {
        this.view = view;
    }

    public void unbindView() {
        view = null;
    }

    public boolean isViewAttached() {
        return view != null;
    }

    public V getView() {
        checkViewAttached();
        return view;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new ViewNotAttachedException();
    }

    public static class ViewNotAttachedException extends RuntimeException {
        public ViewNotAttachedException() {
            super("Please call Presenter.bindView(view) before requesting data to the Presenter");
        }
    }
}
