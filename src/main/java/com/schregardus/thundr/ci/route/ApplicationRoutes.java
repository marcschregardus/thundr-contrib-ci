package com.schregardus.thundr.ci.route;

import com.schregardus.thundr.ci.controller.CIController;
import com.threewks.thundr.route.HttpMethod;
import com.threewks.thundr.route.Router;

/**
 * Main routes (mappings between urls and controller methods handling those urls)
 */
public class ApplicationRoutes {

	public static class Names {

		public static class CI {
			private static final String Prefix = "ci-";
			public static final String Overview = Prefix + "overview";
			public static final String BuildNumber = Prefix + "buildNumber";
			public static final String BuildUrl = Prefix + "buildUrl";
			public static final String Branch = Prefix + "branch";
			public static final String CommitId = Prefix + "commitId";
			public static final String CommitterName = Prefix + "committerName";
			public static final String CommitterEmail = Prefix + "committerEmail";
			public static final String CommitterUsername = Prefix + "committerUsername";
			public static final String Message = Prefix + "message";
		}
	}

	public static void addRoutes(Router router) {
		ci(router);
	}

	private static void ci(Router router) {
		router.add(HttpMethod.GET, "/ci/", CIController.class, "overview", Names.CI.Overview);
		router.add(HttpMethod.GET, "/ci/buildNumber", CIController.class, "buildNumber", Names.CI.BuildNumber);
		router.add(HttpMethod.GET, "/ci/buildUrl", CIController.class, "buildUrl", Names.CI.BuildUrl);
		router.add(HttpMethod.GET, "/ci/branch", CIController.class, "branch", Names.CI.Branch);
		router.add(HttpMethod.GET, "/ci/commitId", CIController.class, "commitId", Names.CI.CommitId);
		router.add(HttpMethod.GET, "/ci/committerName", CIController.class, "committerName", Names.CI.CommitterName);
		router.add(HttpMethod.GET, "/ci/committerEmail", CIController.class, "committerEmail", Names.CI.CommitterEmail);
		router.add(HttpMethod.GET, "/ci/committerUsername", CIController.class, "committerUsername", Names.CI.CommitterUsername);
		router.add(HttpMethod.GET, "/ci/message", CIController.class, "message", Names.CI.Message);
	}
}
