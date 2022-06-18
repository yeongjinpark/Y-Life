package com.pyj.ylife.view.menu.phone;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.pyj.ylife.R;

import java.util.Random;

public class music extends YouTubeBaseActivity {

    //객체 선언
    YouTubePlayerView playerView;
    YouTubePlayer player;

    Random random = new Random();
    int randomValue = random.nextInt(9);


    //유튜브 API KEY와 동영상 ID 변수 설정
    private static String API_KEY = "AIzaSyBkpVVo00dSdNvH1Ie1lOrBSuAxQ13q46A";
    //https://www.youtube.com/watch?v=hl-ii7W4ITg ▶ 유튜브 동영상 v= 다음 부분이 videoId
    String[] videoId={"OeE3RhlYlVI","u1i3OYvskRk", "LSRB-mpHUl0", "Bwump6p5Uwc", "egSauHrIQGk","W4JYfUcDCMU","3oPI1ftscOA"
    ,"de9-lhps7Lc","vawAXQQlip4","IVzwiH1sQ4I"};
    //private static String videoId = "OeE3RhlYlVI";
    //logcat 사용 설정
    private static final String TAG = "music";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kioskvideo);

        initPlayer();

        Button btnPlay = findViewById(R.id.youtubeBtn);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playVideo();
            }
        });
    }

    private void playVideo() {
        if(player != null) {
            if(player.isPlaying()) {
                player.pause();
            }
            player.loadVideo(videoId[randomValue]);
        }
    }

    //유튜브 플레이어 메서드
    private void initPlayer() {
        playerView = findViewById(R.id.youTubePlayerView);
        playerView.initialize(API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                player = youTubePlayer;

                player.setPlayerStateChangeListener(new YouTubePlayer.PlayerStateChangeListener() {
                    @Override
                    public void onLoading() {
                    }

                    @Override
                    public void onLoaded(String id) {
                        Log.d(TAG, "onLoaded: " + id);
                        player.play();
                    }

                    @Override
                    public void onAdStarted() {
                    }

                    @Override
                    public void onVideoStarted() {
                    }

                    @Override
                    public void onVideoEnded() {
                    }

                    @Override
                    public void onError(YouTubePlayer.ErrorReason errorReason) {
                        Log.d(TAG, "onError: " + errorReason);
                    }
                });
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
            }
        });
    }


}