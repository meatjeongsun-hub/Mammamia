package com.example.mammamia;

import android.content.Context;
import android.location.Location;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

public class SafetyLogic {

    private Context context;
    private MediaPlayer mediaPlayer;

    public SafetyLogic(Context context) {
        this.context = context;
    }

    // [기능 1] 강제 알림 실행 (무음 모드 뚫고 소리 울리기)
    public void startForcedAlarm() {
        try {
            AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);

            // 1. 현재 볼륨을 강제로 최대치로 설정 (알람 채널)
            if (audioManager != null) {
                int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_ALARM);
                audioManager.setStreamVolume(AudioManager.STREAM_ALARM, maxVolume, 0);
            }

            // 2. 알람 소리 파일 준비
            Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
            if (alarmUri == null) {
                alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            }

            // 3. 미디어 플레이어 재생
            if (mediaPlayer != null) {
                mediaPlayer.release();
            }
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(context, alarmUri);

            // 오디오 속성 설정: 알람용(Usage Alarm)
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            mediaPlayer.setAudioAttributes(audioAttributes);

            mediaPlayer.prepare();
            mediaPlayer.start();

            Toast.makeText(context, "🚨 강제 알림 발동! 🚨", Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "알림 오류 발생", Toast.LENGTH_SHORT).show();
        }
    }

    // [기능 1-2] 강제 알림 중지
    public void stopForcedAlarm() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    // [기능 2] 엇갈림 방지: 두 사람의 중간 지점 좌표 계산
    public LatLng getMeetingPoint(double parentLat, double parentLon, double childLat, double childLon) {
        double midLat = (parentLat + childLat) / 2;
        double midLon = (parentLon + childLon) / 2;
        return new LatLng(midLat, midLon);
    }

    // [기능 3] 위험지역 감지 및 떨어짐 방지: 거리 계산 (미터 단위)
    public float getDistanceInMeters(double lat1, double lon1, double lat2, double lon2) {
        float[] results = new float[1];
        Location.distanceBetween(lat1, lon1, lat2, lon2, results);
        return results[0]; // 거리(m) 반환
    }

    // 특정 위치(dangerLat/Lon) 반경(radius) 안에 들어왔는지 확인
    public boolean checkDangerZone(double myLat, double myLon, double dangerLat, double dangerLon, float radiusMeters) {
        float distance = getDistanceInMeters(myLat, myLon, dangerLat, dangerLon);
        return distance <= radiusMeters;
    }
}