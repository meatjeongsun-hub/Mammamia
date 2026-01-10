package com.example.mammamia;

public class AlertRecord {
    private String time;       // 발생 시간 (예: "12:12")
    private String message;    // 알림 내용 (예: "위험지역 접근")
    private double latitude;   // 당시 위도
    private double longitude;  // 당시 경도

    public AlertRecord(String time, String message, double latitude, double longitude) {
        this.time = time;
        this.message = message;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getTime() { return time; }
    public String getMessage() { return message; }
    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }
}