package com.wilson.views.adapters;


import com.wilson.models.MetOfficeData;

public interface ItemClickListener {
    void itemClicked(MetOfficeData item);
    void itemClicked(Section section);
}
