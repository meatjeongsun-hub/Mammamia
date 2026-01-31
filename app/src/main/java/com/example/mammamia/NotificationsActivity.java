package com.example.mammamia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NotificationActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AlertAdapter adapter;
    private List<AlertRecord> alertList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 1. 더미 데이터 생성 (실제 앱에서는 DB나 서버에서 가져옵니다)
        alertList = new ArrayList<>();
        // 서울시청 근처 좌표 예시
        alertList.add(new AlertRecord("12:12", "단거리 경로 이탈 감지", 37.5663, 126.9779));
        alertList.add(new AlertRecord("09:30", "위험지역(공사장) 접근", 37.5640, 126.9750));
        alertList.add(new AlertRecord("어제 18:00", "안심 귀가 경로 진입", 37.5670, 126.9780));

        // 2. 어댑터 연결
        adapter = new AlertAdapter(this, alertList);
        recyclerView.setAdapter(adapter);

        // 테스트용: 길찾기 화면으로 이동 버튼
        Button btnGoDirection = findViewById(R.id.btn_go_direction);
        btnGoDirection.setOnClickListener(v -> {
            Intent intent = new Intent(NotificationActivity.this, DirectionsActivity.class);
            startActivity(intent);
        });

        // 테스트용: 위험지역 화면으로 이동 버튼
        Button btnGoDanger = findViewById(R.id.btn_go_danger);
        btnGoDanger.setOnClickListener(v -> {
            Intent intent = new Intent(NotificationActivity.this, DangerZoneActivity.class);
            startActivity(intent);
        });
    }
}