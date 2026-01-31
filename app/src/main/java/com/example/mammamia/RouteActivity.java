package com.example.mammamia;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;

public class RouteActivity extends AppCompatActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);

        MapFragment mapFragment = (MapFragment) getSupportFragmentManager().findFragmentById(R.id.map_fragment);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        // 지도 설정
        naverMap.setMapType(NaverMap.MapType.Navi);
        naverMap.setLayerGroupEnabled(NaverMap.LAYER_GROUP_BUILDING, true);
        naverMap.setLayerGroupEnabled(NaverMap.LAYER_GROUP_TRAFFIC, true);
    }
}