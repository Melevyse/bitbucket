package com.melevyse.android1.homework1.converter;

import android.content.res.Resources;

import com.melevyse.android1.homework1.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RuNumberConverter extends AbstractNumberConverter {
    private String mMinus;
    private String mZero;

    private List<List<String>> mFirstDecade;
    private List<String> mSecondDecade;
    private List<String> mDecades;
    private List<String> mHundreds;
    private List<List<String>> mForms;
    // triadIndex -> gender (0 or 1)
    private int[] mFormsGenders;

    public RuNumberConverter(Resources res) {
        mMinus = res.getString(R.string.minus);
        mZero = res.getString(R.string.zero);

        mFirstDecade = new ArrayList<>(2);
        mFirstDecade.add(prepareNumberFormsArray(res, R.array.first_decade_female));
        mFirstDecade.add(prepareNumberFormsArray(res, R.array.first_decade_male));

        mSecondDecade = prepareNumberFormsArray(res, R.array.second_decade);
        mDecades = prepareNumberFormsArray(res, R.array.decades);
        mHundreds = prepareNumberFormsArray(res, R.array.hundreds);

        mForms = new ArrayList<>(4);
        mForms.add(Arrays.asList("", "", ""));
        mForms.add(Arrays.asList(res.getStringArray(R.array.thousands_forms)));
        mForms.add(Arrays.asList(res.getStringArray(R.array.millions_forms)));
        mForms.add(Arrays.asList(res.getStringArray(R.array.billions_forms)));

        mFormsGenders = new int[] {1, 0, 1, 1};
    }

    private List<String> prepareNumberFormsArray(Resources res, int arrayResId) {
        String[] items = res.getStringArray(arrayResId);
        List<String> result = new ArrayList<>(items.length + 1);
        result.add("");
        Collections.addAll(result, items);
        return result;
    }

    @Override
    protected String getMinusStringForm() {
        return mMinus;
    }

    @Override
    protected String getZeroStringForm() {
        return mZero;
    }

    @Override
    protected String getFirstDecadeStringForm(int index, int gender) {
        return mFirstDecade.get(gender).get(index);
    }

    @Override
    protected String getSecondDecadeStringForm(int index) {
        return mSecondDecade.get(index);
    }

    @Override
    protected String getDecadesStringForm(int index) {
        return mDecades.get(index);
    }

    @Override
    protected String getHundredsStringForm(int index) {
        return mHundreds.get(index);
    }

    @Override
    protected int getTriadGender(int triadIndex) {
        return mFormsGenders[triadIndex];
    }

    @Override
    protected String getTriadName(int triadIndex, int triadValue) {
        if (triadValue % 100 > 4 && triadValue % 100 < 21) {
            return mForms.get(triadIndex).get(2);
        }

        switch (triadValue % 10) {
            case 1:
                return mForms.get(triadIndex).get(0);
            case 2:
            case 3:
            case 4:
                return mForms.get(triadIndex).get(1);
            default:
                return mForms.get(triadIndex).get(2);
        }
    }
}