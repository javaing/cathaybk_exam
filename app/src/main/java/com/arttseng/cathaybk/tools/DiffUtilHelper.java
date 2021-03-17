package com.arttseng.cathaybk.tools;

import androidx.recyclerview.widget.DiffUtil;

import com.solar.beststar.modelnew.match.MatchDataNew;

import java.util.List;

public class DiffUtilHelper extends DiffUtil.Callback {

    private List<MatchDataNew> mOldData, mNewData;

    public DiffUtilHelper(List<MatchDataNew> mOldData, List<MatchDataNew> mNewData) {
        this.mOldData = mOldData;
        this.mNewData = mNewData;
    }

    //老数据集size
    @Override
    public int getOldListSize() {
        return mOldData != null ? mOldData.size() : 0;
    }

    //新数据集size
    @Override
    public int getNewListSize() {
        return mNewData != null ? mNewData.size() : 0;
    }


    @Override
    public boolean areItemsTheSame(int oldPos, int newPos) {
        return mOldData.get(oldPos).getId().equals(mNewData.get(newPos).getId());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        MatchDataNew oldInfo = mOldData.get(oldItemPosition);
        MatchDataNew newInfo = mNewData.get(newItemPosition);


        if (!oldInfo.getMatchStatus().equals(newInfo.getMatchStatus())) {
            return false;//如果有内容不同，就返回false
        }
        return true; //默认两个data内容是相同的
    }
}
