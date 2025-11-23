package com.example.mammamia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen; // import 추가

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.mammamia.databinding.ActivityLoginBinding; // 패키지명 확인

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // !! 중요 !! : setContentView() 보다 반드시 먼저 호출되어야 합니다.
        SplashScreen.installSplashScreen(this);

        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // ... 기존 로그인 버튼 리스너 등 나머지 코드는 동일 ...
        binding.btnLogin.setOnClickListener(v -> {
            // ...
        });
    }
}