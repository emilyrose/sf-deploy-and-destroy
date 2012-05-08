import retrieve.RetrieveBuilder;
import system.OrgEnvironment;
import deploy.DeployBuilder;
import destroy.DestructiveBuilder;

/**
 * @author rocky
 * 
 */
public class Main {

	/**
	 * @param args
	 *            [0]
	 * 
	 *            The name of the environemnt file for the org that you want to
	 *            deploy from. if the files name is example1.env then use
	 *            example1 without the .env
	 * 
	 * @param args
	 *            [1]
	 * 
	 *            The name of the environemnt file for the org that you want to
	 *            deploy to. if the files name is example1.env then use example1
	 *            without the .env
	 */
	public static void main(String[] args) {

		if (args.length != 2) {

			System.out.println("Missing args");
			System.out
					.println("Useage: deployAndDestroy [from env name] [to env name]");
			System.exit(1);
		}

		String envNameFrom = args[0].toLowerCase();
		String envNameTo = args[1].toLowerCase();

		OrgEnvironment orgFrom = new OrgEnvironment(envNameFrom);
		OrgEnvironment orgTo = new OrgEnvironment(envNameTo);

		RetrieveBuilder retrieveFrom = new RetrieveBuilder(orgFrom);
		retrieveFrom.retreive();

		RetrieveBuilder retrieveTo = new RetrieveBuilder(orgTo);
		retrieveTo.retreive();

		DestructiveBuilder destroybuilder = new DestructiveBuilder(orgFrom,
				orgTo);
		destroybuilder.buildDestructiveChanges();
		destroybuilder.printDestructiveChanges();

		// Commented out until I resolve all destructable components and their structure. 
		// DeployBuilder deployBuilder = new DeployBuilder(orgFrom, orgTo);
		// deployBuilder.deploy();
	}
}