package com.ibt.niramaya.ui.activity.ambulance;

import com.google.android.gms.maps.model.LatLng;

import java.net.SocketOption;

public class Bearing {


    public static void main(String[] args){

        LatLng startLatlng = new LatLng(22.719922, 75.883309);
        LatLng endLatlng = new LatLng(22.719684, 75.883406);
        Bearing bearing = new Bearing();
        float berng = bearing.getBearing(startLatlng, endLatlng);

        System.out.println(berng);

    }

    private float getBearing(LatLng begin, LatLng end) {
        double lat = Math.abs(begin.latitude - end.latitude);
        double lng = Math.abs(begin.longitude - end.longitude);

        if (begin.latitude < end.latitude && begin.longitude < end.longitude)
            return (float) (Math.toDegrees(Math.atan(lng / lat)));
        else if (begin.latitude >= end.latitude && begin.longitude < end.longitude)
            return (float) ((90 - Math.toDegrees(Math.atan(lng / lat))) + 90);
        else if (begin.latitude >= end.latitude && begin.longitude >= end.longitude)
            return (float) (Math.toDegrees(Math.atan(lng / lat)) + 180);
        else if (begin.latitude < end.latitude && begin.longitude >= end.longitude)
            return (float) ((90 - Math.toDegrees(Math.atan(lng / lat))) + 270);
        return -1;
    }
}
