import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class getServer extends Thread{
    static boolean startCount = false;
    @Override
    public void run() {
        String result = jsonGetRequest("http://"+ value.ServerUrl+"/server/index.php/welcome/getexcute/"+ value.PcNumber+"/"+value.LockedStatus+"/"+value.time);
        gettedResult(result.toLowerCase());
        if(startCount)
        value.time +=5;

        if(value.LockedStatus.equals(value.unLockedClient))
        UpdateUIText();

        MakeSleep(5000);
        run();
    }

    public void UpdateUIText() {
        String tt = "    Time: ";
        tt += MakeTwoDigit((int)(value.time/3600))+":";
        tt += MakeTwoDigit((int)((value.time% 3600)/60))+":";
        tt += MakeTwoDigit((int)(value.time % 60));

        float f = ((int)(value.time/value.timeOfCost))*value.CostClient;

        if(f == 0)
            f=0.5f;

        String tt2 = "    Cost: ";
        tt2 += String.format("%.2f", f)+ " Euro";
        Main.text = tt;
        Main.text2 = tt2;

    }
    private String MakeTwoDigit(int n){
        return n > 9 ? "" + n: "0" + n;
    }
    private void gettedResult(String result) {
        //System.out.println(result);
        switch (result)
        {
            case value.LockedClient:
                LockPc();
                break;
            case value.unLockedClient:
                unLockPc();
                break;
            case value.ShutdonwClient:
                ShutdownPc();
                break;
        }
    }

    public void ShutdownPc() {
        System.out.println("shut");
    }

    public void unLockPc() {
        System.out.println("|||unlock");
    }

    public void LockPc() {
        System.out.println("|||lock");
    }

    private void MakeSleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String streamToString(InputStream inputStream) {
        String text = new Scanner(inputStream, "UTF-8").useDelimiter("\\Z").next();
        return text;
    }

    public String jsonGetRequest(String urlQueryString) {
        String json = null;
        try {
            URL url = new URL(urlQueryString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("charset", "utf-8");
            connection.connect();
            InputStream inStream = connection.getInputStream();
            json = streamToString(inStream); // input stream to string
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return json;
    }

}
