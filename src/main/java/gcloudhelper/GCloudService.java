package gcloudhelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

import com.google.gson.Gson;

public class GCloudService {
	public static final String GCLOUD_PATH = "/Users/erikpeldan/Downloads/google-cloud-sdk/bin/gcloud";
	
	private Gson gson = new Gson();
	
	public LogEntry[] getLogs(String logName) {
		// /bin/bash -c 'gcloud logging read logName --limit=2 --quiet --format=json'
		return null;
	}
	
	public String[] getLogNames() {
		String[] args = new String[] {"logging", "logs", "list", "--quiet", "--format=json"};
		try {
			InputStream is = run(args);
			
			return gson.fromJson(new InputStreamReader(is), String[].class);
		} catch (IOException e) {
			System.err.println(e);
			return new String[] {};
		}
	}
	
	public static void stringify(InputStream inputStream) throws IOException {
		//creating an InputStreamReader object
	      InputStreamReader isReader = new InputStreamReader(inputStream);
	      //Creating a BufferedReader object
	      BufferedReader reader = new BufferedReader(isReader);
	      StringBuffer sb = new StringBuffer();
	      String str;
	      while((str = reader.readLine())!= null){
	         sb.append(str);
	      }
	      System.out.println(sb.toString());
	}
	
	private String[] getCommandLineArgs(String[] gcloudArgs) {
		String gcloudCommand = GCLOUD_PATH + String.join(" ", gcloudArgs);
		String[] initialArgs = new String[] {"/bin/bash", "-c", gcloudCommand};
		String[] both = Arrays.copyOf(initialArgs, initialArgs.length + gcloudArgs.length);
		System.arraycopy(gcloudArgs, 0, both, initialArgs.length, gcloudArgs.length);
		return both;
	}
	
	private InputStream run(String[] gcloudArgs) throws IOException {
		String[] args = getCommandLineArgs(gcloudArgs);
		System.out.println(String.join(" ", args));
		Process p = Runtime.getRuntime().exec(args);
		return p.getInputStream();
	}
}
