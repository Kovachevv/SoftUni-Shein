package com.example.shein.service;

import com.example.shein.model.view.StatsView;

public interface StatsService {

    void brandRequests();
    void clothingRequests();
    void accessoryRequests();
    void shoesRequests();
    void albumRequests();
    void artistRequests();
    StatsView getRequests();
}
