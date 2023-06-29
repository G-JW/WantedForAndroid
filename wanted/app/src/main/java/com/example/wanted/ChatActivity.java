package com.example.wanted;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener{
    private Button button_call;
    private Button button_send;
    private TextView edittext;
    private TextView chat_text;
    Bundle user_data;
    Socket socket;
    PrintWriter writer;
    BufferedReader reader;
    BufferedReader consoleReader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        button_call = findViewById(R.id.button_call);
        button_send = findViewById(R.id.sendButton);
        edittext = findViewById(R.id.messageEditText);
        chat_text = findViewById(R.id.chatTextView);
        Intent getintend = getIntent();
        user_data = getintend.getBundleExtra("data");
        button_call.setOnClickListener((View.OnClickListener) this);
        button_send.setOnClickListener((View.OnClickListener) this);


        new NetworkTask().execute(); // 执行网络操作的任务


    }

    @Override
    public void onClick(View view)
    {
        if(view == button_call)
        {
            Intent intent=new Intent(ChatActivity.this,SipActivity.class);
            intent.putExtra("data",user_data);
            startActivity(intent);
        }
        else if(view == button_send)
        {
            // Read messages from the console and send them to the server
            String input;
            input = edittext.getText().toString();
            new SendTask().execute(input);
            System.out.println(input);
//            writer.println(input);
            edittext.setText("");
        }
    }

    private class MessageReader implements Runnable {
        private BufferedReader reader;

        public MessageReader(BufferedReader reader) {
            this.reader = reader;
        }

        @Override
        public void run() {
            try {
                String message;
                while ((message = reader.readLine()) != null) {
                    System.out.println(message);
                    chat_text.append(message+"\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class NetworkTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {

                String serverIp = "101.201.80.163"; // 服务器的 IP 地址
                int serverPort = 13275; // 服务器的端口号
                String clientId = user_data.getString("disname");; // 客户端的唯一 ID

                socket = new Socket(serverIp, serverPort);
                writer = new PrintWriter(socket.getOutputStream(), true);
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                consoleReader = new BufferedReader(new InputStreamReader(System.in));

                // Send client ID to the server
                writer.println(clientId);

                // Start a separate thread to read messages from the server
                Thread messageReaderThread = new Thread(new MessageReader(reader));
                messageReaderThread.start();

//                // Read messages from the console and send them to the server
//                String input;
//                while ((input = consoleReader.readLine()) != null) {
//                    writer.println(input);
//                }

//                // Cleanup
//                writer.close();
//                reader.close();
//                consoleReader.close();
//                socket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
    class SendTask extends AsyncTask<String, String, Void> {
        @Override
        protected Void doInBackground(String... params) {
            String input = params[0]; // 获取传递的参数（消息）
            writer.println(input); // 发送消息给服务器
            return null;
        }
//        @Override
//        protected void onProgressUpdate(String... values) {
//            super.onProgressUpdate(values);
//            // 在 UI 线程更新 TextView
//            String message = values[0];
//            chat_text.append(message + "\n");
//        }
    }

}