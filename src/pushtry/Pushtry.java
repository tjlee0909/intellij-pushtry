package pushtry;

import java.io.IOException;

/**
 * This class is responsible for actually executing the remote pushtry script
 * via SSH from the client.
 */
public class Pushtry
{
	private final String PUSHTRY_SCRIPT_COMMAND = "/box/www/devtools/bin/pushtry_webapp_remotely";
	private String _username;
	private String _hostname;

	/**
	 * @param username - the user to SSH into hostname as and execute commands as
	 * @param hostname - the the hostname to SSH to
	 */
	public Pushtry(String username, String hostname)
	{
		this._username = username;
		this._hostname = hostname;
	}

	public void run()
	{
		this.executeCommandRemotely(this.PUSHTRY_SCRIPT_COMMAND);
	}

	/**
	 * Execute a command remotely via SSH on this._hostname as this._username.
	 *
	 * @param remoteCommand - the linux command to execute on the remote host
	 * @return Successfully executed SSH command?
	 */
	private void executeCommandRemotely(String remoteCommand)
	{
		Runtime rt = Runtime.getRuntime();
		Process sshProcess;

		try
		{
			String cmd = "ssh " + this._username + "@" + this._hostname + " '" + remoteCommand + "'";
			sshProcess = rt.exec(cmd);
		}
		catch (IOException e)
		{
			System.out.println(e.getMessage());
		}
	}
}