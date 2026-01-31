package com.example.mammamia;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 버튼 찾기
        LinearLayout btnRoute = findViewById(R.id.btn_route);
        LinearLayout btnDanger = findViewById(R.id.btn_danger);
        Button btnAlert = findViewById(R.id.btn_alert);

        // 1. 길안내 클릭 -> 화면 이동
        btnRoute.setOnClickListener(v -> startActivity(new Intent(this, RouteActivity.class)));

        // 2. 위험지역 클릭 -> 화면 이동
        btnDanger.setOnClickListener(v -> startActivity(new Intent(this, DangerZoneActivity.class)));

        // 3. 알림 버튼 클릭 -> 메시지 띄우기
        btnAlert.setOnClickListener(v -> Toast.makeText(this, "보호자에게 위치 알림을 전송했습니다!", Toast.LENGTH_LONG).show());
    }
}