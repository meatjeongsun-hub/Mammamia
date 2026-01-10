package com.example.mammamia;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraAnimation;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.CircleOverlay;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.util.MarkerIcons;

public class MapViewerActivity extends AppCompatActivity implements OnMapReadyCallback {

    private double lat, lng;
    private String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_viewer);

        // 인텐트 데이터 수신
        lat = getIntent().getDoubleExtra("lat", 37.5665);
        lng = getIntent().getDoubleExtra("lng", 126.9780);
        time = getIntent().getStringExtra("time");

        TextView tvInfo = findViewById(R.id.tv_info_map);
        tvInfo.setText("감지 시간: " + time);

        // 지도 프래그먼트 연결
        MapFragment mapFragment = (MapFragment) getSupportFragmentManager().findFragmentById(R.id.map_fragment_viewer);
        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.map_fragment_viewer, mapFragment).commit();
        }
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        LatLng location = new LatLng(lat, lng);

        // 1. 카메라 이동
        CameraUpdate cameraUpdate = CameraUpdate.scrollTo(location)
                .animate(CameraAnimation.Fly);
        naverMap.moveCamera(cameraUpdate);

        // 2. 당시 위치 마커 표시
        Marker marker = new Marker();
        marker.setPosition(location);
        marker.setIcon(MarkerIcons.RED); // 붉은색 마커
        marker.setCaptionText("감지 위치");
        marker.setMap(naverMap);

        // 3. 시각적 강조를 위한 원 그리기
        CircleOverlay circle = new CircleOverlay();
        circle.setCenter(location);
        circle.setRadius(50); // 반경 50m
        circle.setColor(Color.argb(70, 255, 0, 0)); // 붉은색 반투명
        circle.setOutlineColor(Color.RED);
        circle.setMap(naverMap);
    }
}