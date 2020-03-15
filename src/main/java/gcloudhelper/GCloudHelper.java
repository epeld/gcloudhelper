package gcloudhelper;

import com.google.gson.Gson;

import gcloudhelper.ui.QueryGUIController;
import gcloudhelper.ui.QueryGUIListener;

public class GCloudHelper implements QueryGUIListener {

	private QueryGUIController ctrl;
	
	public GCloudHelper() {
		ctrl = new QueryGUIController();
	}

	// gcloud logging read --limit 1
	
	public static void main(String[] args) {
		System.out.println("GCloudHelper");
		
		GCloudHelper gc = new GCloudHelper();
		// System.out.println(String.join(", ", new GCloudService().getLogs()));
		
		gc.showGUI();
	}

	private void showGUI() {
		ctrl.setListener(this);
		ctrl.showGUI();
	}

	@Override
	public void didSelectQuery(LogQuerySpec spec) {
		ctrl.hideGUI();
		System.out.println(new Gson().toJson(spec));
	}
}
