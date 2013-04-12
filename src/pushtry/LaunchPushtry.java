package pushtry;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.MessageType;
import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.wm.StatusBar;
import com.intellij.openapi.wm.WindowManager;
import com.intellij.ui.awt.RelativePoint;

public class LaunchPushtry extends AnAction
{
	private Pushtry _pushtry;

	public void actionPerformed(AnActionEvent e)
	{
		if (this.getPushtryInstance() == null)
		{
			this.showMessage("There was an error launching pushtry.", MessageType.ERROR, e);
			return;
		}

		this.getPushtryInstance().run();
		this.showMessage("Successfully launched pushtry.", MessageType.INFO, e);
	}

	private Pushtry getPushtryInstance()
	{
		if (this._pushtry == null)
		{
			String username = System.getenv("USER");

			if (username != null && username.length() > 0)
			{
				this._pushtry = new Pushtry(username, username + ".dev.box.net");
			}
		}

		return this._pushtry;
	}

	private void showMessage(String message, MessageType messageType, AnActionEvent e)
	{
		StatusBar statusBar = WindowManager.getInstance().getStatusBar(e.getProject());

		JBPopupFactory.getInstance().
				createHtmlTextBalloonBuilder(message, messageType, null).
				setFadeoutTime(1500).
				createBalloon().
				show(RelativePoint.getNorthWestOf(statusBar.getComponent()), Balloon.Position.atRight);
	}
}
