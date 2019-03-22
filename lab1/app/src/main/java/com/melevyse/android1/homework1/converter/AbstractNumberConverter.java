package com.melevyse.android1.homework1.converter;

public abstract class AbstractNumberConverter {
    protected abstract String getMinusStringForm();
    protected abstract String getZeroStringForm();
    protected abstract String getFirstDecadeStringForm(int index, int gender);
    protected abstract String getSecondDecadeStringForm(int index);
    protected abstract String getDecadesStringForm(int index);
    protected abstract String getHundredsStringForm(int index);
    protected abstract String getTriadName(int triadIndex, int triadValue);
    protected abstract int getTriadGender(int triadIndex);

    private StringBuilder mStringBuilder;

    public AbstractNumberConverter() {
        mStringBuilder = new StringBuilder();
    }

    protected String convertTriad(int number, int gender) {
        mStringBuilder.setLength(0);
        if (number % 1000 > 99) {
            mStringBuilder.append(getHundredsStringForm(number % 1000 / 100)).append(" ");
        }
        if (number % 100 > 10 && number % 100 < 20) {
            mStringBuilder.append(getSecondDecadeStringForm(number % 10)).append(" ");
            return mStringBuilder.toString();
        }
        if (number % 100 > 9) {
            mStringBuilder.append(getDecadesStringForm(number % 100 / 10)).append(" ");
        }
        if (mStringBuilder.length() == 0 || number % 10 > 0) {
            mStringBuilder.append(getFirstDecadeStringForm(number % 10, gender)).append(" ");
        }
        return mStringBuilder.toString();
    }

    public String convert(int number) {
        if (number == 0) {
            return getZeroStringForm();
        }

        String res = "";
        boolean isNegative = false;
        if (number < 0) {
            isNegative = true;
            number = -number;
        }

        int index = 0;
        String triad;
        while (number > 0) {
            triad = convertTriad(number % 1000, getTriadGender(index));
            res = triad + getTriadName(index, number % 1000) + " " + res;
            number = number / 1000;
            index++;
        }

        if (isNegative) {
            return (getMinusStringForm() + " " + res).trim();
        }

        return res.trim();
    }
}