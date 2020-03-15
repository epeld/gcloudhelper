package gcloudhelper;

public class LogQuerySpec {
	private String logName;
	
	public LogQuerySpec(String logName) {
		this.logName = logName;
	}

	public String getLogName() {
		return logName;
	}

	public void setLogName(String logName) {
		this.logName = logName;
	}
}
