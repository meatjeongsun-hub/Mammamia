package com.example.mammamia;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.PathOverlay;
import com.naver.maps.map.util.MarkerIcons;

import java.util.Arrays;

public class DirectionActivity extends AppCompatActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direction);

        MapFragment mapFragment = (MapFragment) getSupportFragmentManager().findFragmentById(R.id.map_fragment_direction);
        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.map_fragment_direction, mapFragment).commit();
        }
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        // 임의의 경로 좌표 설정 (서울 시청 -> 광화문)
        LatLng start = new LatLng(37.5663, 126.9779);
        LatLng middle = new LatLng(37.5680, 126.9785);
        LatLng end = new LatLng(37.5704, 126.9769);

        // 1. 카메라 이동
        naverMap.moveCamera(CameraUpdate.scrollTo(middle).zoom(15));

        // 2. 경로선 그리기 (PathOverlay)
        PathOverlay path = new PathOverlay();
        path.setCoords(Arrays.asList(start, middle, end));
        path.setWidth(30); // 경로선 두께
        path.setColor(Color.parseColor("#2196F3")); // 경로선 색상 (파랑)
        path.setOutlineWidth(0);
        // 화살표 패턴 적용 (네이버 기본 패턴 사용)
        path.setPatternImage(PathOverlay.ARROW_PATTERN);
        path.setPatternInterval(50); // 패턴 간격
        path.setMap(naverMap);

        // 3. 출발지 마커 (아이)
        Marker startMarker = new Marker();
        startMarker.setPosition(start);
        startMarker.setIcon(MarkerIcons.BLUE);
        startMarker.setCaptionText("아이");
        startMarker.setMap(naverMap);

        // 4. 도착지 마커 (부모/목적지)
        Marker endMarker = new Marker();
        endMarker.setPosition(end);
        endMarker.setIcon(MarkerIcons.RED);
        endMarker.setCaptionText("목적지");
        endMarker.setMap(naverMap);
    }
}