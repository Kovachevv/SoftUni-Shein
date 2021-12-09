package com.example.shein.service.impl;

import com.example.shein.model.view.StatsView;
import com.example.shein.service.StatsService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class StatsServiceImpl implements StatsService {

    private int brandRequests;
    private int clothingRequests;
    private int accessoryRequests;
    private int shoesRequests;
    private int albumRequests;
    private int artistRequests;


    @Override
    public void brandRequests() {

        Authentication authentication = SecurityContextHolder.
                getContext().
                getAuthentication();

        if (authentication != null && (authentication.getPrincipal() instanceof UserDetails)) {
            brandRequests++;
        }
    }


    @Override
    public void clothingRequests() {
        Authentication authentication = SecurityContextHolder.
                getContext().
                getAuthentication();

        if (authentication != null && (authentication.getPrincipal() instanceof UserDetails)) {
            clothingRequests++;
        }
    }
    @Override
    public void accessoryRequests() {

        Authentication authentication = SecurityContextHolder.
                getContext().
                getAuthentication();

        if (authentication != null && (authentication.getPrincipal() instanceof UserDetails)) {
            accessoryRequests++;
        }

    }

    @Override
    public void shoesRequests() {
        Authentication authentication = SecurityContextHolder.
                getContext().
                getAuthentication();

        if (authentication != null && (authentication.getPrincipal() instanceof UserDetails)) {
            shoesRequests++;
        }

    }

    @Override
    public void albumRequests() {
        Authentication authentication = SecurityContextHolder.
                getContext().
                getAuthentication();

        if (authentication != null && (authentication.getPrincipal() instanceof UserDetails)) {
            albumRequests++;
        }
    }

    @Override
    public void artistRequests() {
        Authentication authentication = SecurityContextHolder.
                getContext().
                getAuthentication();

        if (authentication != null && (authentication.getPrincipal() instanceof UserDetails)) {
            artistRequests++;
        }
    }


    @Override
    public StatsView getRequests() {
        return new StatsView(brandRequests, clothingRequests, accessoryRequests, shoesRequests, albumRequests, artistRequests);
    }
}
