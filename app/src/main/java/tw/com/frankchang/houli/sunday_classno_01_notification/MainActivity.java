package tw.com.frankchang.houli.sunday_classno_01_notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //啟動 Notification
    private void startNotification(int id, Notification notification) {
        NotificationManager notificationManager;

        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(id, notification);
    }

    //一般基本的
    public void basicOnClick(View view) {
        int notifyId = 9001;

        Notification note = new Notification.Builder(this)
                //大圖示
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.box))
                //小圖示
                .setSmallIcon(R.drawable.apple)
                .setContentTitle(getString(R.string.MainActivity_basic_title))
                .setContentText(getString(R.string.MainActivity_basic_text))
                .setSubText(getString(R.string.MainActivity_basic_sub))
                .setTicker(getString(R.string.MainActivity_basic_ticker))
                .setAutoCancel(true)    //點擊自動取消
                .build();   //建立

        startNotification(notifyId, note);
    }

    //有時間的
    public void timeOnClick(View view) {
        int notifyId = 9002;

        Notification note = new Notification.Builder(this)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.box))
                .setSmallIcon(R.drawable.apple)
                .setContentTitle(getString(R.string.MainActivity_time_title))
                .setContentText(getString(R.string.MainActivity_time_text))
                .setSubText(getString(R.string.MainActivity_time_sub))
                .setTicker(getString(R.string.MainActivity_time_ticker))

                .setShowWhen(true)  //是否顯示設置的時間戳
                .setWhen(System.currentTimeMillis())    //使用系統目前的時間

                .setAutoCancel(true)
                .build();

        startNotification(notifyId, note);
    }

    //有聲音的
    public void soundOnClick(View view) {
        int notifyId = 9003;
        //Uri uri_sound = Uri.parse("android.resource://" + getPackageName() + "/raw/zeta");

        Notification note = new Notification.Builder(this)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.box))
                .setSmallIcon(R.drawable.apple)
                .setContentTitle(getString(R.string.MainActivity_sound_title))
                .setContentText(getString(R.string.MainActivity_sound_text))
                .setSubText(getString(R.string.MainActivity_sound_sub))
                .setTicker(getString(R.string.MainActivity_sound_ticker))

                //設定要有聲音
                .setDefaults(Notification.DEFAULT_SOUND)
                //自訂音效
                //.setSound(uri_sound)

                .setAutoCancel(true)
                .build();

        //聲音要持續叫（必需要在建立後再另外設定）
        //note.flags = Notification.FLAG_INSISTENT;

        startNotification(notifyId, note);
    }

    //有震動的
    public void shockOnClick(View view) {
        int notifyId = 9004;

        //震動效果，陣列中元素依序為停止、震動的時間，單位是毫秒
        //long[] vibrate = {1000, 2000, 1000, 3000};

        Notification note = new Notification.Builder(this)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.box))
                .setSmallIcon(R.drawable.apple)
                .setContentTitle(getString(R.string.MainActivity_shock_title))
                .setContentText(getString(R.string.MainActivity_shock_text))
                .setSubText(getString(R.string.MainActivity_shock_sub))
                .setTicker(getString(R.string.MainActivity_shock_ticker))

                //設定震動，需注意必需新增「使用震動的權限」
                .setDefaults(Notification.DEFAULT_VIBRATE)
                //自訂震動
                //.setVibrate(vibrate)

                .setAutoCancel(true)
                .build();

        startNotification(notifyId, note);
    }

    //有優先權的
    public void priorityOnClick(View view) {
        int notifyId = 9005;

        Notification note = new Notification.Builder(this)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.box))
                .setSmallIcon(R.drawable.apple)
                .setContentTitle(getString(R.string.MainActivity_priority_title))
                .setContentText(getString(R.string.MainActivity_priority_text))
                .setSubText(getString(R.string.MainActivity_priority_sub))
                .setTicker(getString(R.string.MainActivity_priority_ticker))

                //設定高優先權
                .setPriority(Notification.PRIORITY_HIGH)    //優先權：高（至少要這個起跳）
                //.setPriority(Notification.PRIORITY_MAX)   //優先權：最高

                .setAutoCancel(true)
                .build();

        startNotification(notifyId, note);
    }

    //有圖檔的
    public void imagesOnClick(View view) {
        int notifyId = 9006;

        Notification note = new Notification.Builder(this)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.box))
                .setSmallIcon(R.drawable.apple)
                .setContentTitle(getString(R.string.MainActivity_images_title))
                .setContentText(getString(R.string.MainActivity_images_text))
                .setSubText(getString(R.string.MainActivity_images_sub))
                .setTicker(getString(R.string.MainActivity_images_ticker))

                //設定取得圖檔
                .setStyle(getBigImages())

                .setAutoCancel(true)
                .build();

        startNotification(notifyId, note);
    }

    //取得圖檔
    private Notification.Style getBigImages() {
        //建立物
        Notification.BigPictureStyle bigPictureStyle = new Notification.BigPictureStyle();
        //取得圖檔
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.volvo_60);
        //設定圖檔到物件中
        bigPictureStyle.bigPicture(bmp);
        //設定圖檔的說明文字
        bigPictureStyle.setSummaryText(getString(R.string.MainActivity_imagesText));

        return bigPictureStyle;
    }

    //有大量文字的
    public void textOnClick(View view) {
        int notifyId = 9007;

        Notification note = new Notification.Builder(this)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.box))
                .setSmallIcon(R.drawable.apple)
                .setContentTitle(getString(R.string.MainActivity_text_title))
                .setContentText(getString(R.string.MainActivity_text_text))
                .setSubText(getString(R.string.MainActivity_text_sub))
                .setTicker(getString(R.string.MainActivity_text_ticker))

                //設定取得大量文字
                .setStyle(getLotsOfText())

                .setAutoCancel(true)
                .build();

        startNotification(notifyId, note);
    }

    private Notification.Style getLotsOfText() {
        String strLotsOfText;

        strLotsOfText = "TNR\n    誘捕、絕育、放回原地（英文：Trap Neuter Return，縮寫：TNR），是一種宣稱" +
                "能管理和減少流浪犬和流浪貓數量的方法。TNR藉由施以絕育手術，使之無法繁殖。自從1980年代後期和1990年" +
                "代早期以後，目前數個動保團體在美國逐漸推動TNR[1]。TNR仍然存在一些非傳統方法使TNR無法被普遍性地接" +
                "受。根據2010年一項研究指出，控制TNR動物族群易受其他因素干擾。\n    近年來在美歐有些TNR的團體，" +
                "他們改稱CNR（捕捉、結紮、放回原處，Catch Neuter Return，簡稱CNR），他們認為用捕捉比誘捕好聽，" +
                "放回原處比野放更合乎這活動的實際意義，這個稱呼的改變使字面的意義更接近實際的活動，更接近人道的作法。";
        //------ 以上是有換行的最大顯示文字數量

        //strLotsOfText += "\n    後來也開始出現誘捕、絕育、注射疫苗、放回原地（英文：Trap Neuter Vaccine " +
        //        "Return，縮寫：TNVR），增加了注射狂犬病";
        //------ 以上是沒有換行的最大顯示文字數量

        Notification.BigTextStyle bigTextStyle = new Notification.BigTextStyle();
        bigTextStyle.bigText(strLotsOfText);

        return bigTextStyle;
    }

    //可呼叫 APP 的
    public void callAPPOnClick(View view) {
        int notifyId = 9008;

        Notification note = new Notification.Builder(this)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.box))
                .setSmallIcon(R.drawable.apple)
                .setContentTitle(getString(R.string.MainActivity_callAPP_title))
                .setContentText(getString(R.string.MainActivity_callAPP_text))
                .setSubText(getString(R.string.MainActivity_callAPP_sub))
                .setTicker(getString(R.string.MainActivity_callAPP_ticker))

                //設定 PendingIntent 用來呼叫 APP 頁面
                .setContentIntent(getPendingIntent(notifyId))

                .setAutoCancel(true)
                .build();

        startNotification(notifyId, note);
    }

    private PendingIntent getPendingIntent(int requestCode) {
        //呼叫內建的計算機（已知模擬器計算機的 PackageName）
        //Intent intent = getPackageManager().getLaunchIntentForPackage("com.android.calculator2");
        //PendingIntent pendingIntent = PendingIntent.getActivity(this, requestCode, intent,
        //        PendingIntent.FLAG_ONE_SHOT);
        //return pendingIntent;

        //尋找關鍵字方式呼叫計算機
        Intent intent;
        PendingIntent pendingIntent;
        final PackageManager pm = getPackageManager();

        ArrayList<HashMap<String,Object>> items =new ArrayList<>();
        List<PackageInfo> packs = pm.getInstalledPackages(0);

        for (PackageInfo pi : packs) {
            if( pi.packageName.toString().toLowerCase().contains("calcul")){
                HashMap<String, Object> map = new HashMap<>();
                map.put("appName", pi.applicationInfo.loadLabel(pm));
                map.put("packageName", pi.packageName);
                items.add(map);
            }
        }

        if(items.size() >= 1){
            String packageName = (String) items.get(0).get("packageName");
            intent = pm.getLaunchIntentForPackage(packageName);
            pendingIntent = PendingIntent.getActivity(this, requestCode, intent,
                    PendingIntent.FLAG_ONE_SHOT);

            return pendingIntent;
        }
        else{
            Toast.makeText(this, R.string.MainActivity_call_calculator_error, Toast.LENGTH_SHORT).show();
        }

        //網路查到的說：Api >= 15 就能以下面程式呼叫，但模擬器測試無反應，實機待測
        //Intent intent = new Intent();
        //intent.setAction(Intent.ACTION_MAIN);
        //intent.addCategory(Intent.CATEGORY_APP_CALCULATOR);
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //PendingIntent pendingIntent = PendingIntent.getActivity(this, requestCode, intent,
        //        PendingIntent.FLAG_ONE_SHOT);
        //return pendingIntent;

        return null;
    }

    //有按鈕的
    public void buttonOnClick(View view) {
        int notifyId = 9009;

        Notification note = new Notification.Builder(this)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.box))
                .setSmallIcon(R.drawable.apple)
                .setContentTitle(getString(R.string.MainActivity_button_title))
                .setContentText(getString(R.string.MainActivity_button_text))
                .setSubText(getString(R.string.MainActivity_button_sub))
                .setTicker(getString(R.string.MainActivity_button_ticker))

                //設定按鈕
                .addAction(R.drawable.apple,
                        getString(R.string.MainActivity_call_calculator),
                        getPendingIntent(notifyId))

                //需 API Level 20 以上才可使用
                //Notification.Action action = new Notification.Action.Builder(R.drawable.apple,
                //                                          "呼叫計算機", getPendingIntent(notifyId))
                //                              .build();
                //.addAction(getActionButton(notifyId))

                .setAutoCancel(true)
                .build();
        //note.flags = Notification.FLAG_AUTO_CANCEL;

        startNotification(notifyId, note);
    }
}
