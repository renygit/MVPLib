package com.reny.mvpdemo.entity.event;

/**
 * Created by reny on 2017/3/8.
 *
 * 给RecyclerView发送滚动事件
 */

public class RvScrollEvent {

    private int tabIndex;
    private int pos;

    public RvScrollEvent(int tabIndex, int pos){
        this.tabIndex = tabIndex;
        this.pos = pos;
    }

    public int getTabIndex() {
        return tabIndex;
    }

    public int getPos() {
        return pos;
    }
}
