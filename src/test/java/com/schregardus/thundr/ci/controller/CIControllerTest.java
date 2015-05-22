package com.schregardus.thundr.ci.controller;

import com.threewks.thundr.view.View;
import com.threewks.thundr.view.handlebars.HandlebarsView;
import com.threewks.thundr.view.json.JsonView;
import com.threewks.thundr.view.string.StringView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CIControllerTest {

	@Mock private HttpServletRequest request;

	private String ciBuildNumber;
	private String ciBuildUrl;
	private String ciBranch;
	private String ciCommitId;
	private String ciCommitterName;
	private String ciCommitterEmail;
	private String ciCommitterUsername;
	private String ciMessage;

	private CIController ciController;

	@Before
	public void setUp() throws Exception {

		ciBuildNumber = "Some Build Number";
		ciBuildUrl = "Some Build Url";
		ciBranch = "Some CI Branch";
		ciCommitId = "Some Commit Id";
		ciCommitterName = "Some Committer Name";
		ciCommitterEmail = "Some Committer Email";
		ciCommitterUsername = "Some Committer Username";
		ciMessage = "Some Message";

		ciController = new CIController(ciBuildNumber, ciBuildUrl, ciBranch, ciCommitId, ciCommitterName,
				ciCommitterEmail, ciCommitterUsername,
				ciMessage);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void shouldProvideJsonOverview() throws Exception {

		when(request.getContentType()).thenReturn("application/json");

		View overview = ciController.overview(request);
		assertThat(overview, notNullValue());
		assertThat(overview, instanceOf(JsonView.class));

		JsonView jOverview = (JsonView) overview;
		assertThat(jOverview.getOutput(), notNullValue());
		assertThat(jOverview.getOutput(), instanceOf(Map.class));

		Map<String, Object> ciMap = (Map<String, Object>) jOverview.getOutput();
		assertThat(ciMap.size(), is(8));

		assertThat((String) ciMap.get("buildNumber"), is(ciBuildNumber));
		assertThat((String) ciMap.get("buildUrl"), is(ciBuildUrl));
		assertThat((String) ciMap.get("branch"), is(ciBranch));
		assertThat((String) ciMap.get("commitId"), is(ciCommitId));
		assertThat((String) ciMap.get("committerName"), is(ciCommitterName));
		assertThat((String) ciMap.get("committerEmail"), is(ciCommitterEmail));
		assertThat((String) ciMap.get("committerUsername"), is(ciCommitterUsername));
		assertThat((String) ciMap.get("message"), is(ciMessage));

		verify(request, times(1)).getContentType();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void shouldProvideHandlebarsOverview() throws Exception {

		when(request.getContentType()).thenReturn("application/html");

		View overview = ciController.overview(request);
		assertThat(overview, notNullValue());
		assertThat(overview, instanceOf(HandlebarsView.class));

		HandlebarsView hOverview = (HandlebarsView) overview;
		assertThat(hOverview.getView(), notNullValue());
		assertThat(hOverview.getView(), is("/hbs/ci.hbs"));

		assertThat(hOverview.getModel(), notNullValue());
		assertThat(hOverview.getModel(), instanceOf(Map.class));

		Map<String, Object> ciMap = hOverview.getModel();
		assertThat(ciMap.size(), is(8));

		assertThat((String) ciMap.get("buildNumber"), is(ciBuildNumber));
		assertThat((String) ciMap.get("buildUrl"), is(ciBuildUrl));
		assertThat((String) ciMap.get("branch"), is(ciBranch));
		assertThat((String) ciMap.get("commitId"), is(ciCommitId));
		assertThat((String) ciMap.get("committerName"), is(ciCommitterName));
		assertThat((String) ciMap.get("committerEmail"), is(ciCommitterEmail));
		assertThat((String) ciMap.get("committerUsername"), is(ciCommitterUsername));
		assertThat((String) ciMap.get("message"), is(ciMessage));

		verify(request, times(1)).getContentType();
	}

	@Test
	public void shouldGetBuildNumber() throws Exception {

		StringView stringView = ciController.buildNumber();
		assertThat(stringView, notNullValue());
		assertThat(stringView.content(), is(ciBuildNumber));
	}

	@Test
	public void shouldGetBuildUrl() throws Exception {

		StringView stringView = ciController.buildUrl();
		assertThat(stringView, notNullValue());
		assertThat(stringView.content(), is(ciBuildUrl));
	}

	@Test
	public void shouldGetBranch() throws Exception {

		StringView stringView = ciController.branch();
		assertThat(stringView, notNullValue());
		assertThat(stringView.content(), is(ciBranch));
	}

	@Test
	public void shouldGetCommitId() throws Exception {

		StringView stringView = ciController.commitId();
		assertThat(stringView, notNullValue());
		assertThat(stringView.content(), is(ciCommitId));
	}

	@Test
	public void shouldGetCommitterName() throws Exception {

		StringView stringView = ciController.committerName();
		assertThat(stringView, notNullValue());
		assertThat(stringView.content(), is(ciCommitterName));
	}

	@Test
	public void shouldGetCommitterEmail() throws Exception {

		StringView stringView = ciController.committerEmail();
		assertThat(stringView, notNullValue());
		assertThat(stringView.content(), is(ciCommitterEmail));
	}

	@Test
	public void shouldGetCommitterUsername() throws Exception {

		StringView stringView = ciController.committerUsername();
		assertThat(stringView, notNullValue());
		assertThat(stringView.content(), is(ciCommitterUsername));
	}

	@Test
	public void shouldGetMessage() throws Exception {

		StringView stringView = ciController.message();
		assertThat(stringView, notNullValue());
		assertThat(stringView.content(), is(ciMessage));
	}
}