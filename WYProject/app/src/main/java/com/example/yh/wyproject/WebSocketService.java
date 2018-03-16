package com.example.yh.wyproject;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by wb on 2017/12/4.
 */

public abstract class WebSocketService extends Service {
//    private static final String TAG = WebSocketService.class.getSimpleName();
//    private BroadcastReceiver connectionReceiver;
//    private static boolean isClosed = true;
//    private static String websocketHost = "wss://socket.tthud.cn:4431";
//    private static WebSocketClient mClient;
//    private static boolean isExitApp = false;
//
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        if (connectionReceiver == null) {
//
//            connectionReceiver = new BroadcastReceiver() {
//                @Override
//                public void onReceive(Context context, Intent intent) {
//                    ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
//                    NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
//                    if (networkInfo == null || !networkInfo.isAvailable()) {
//                        Toast.makeText(getApplicationContext(), "网络已断开，请重新连接", Toast.LENGTH_SHORT).show();
//                    } else {
//                        // 监听网络，有网的时候
//                        if (mClient != null) {
//                            mClient.close();
//                        }
//                        if (isClosed) {
//                            webSocketConnect();
//                        }
//                    }
//                }
//            };
//            IntentFilter intentFilter = new IntentFilter();
//            intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
//            registerReceiver(connectionReceiver, intentFilter);
//        }
//        return super.onStartCommand(intent, flags, startId);
//    }
//
//    @Nullable
//    @Override
//    public IBinder onBind(Intent intent) {
//        return null;
//    }
//
//    public static void webSocketConnect(){
//        try {
//            mClient = new WebSocketClient(new URI(websocketHost)) {
//                @Override
//                public void onOpen(ServerHandshake handshakedata) {
//                    System.out.println( "开流--opened connection" );
//                    isClosed = false;
//                }
//                @Override
//                public void onMessage(String message) {
//                    System.out.println( "接收--received: " + message );
//                    ChatManager.getInstance().messgeHandle(message);
//                }
//                @Override
//                public void onClose(int code, String reason, boolean remote) {
//                    System.out.println( "关流--Connection closed by " + ( remote ? "remote peer" : "us" ) );
//                    isClosed = true;
//                }
//                @Override
//                public void onError(Exception ex) {
//                    ex.printStackTrace();
//                    System.out.println( "出错--Error Exception" );
//                }
//            };
//            mClient.connectBlocking();
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void closeWebsocket(boolean exitApp) {
//        isExitApp = exitApp;
//        if (mClient != null && !mClient.isClosed()) {
//            mClient.close();
//            mClient = null;
//        }
//    }
//    public static void sendMsg(String s) {
//        Log.d(TAG, "sendMsg = " + s);
//        if (mClient != null) {
//            mClient.send(s);
//        }
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        if (connectionReceiver != null) {
//            unregisterReceiver(connectionReceiver);
//        }
//    }
////    public static void main(String[] args) {
////        webSocketConnect();
////    }


}
