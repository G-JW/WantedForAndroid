package com.example.wanted;

import org.linphone.core.*;
import org.linphone.mediastream.video.capture.CaptureTextureView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.TextureView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class SipActivity extends AppCompatActivity {
    private Core core;
    private Button btnCall,btnAnswer,btnMute,btnSpeaker,btnVideo,btnCamera,btnHangUp;
    private TextureView textureView;
    private EditText remoteAddress;
    private TextView tv_rStatus,tv_cStatus;
    private CaptureTextureView captureTextureView;
    Bundle user_data;

    private CoreListener coreListener=new CoreListenerStub(){
        @Override
        public void onAccountRegistrationStateChanged(@NonNull Core core, @NonNull Account account, RegistrationState state, @NonNull String message) {
            tv_rStatus.setText(message);
            if (state == RegistrationState.Failed || state == RegistrationState.Cleared) {
                Toast.makeText(SipActivity.this,"服务器连接失败",Toast.LENGTH_LONG).show();
            } else if (state == RegistrationState.Ok) {
                Toast.makeText(SipActivity.this,"服务器连接成功",Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onCallStateChanged(@NonNull Core core, @NonNull Call call, Call.State state, @NonNull String message) {
            tv_cStatus.setText(message);
            if (state == Call.State.IncomingReceived) {
                btnHangUp.setEnabled(true);
                btnAnswer.setEnabled(true);
                remoteAddress.setText(call.getRemoteAddress().asStringUriOnly());
            }
            else if(state == Call.State.StreamsRunning){
                btnVideo.setEnabled(true);
                if(core.getVideoDevicesList().length>2&&call.getParams().isVideoEnabled())
                    btnCamera.setEnabled(true);
            }
            else if(state == Call.State.Connected) {
                btnMute.setEnabled(true);
                btnSpeaker.setEnabled(true);
                btnAnswer.setEnabled(false);
                Toast.makeText(SipActivity.this,"接通",Toast.LENGTH_LONG).show();
            }
            else if(state == Call.State.Released) {
                btnMute.setEnabled(false);
                btnSpeaker.setEnabled(false);
                btnAnswer.setEnabled(false);
                btnVideo.setEnabled(false);
                btnCamera.setEnabled(false);
                btnHangUp.setEnabled(false);
                remoteAddress.clearComposingText();
                tv_cStatus.clearComposingText();
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sip);

        btnAnswer=findViewById(R.id.answer);
        btnMute=findViewById(R.id.mute_mic);
        btnSpeaker=findViewById(R.id.toggle_speaker);
        btnVideo=findViewById(R.id.toggle_video);
        btnCamera=findViewById(R.id.toggle_camera);
        btnHangUp=findViewById(R.id.hang_up);
        tv_rStatus=findViewById(R.id.registration_status);
        tv_cStatus=findViewById(R.id.call_status);
        remoteAddress=findViewById(R.id.remote_address);
        textureView=findViewById(R.id.remote_video_surface);
        btnCall=findViewById(R.id.call);
        captureTextureView=findViewById(R.id.local_preview_video_surface);

        Intent getintend = getIntent();
        user_data = getintend.getBundleExtra("data");

        Factory factory = Factory.instance();
        core = factory.createCore(null, null, this);

        login();

        //设置视频
        core.setNativeVideoWindowId(textureView);
        core.setNativePreviewWindowId(captureTextureView);

        core.setVideoCaptureEnabled(true);
        core.setVideoDisplayEnabled(true);
        core.getVideoActivationPolicy().setAutomaticallyAccept(true);

        core.setEchoCancellationEnabled(true);
        core.setMicGainDb(5);
        core.setPlaybackGainDb(5);

        btnCall.setOnClickListener(view -> {outGoingCall();btnHangUp.setEnabled(true);});

        btnAnswer.setOnClickListener(view -> Objects.requireNonNull(core.getCurrentCall()).accept());

        btnMute.setOnClickListener(view -> core.setMicEnabled(!core.isMicEnabled()));

        btnHangUp.setOnClickListener(view -> hangUp());

        btnSpeaker.setOnClickListener(view -> toggleSpeaker());

        btnVideo.setOnClickListener(view -> toggleVideo());

        btnCamera.setOnClickListener(view -> toggleCamera());

        btnMute.setEnabled(false);
        btnSpeaker.setEnabled(false);
        btnAnswer.setEnabled(false);
        btnVideo.setEnabled(false);
        btnCamera.setEnabled(false);
        btnHangUp.setEnabled(false);
    }

    //切换扬声器
    private void toggleSpeaker() {
        AudioDevice currentAudioDevice = Objects.requireNonNull(core.getCurrentCall()).getOutputAudioDevice();
        assert currentAudioDevice != null;
        boolean speakerEnabled = currentAudioDevice.getType() == AudioDevice.Type.Speaker;
        AudioDevice[] audioDevices = core.getAudioDevices();
        for (AudioDevice audioDevice : audioDevices) {
            if (speakerEnabled && audioDevice.getType() == AudioDevice.Type.Earpiece) {
                core.getCurrentCall().setOutputAudioDevice(audioDevice);
                return;
            } else if (!speakerEnabled && audioDevice.getType() == AudioDevice.Type.Speaker) {
                core.getCurrentCall().setOutputAudioDevice(audioDevice);
                return;
            }
        }
    }

    //打电话
    public void outGoingCall() {
        String remoteSipUri = remoteAddress.getText().toString();
        Address remoteAddress = Factory.instance().createAddress(remoteSipUri);
        if (remoteAddress == null) {
            return;
        }
        CallParams params = core.createCallParams(null);
        if (params == null) {
            return;
        }
        params.setMediaEncryption(MediaEncryption.None);

        core.inviteAddressWithParams(remoteAddress, params);
    }


    //挂断
    private void hangUp() {
        if (core.getCallsNb() == 0)
            return;
        Call call = core.getCurrentCall() != null ? core.getCurrentCall() : core.getCalls()[0];
        call.terminate();
    }

    //开关视频
    private void toggleVideo() {
        if (core.getCallsNb() == 0) return;
        Call call = core.getCurrentCall() != null ? core.getCurrentCall() : core.getCalls()[0];
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 0);
            return;
        }

        CallParams params=core.createCallParams(call);
        assert params != null;
        params.setVideoEnabled(!call.getCurrentParams().isVideoEnabled());
        call.update(params);
    }

    //切换摄像头
    private void toggleCamera(){
        String currentDevice=core.getVideoDevice();

        String[] videoDevices = core.getVideoDevicesList();
        for (String videoDevice : videoDevices) {
            if (!Objects.equals(videoDevice, currentDevice) && !Objects.equals(videoDevice, "StaticImage: Static picture")) {
                core.setVideoDevice(videoDevice);
                return;
            }
        }
    }

    //注册
    private void login(){
        String username = user_data.getString("disname");
        String password = user_data.getString("password");
        String domain = "sip.linphone.org";
        TransportType transportType=TransportType.Tls;
        AuthInfo authInfo=Factory.instance().createAuthInfo(username,null,password,null,null,domain);
        //SIP账户参数
        AccountParams accountParams=core.createAccountParams();
        //地址
        Address identity=Factory.instance().createAddress("sip:"+username+"@"+domain);
        accountParams.setIdentityAddress(identity);
        //代理服务器地址
        Address address=Factory.instance().createAddress("sip:"+domain);
        assert address != null;
        address.setTransport(transportType);
        accountParams.setRegisterEnabled(true);
        //创建SIP账户
        Account account=core.createAccount(accountParams);

        core.getConfig().setBool("video", "auto_resize_preview_to_keep_ratio", true);

        core.addAuthInfo(authInfo);
        core.addAccount(account);

        core.setDefaultAccount(account);
        //账户监听器
        core.addListener(coreListener);
        //启动核心注册
        core.start();

        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.RECORD_AUDIO)!= PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, 0);

    }
}



