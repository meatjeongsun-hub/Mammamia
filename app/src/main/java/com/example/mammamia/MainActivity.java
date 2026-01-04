package com.example.mammamia;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends AppCompatActivity {

    // 로직 클래스 선언
    private SafetyLogic safetyLogic;

    // 예시 데이터 (테스트용 좌표: 서울시청 & 광화문)
    double parentLat = 37.5665, parentLon = 126.9780;
    double childLat = 37.5704, childLon = 126.9768;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // 본인의 xml 파일 이름이 activity_main이라고 가정

        // 1. SafetyLogic 초기화
        safetyLogic = new SafetyLogic(this);

        // ========================================================
        // 버튼 연결 (XML에 있는 버튼 ID와 맞춰야 합니다)
        // 만약 ID가 다르다면 R.id.xxx 부분을 수정해주세요.
        // ========================================================

        // [버튼 1] 강제 알림 (아이 폰 울리기)
        Button btnForceAlarm = findViewById(R.id.btn_force_alarm); // XML에 이 ID의 버튼이 있어야 함
        if (btnForceAlarm != null) {
            btnForceAlarm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    safetyLogic.startForcedAlarm();
                }
            });
        }

        // [버튼 2] 알림 끄기
        Button btnStopAlarm = findViewById(R.id.btn_stop_alarm);
        if (btnStopAlarm != null) {
            btnStopAlarm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    safetyLogic.stopForcedAlarm();
                    Toast.makeText(MainActivity.this, "알림을 껐습니다.", Toast.LENGTH_SHORT).show();
                }
            });
        }

        // [버튼 3] 중간 지점 확인 (엇갈림 방지)
        Button btnCheckPath = findViewById(R.id.btn_check_path);
        if (btnCheckPath != null) {
            btnCheckPath.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LatLng meetingPoint = safetyLogic.getMeetingPoint(parentLat, parentLon, childLat, childLon);
                    Toast.makeText(MainActivity.this,
                            "중간 만남 지점: " + meetingPoint.latitude + ", " + meetingPoint.longitude,
                            Toast.LENGTH_LONG).show();
                }
            });
        }

        // [버튼 4] 위험지역/떨어짐 확인 테스트
        Button btnDangerCheck = findViewById(R.id.btn_danger_check);
        if (btnDangerCheck != null) {
            btnDangerCheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 예: 아이가 부모로부터 100m 이상 떨어졌는지 확인
                    float distance = safetyLogic.getDistanceInMeters(parentLat, parentLon, childLat, childLon);

                    if (distance > 100) {
                        Toast.makeText(MainActivity.this, "⚠️ 주의: 거리가 " + (int)distance + "m 멀어졌습니다!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this, "안전 거리 유지 중 (" + (int)distance + "m)", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}