package katsura.weibo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import weibo4j.Oauth;
import weibo4j.Timeline;
import weibo4j.http.AccessToken;
import weibo4j.model.StatusWapper;
import weibo4j.model.WeiboException;
import weibo4j.util.BareBonesBrowserLaunch;

public class OAuth4Code {
	public static void main(String [] args) throws WeiboException, IOException{
		Oauth oauth = new Oauth();
		
		HttpClient client = new HttpClient();
		GetMethod request = new GetMethod(oauth.authorize("code"));
		try{
			BareBonesBrowserLaunch.openURL(oauth.authorize("code"));
			System.out.println(oauth.authorize("code"));
			System.out.print("Hit enter when it's done.[Enter]:");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String code = br.readLine();
			Log.logInfo("code: " + code);
			
			AccessToken token = oauth.getAccessTokenByCode(code);
			System.out.println(token);
			Timeline tm = new Timeline(token.getAccessToken());
			StatusWapper status = tm.getUserTimeline();
			long total = status.getStatuses().size();
			System.out.println(total);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
}
