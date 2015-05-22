package com.schregardus.thundr.ci.route;

import com.schregardus.thundr.ci.controller.CIController;
import com.threewks.thundr.route.HttpMethod;
import com.threewks.thundr.route.Router;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;

import static com.schregardus.thundr.ci.route.ApplicationRoutes.Names;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class ApplicationRoutesTest {

	private Router router;

	@Before
	public void before() {
		router = new Router();

		ApplicationRoutes.addRoutes(router);
	}

	@Test
	public void shouldConstructObjects() {

		// Just to get the test coverage up - could protect instantiation with private
		// constructors, but is just noise in the code
		new ApplicationRoutes();
		new Names();
		new Names.CI();
	}

	@Test
	public void shouldHaveCIRoutes() throws Exception {

		assertThat(router.has(Names.CI.Overview), is(true));
		assertThat(router.has(HttpMethod.GET, "/ci/"), is(true));
		assertThat(CIController.class.getMethod("overview", HttpServletRequest.class), notNullValue());

		assertThat(router.has(Names.CI.BuildNumber), is(true));
		assertThat(router.has(HttpMethod.GET, "/ci/buildNumber"), is(true));
		assertThat(CIController.class.getMethod("buildNumber"), notNullValue());

		assertThat(router.has(Names.CI.BuildUrl), is(true));
		assertThat(router.has(HttpMethod.GET, "/ci/buildUrl"), is(true));
		assertThat(CIController.class.getMethod("buildUrl"), notNullValue());

		assertThat(router.has(Names.CI.Branch), is(true));
		assertThat(router.has(HttpMethod.GET, "/ci/branch"), is(true));
		assertThat(CIController.class.getMethod("branch"), notNullValue());

		assertThat(router.has(Names.CI.CommitId), is(true));
		assertThat(router.has(HttpMethod.GET, "/ci/commitId"), is(true));
		assertThat(CIController.class.getMethod("commitId"), notNullValue());

		assertThat(router.has(Names.CI.CommitterName), is(true));
		assertThat(router.has(HttpMethod.GET, "/ci/committerName"), is(true));
		assertThat(CIController.class.getMethod("committerName"), notNullValue());

		assertThat(router.has(Names.CI.CommitterEmail), is(true));
		assertThat(router.has(HttpMethod.GET, "/ci/committerEmail"), is(true));
		assertThat(CIController.class.getMethod("committerEmail"), notNullValue());

		assertThat(router.has(Names.CI.CommitterUsername), is(true));
		assertThat(router.has(HttpMethod.GET, "/ci/committerUsername"), is(true));
		assertThat(CIController.class.getMethod("committerUsername"), notNullValue());

		assertThat(router.has(Names.CI.Message), is(true));
		assertThat(router.has(HttpMethod.GET, "/ci/message"), is(true));
		assertThat(CIController.class.getMethod("message"), notNullValue());
	}
}