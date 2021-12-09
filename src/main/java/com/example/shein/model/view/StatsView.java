package com.example.shein.model.view;

public class StatsView {

    private final int brandRequests;
    private final int clothingRequests;
    private final int accessoryRequests;
    private final int shoesRequests;
    private final int albumRequests;
    private final int artistRequests;

    public StatsView(int brandRequests, int clothingRequests, int accessoryRequests, int shoesRequests, int albumRequests, int artistRequests) {
        this.brandRequests = brandRequests;
        this.clothingRequests = clothingRequests;
        this.accessoryRequests = accessoryRequests;
        this.shoesRequests = shoesRequests;
        this.albumRequests = albumRequests;
        this.artistRequests = artistRequests;
    }

    public int getBrandRequests() {
        return brandRequests;
    }

    public int getClothingRequests() {
        return clothingRequests;
    }

    public int getAccessoryRequests() {
        return accessoryRequests;
    }

    public int getShoesRequests() {
        return shoesRequests;
    }

    public int getAlbumRequests() {
        return albumRequests;
    }

    public int getArtistRequests() {
        return artistRequests;
    }
}
